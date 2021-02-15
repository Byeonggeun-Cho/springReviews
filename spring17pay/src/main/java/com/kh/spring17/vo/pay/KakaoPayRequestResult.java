package com.kh.spring17.vo.pay;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class KakaoPayRequestResult {
	private String tid;
	private String next_redirect_pc_url;
	private String created_at;
	
}
