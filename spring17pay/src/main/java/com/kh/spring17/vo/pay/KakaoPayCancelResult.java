package com.kh.spring17.vo.pay;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/*
 * 카카오 페이 결제 취소 결과 정보 vo
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class KakaoPayCancelResult {
	private String aid;
	private String tid;
	private String cid;
	private String status;
	private String partner_order_id;
	private String partner_user_id;
	private String payment_method_type;
	
	private String item_name;
	private int quantity;

	private KakaoPayAmount amount;
	private KakaoPayAmount approved_canceled_amount;
	private KakaoPayAmount canceled_amount;
	private KakaoPayAmount canceled_available_amount;
	
	private Date created_at;
	private Date approved_at;
	private Date canceled_at;
}
