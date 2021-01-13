package com.kh.spring08.service;

import java.io.IOException;
import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.kh.spring08.entity.Menu;

public interface MenuService {
	// 메뉴 등록
	public void add(Menu menu, List<MultipartFile> fileList) throws IllegalStateException, IOException;
	
}
