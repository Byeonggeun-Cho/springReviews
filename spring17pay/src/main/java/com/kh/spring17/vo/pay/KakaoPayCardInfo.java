package com.kh.spring17.vo.pay;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/*
 * 카카오페이 결제카드 정보를 갖는 vo
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class KakaoPayCardInfo {
	private String purchase_corp;
	private String purchase_corp_code;
	private String issuer_corp;
	private String kakaopay_purchase_corp;
	private String kakaopay_purchase_corp_code;
	private String kakaopay_issuer_corp;
	private String kakaopay_issuer_corp_code;
	private String bin;
	private String card_type;
	private String instal_month;
	private String approved_id;
	private String card_mid;
	private String interest_free_install;
	private String card_item_code;
}
