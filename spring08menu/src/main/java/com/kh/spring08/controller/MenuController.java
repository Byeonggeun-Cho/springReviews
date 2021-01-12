package com.kh.spring08.controller;

import java.io.File;
import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.kh.spring08.entity.Menu;
import com.kh.spring08.entity.MenuImage;

@Controller
@RequestMapping("/menu")	// 공용매핑 - 컨트롤러 간 충돌 방지
public class MenuController {

	@Autowired
	private SqlSession sqlSession;
	
	private final String path = "D:\\upload\\menu";
	
	// 메뉴 등록 매핑
	// - GET: 입력 페이지 전송
	// - POST: 처리 후 다른 곳으로 리다이렉트
	
	@GetMapping("/add")
	public String add(HttpSession session) {
		
//		return "/WEB-INF/views/menu/add.jsp";
		return "menu/add";
	}
	
	// 파일 업로드가 이루어질 경우 컨트롤러에서는 MultipartFile 형태로 수신한다.
	// - getName(): 파라미터 명
	// - getOriginalFilename(): 파일명
	// - getContentType(): MIME-TYPE(검사/분류/...)
	// - getSize(): 파일크기(byte)
	@PostMapping("/add")
	public String add(@ModelAttribute Menu menu,
//						@RequestParam MultipartFile imList	// 단일 파일의 경우 / 파라미터명 일치
						@RequestParam List<MultipartFile> imList) throws IllegalStateException, IOException {
		
		// 파일이 들어왔는지 확인
//		for(MultipartFile im: imList) {
//			System.out.println(im.getName());
//			System.out.println(im.getOriginalFilename());
//			System.out.println(im.getContentType());
//			System.out.println(im.getSize());
//			System.out.println("----------");
//		}
		
		// 등록
		int no = sqlSession.selectOne("menu.seq");
		menu.setNo(no);
		sqlSession.insert("menu.add", menu);
		
		// 파일이 존재하는 경우에만 저장 로직 처리
		// list의 경우 첫 번째 인자의 존재여부로 확인
		if(!imList.get(0).isEmpty()) {
			
			// no를 이용해서 파일 테이블에 정보 저장 및 실제 파일을 하드디스크에 저장
			// - 저장위치: path(D:\\upload\\menu)
			// - table: menu_image
			
			// 실제 저장 코드
			// 1. 저장될 파일의 객체 생성
			for(MultipartFile im: imList) {
				
				// 테이블 저장 코드
				// 1. 번호 먼저 생성
				int file_no = sqlSession.selectOne("menu_image.seq");
				// 2. 데이터 저장
				MenuImage image = MenuImage.builder()
										.file_no(file_no)
										.file_name(im.getOriginalFilename())
										.file_type(im.getContentType())
										.file_size(im.getSize())
										.menu_no(no)
										.build();
				
				sqlSession.insert("menu_image.add", image);		
			
				File target = new File(path, String.valueOf(file_no));
	
				// 2. 저장
				im.transferTo(target);
			}
		}
		
		return "redirect:add";
	}
	
}
