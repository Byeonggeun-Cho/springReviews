package com.kh.spring08.service;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.kh.spring08.entity.Menu;
import com.kh.spring08.entity.MenuImage;
import com.kh.spring08.repository.ImageFileDao;
import com.kh.spring08.repository.MenuDao;
import com.kh.spring08.repository.MenuImageDao;

@Service
public class MenuServiceImpl implements MenuService {

	@Autowired
	private MenuDao menuDao;
	
	@Autowired
	private MenuImageDao menuImageDao;
	
	@Autowired
	private ImageFileDao imageFileDao;
	
	@Override
	public void add(Menu menu, List<MultipartFile> fileList) throws IllegalStateException, IOException {

		int no = menuDao.add(menu);
		
		// 파일이 존재하는 경우에만 저장 로직 처리
		// list의 경우 첫 번째 인자의 존재여부로 확인
		if(!fileList.get(0).isEmpty()) {
			
			// 실제 저장 코드
			// 1. 저장될 파일의 객체 생성
			for(MultipartFile file: fileList) {
				
				// 테이블 저장 코드
				
				// 2. 데이터 저장
				MenuImage image = MenuImage.builder()
										.file_name(file.getOriginalFilename())
										.file_type(file.getContentType())
										.file_size(file.getSize())
										.menu_no(no)
										.build();
				
				int file_no = menuImageDao.add(image);

				// 2. 저장
				imageFileDao.save(file, file_no);
				
			}
		}
		
		
	}

}
