package com.kh.spring12.entity;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

// City와 연관된 Zone 정보를 같이 저장
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CityWithZone {
	private int city_no;
	private String city_name;
	private List<Zone> list;
}
