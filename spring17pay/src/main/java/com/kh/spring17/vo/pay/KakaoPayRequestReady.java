package com.kh.spring17.vo.pay;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/*
 * 결제를 요청하기 위해 필요한 데이터를 갖는 vo
 * 
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class KakaoPayRequestReady {
	private String partner_order_id;
	private String partner_user_id;
	private String item_name;
	private int quantity;
	private int total_amount;
}
