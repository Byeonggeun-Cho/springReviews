package com.kh.spring17.vo.pay;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

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
