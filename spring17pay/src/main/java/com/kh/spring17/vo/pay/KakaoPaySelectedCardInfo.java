package com.kh.spring17.vo.pay;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/*
 * 카드 결제 상세 내역
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class KakaoPaySelectedCardInfo {
	private String card_bin;
	private int install_month;
	private String card_corp_name;
	private String interest_free_install;
}
