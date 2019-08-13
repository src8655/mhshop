package com.cafe24.mhmall.frontend.dto;

import java.util.List;

import com.cafe24.mhmall.frontend.vo.OrdersItemVo;
import com.cafe24.mhmall.frontend.vo.OrdersVo;


public class ResponseOrdersViewDto {
	private OrdersVo ordersVo;
	private List<OrdersItemVo> ordersItemList;

	public ResponseOrdersViewDto() {}
	public ResponseOrdersViewDto(OrdersVo ordersVo, List<OrdersItemVo> ordersItemList) {
		this.ordersVo = ordersVo;
		this.ordersItemList = ordersItemList;
	}
	public OrdersVo getOrdersVo() {
		return ordersVo;
	}
	public void setOrdersVo(OrdersVo ordersVo) {
		this.ordersVo = ordersVo;
	}
	public List<OrdersItemVo> getOrdersItemList() {
		return ordersItemList;
	}
	public void setOrdersItemList(List<OrdersItemVo> ordersItemList) {
		this.ordersItemList = ordersItemList;
	}
	

}
