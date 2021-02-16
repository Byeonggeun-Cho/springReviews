package com.kh.spring17.entity;

import java.sql.Date;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Payment {
	private int no;
	private String tid;
	private String partner_order_id;
	private String partner_user_id;
	private int total_amount;
	private Date time;
}
