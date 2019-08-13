package com.cafe24.mhmall.frontend.dto;

import java.util.List;

import com.cafe24.mhmall.frontend.vo.OrdersItemVo;


public class ResponseOrdersDto {
	private String ordersNo;
	private List<OrdersItemVo> ordersItemList;

	public ResponseOrdersDto() {}
	public ResponseOrdersDto(String ordersNo, List<OrdersItemVo> ordersItemList) {
		this.ordersNo = ordersNo;
		this.ordersItemList = ordersItemList;
	}
	public String getOrdersNo() {
		return ordersNo;
	}
	public void setOrdersNo(String ordersNo) {
		this.ordersNo = ordersNo;
	}
	public List<OrdersItemVo> getOrdersItemList() {
		return ordersItemList;
	}
	public void setOrdersItemList(List<OrdersItemVo> ordersItemList) {
		this.ordersItemList = ordersItemList;
	}

}
