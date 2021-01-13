package com.kh.spring08.repository;

import java.io.File;
import java.io.IOException;

import org.springframework.stereotype.Repository;
import org.springframework.web.multipart.MultipartFile;

@Repository
public class ImageFileDaoImpl implements ImageFileDao{

//	private final String path = "D:\\uplaod\\menu";
	private final String path = "D:/upload/menu";
	
	@Override
	public void save(MultipartFile file, int file_no) throws IllegalStateException, IOException {
		File target = new File(path, String.valueOf(file_no));
		
		file.transferTo(target);
	}
}
