package com.cafe24.mhshop.dto;

import org.hibernate.validator.constraints.NotEmpty;

import com.cafe24.mhshop.vo.OrdersVo;

public class RequestOrdersNoDto {
	@NotEmpty
	private String ordersNo;

	public OrdersVo toVo() {
		return new OrdersVo(ordersNo, null, null, null, null, null, null, null, null, null, null, null, null);
	}
	public String getOrdersNo() {
		return ordersNo;
	}

	public void setOrdersNo(String ordersNo) {
		this.ordersNo = ordersNo;
	}
	
}
