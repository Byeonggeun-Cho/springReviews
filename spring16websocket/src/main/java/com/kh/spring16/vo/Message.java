package com.kh.spring16.vo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/*
 * JSON 형태로 주고받는 서버에서 사용할 양식
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Message {
	private int room;
	private String type;
	private String content;
	private String time;
}
