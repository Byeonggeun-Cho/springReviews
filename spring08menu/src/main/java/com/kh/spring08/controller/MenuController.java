package com.kh.spring08.controller;

import java.io.File;
import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.io.FileUtils;
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
import com.kh.spring08.service.MenuService;

@Controller
@RequestMapping("/menu")	// 공용매핑 - 컨트롤러 간 충돌 방지
public class MenuController {

	@Autowired
	private MenuService menuService;
	
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
		
		menuService.add(menu, imList);
		
		return "redirect:add";
	}
	
	
	@Autowired
	private SqlSession sqlSession;
	
	// 이미지 다운로드
	// - 메뉴 번호를 파라미터로 받아 해당하는 메뉴의 이미지 파일을 전송
	// - jsp 반환 없이 직접 다운로드 진행하므로 반환형을 void
	@GetMapping("/download")
	public void download(
			HttpServletResponse response,
			@RequestParam int no) throws IOException {
		
		// 1. no를 이용해서 MenuImage 정보를 불러온다
		MenuImage image = sqlSession.selectOne("menu_image.find", no);
		
		// 2. image 정보를 이용해 실제 파일을 불러온다.
		File target = new File("D:/upload/menu", String.valueOf(no));
		
		byte[] data = FileUtils.readFileToByteArray(target);	// commons-io의 명령
		
		// 3. 사용자에게 파일을 전송
		response.getOutputStream().write(data);
	}
	
}
