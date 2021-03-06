package com.kh.spring17.vo.pay;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/*
 * 결제 요청 내용을 확인하기 위해 필요한 데이터를 갖는 vo
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class KakaoPayRequestResult {
	private String tid;
	private String next_redirect_pc_url;
	private String created_at;
	
}
