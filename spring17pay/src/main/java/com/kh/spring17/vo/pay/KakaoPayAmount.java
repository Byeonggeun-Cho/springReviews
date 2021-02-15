package com.kh.spring17.vo.pay;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/*
 * 금액 정보를 갖는 vo
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class KakaoPayAmount {
	private int total;
	private int tax_free;
	private int vat;
	private int point;
	private int discount;
}
