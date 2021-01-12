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
public class Menu {
	private int no;
	private String name;
	private String type;
	private int price;
	private Date reg;
}
