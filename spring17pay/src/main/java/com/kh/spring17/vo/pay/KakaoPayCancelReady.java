package com.kh.spring17.vo.pay;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/*
 * 카카오 결제 취소 준비 vo
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class KakaoPayCancelReady {
	private String tid;
	private int cancel_amount;
	private int cancel_tax_free_amount;
}
