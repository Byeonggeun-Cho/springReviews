package com.kh.spring08.entity;

import java.sql.Date;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MenuImage {
	private int file_no;
	private String file_name;
	private long file_size;
	private String file_type;
	private Date file_time;
	private int menu_no;

}
