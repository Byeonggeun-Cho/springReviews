package com.kh.spring17.vo.pay;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/*
 * 결제 승인을 확인하기 위해 필요한 데이터를 갖는 vo
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class KakaoPayApproveResult {
	private String aid;
	private String tid;
	private String cid;
	private String sid;
	private String partner_order_id;
	private String partner_user_id;
	private String payment_method_type;
	
	private KakaoPayAmount amount;
	private KakaoPayCardInfo card_info;
	
	private String item_name;
	private String item_code;
	private int quantity;
	private Date created_at;
	private Date approved_at;
	private String payload;
}
