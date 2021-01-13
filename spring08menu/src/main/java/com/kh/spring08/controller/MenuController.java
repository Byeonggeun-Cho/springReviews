package com.kh.spring08.controller;

import java.io.File;
import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpSession;

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
import com.kh.spring08.repository.ImageFileDao;
import com.kh.spring08.repository.MenuDao;
import com.kh.spring08.repository.MenuImageDao;

@Controller
@RequestMapping("/menu")	// 공용매핑 - 컨트롤러 간 충돌 방지
public class MenuController {

	// MenuDao 하위 계층에서 등록된 도구를 가져와라
	@Autowired
	private MenuDao menuDao;
	
	@Autowired
	private MenuImageDao menuImageDao;
	
	@Autowired
	private ImageFileDao imageFileDao;
	
	
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
		
		
		int no = menuDao.add(menu);
		
		// 파일이 존재하는 경우에만 저장 로직 처리
		// list의 경우 첫 번째 인자의 존재여부로 확인
		if(!imList.get(0).isEmpty()) {
			
			// 실제 저장 코드
			// 1. 저장될 파일의 객체 생성
			for(MultipartFile im: imList) {
				
				// 테이블 저장 코드
				
				// 2. 데이터 저장
				MenuImage image = MenuImage.builder()
										.file_name(im.getOriginalFilename())
										.file_type(im.getContentType())
										.file_size(im.getSize())
										.menu_no(no)
										.build();
				
				int file_no = menuImageDao.add(image);

				// 2. 저장
				imageFileDao.save(im, file_no);
				
			}
		}
		
		return "redirect:add";
	}
	
}
