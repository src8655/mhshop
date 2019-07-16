package com.cafe24.mhshop.dto;

import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import com.cafe24.mhshop.vo.OrdersVo;

public class RequestOrdersTrackingDto {
	@NotEmpty
	private String ordersNo;
	@NotEmpty
	private String trackingNum;
	
	public OrdersVo toVo() {
		return new OrdersVo(ordersNo, null, null, null, null, null, null, trackingNum, null, null, null, null, null);
	}
	
	public String getOrdersNo() {
		return ordersNo;
	}
	public void setOrdersNo(String ordersNo) {
		this.ordersNo = ordersNo;
	}
	public String getTrackingNum() {
		return trackingNum;
	}
	public void setTrackingNum(String trackingNum) {
		this.trackingNum = trackingNum;
	}
	
	
}
