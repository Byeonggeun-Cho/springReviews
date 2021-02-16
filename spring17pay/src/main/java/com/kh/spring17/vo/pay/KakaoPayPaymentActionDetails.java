package com.kh.spring17.vo.pay;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/*
 * 결제 관련 상세 내역
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class KakaoPayPaymentActionDetails {
	private String aid;
	private String approved_at;
	private int amount;
	private int point_amount;
	private int discount_amount;
	private String payment_action_type;
	private String payload;
}
