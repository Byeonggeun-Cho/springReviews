package com.kh.spring12.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Zone {
	private int zone_no;
	private String zone_name;
	private int city_no;
}
