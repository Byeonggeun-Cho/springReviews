package com.kh.spring17.vo.pay;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/*
 * 카카오페이 조회 결과
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class KakaoPaySearchResult {
	private String tid;
	private String cid;
	private String status;
	private String partner_order_id;
	private String partner_user_id;
	private String payment_method_type;
	
	private KakaoPayAmount amount;
	private KakaoPayAmount canceled_amount;
	private KakaoPayAmount canceled_available_amount;
	
	private String item_name;
	private String item_code;
	private int quantity;
	private Date created_at;
	private Date approved_at;
	private Date canceled_at;
	
	private KakaoPaySelectedCardInfo selected_card_info;
	
	private KakaoPayPaymentActionDetails[] payment_action_details;
}
