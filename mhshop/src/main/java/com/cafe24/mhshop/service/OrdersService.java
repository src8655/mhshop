package com.cafe24.mhshop.service;

import java.util.List;

import com.cafe24.mhshop.vo.OrdersVo;

public interface OrdersService {

	List<OrdersVo> getList();										// 주문리스트
	OrdersVo getByOrdersNo(String ordersNo);						// 주문상세
	boolean changeStatus(String ordersNo, String status);			// 상태 변경
	boolean changeTrackingNum(String ordersNo, String trackingNum);	// 운송장번호 변경

}
