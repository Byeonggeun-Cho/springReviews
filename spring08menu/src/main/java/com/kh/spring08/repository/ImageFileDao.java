package com.kh.spring08.repository;

import java.io.IOException;

import org.springframework.web.multipart.MultipartFile;

public interface ImageFileDao {
	// 실제 저장 기능
	public void save(MultipartFile file, int file_no) throws IllegalStateException, IOException;
}
