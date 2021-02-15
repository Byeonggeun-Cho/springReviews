package com.kh.spring17.vo.pay;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/*
 * 결제 승인을 요청하기 위해 필요한 데이터를 갖는 vo
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class KakaoPayApproveReady {
	private String tid;
	private String partner_order_id;
	private String partner_user_id;
	private String pg_token;
}
