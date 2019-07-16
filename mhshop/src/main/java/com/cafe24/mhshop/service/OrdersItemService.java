package com.cafe24.mhshop.service;

import java.util.List;

import com.cafe24.mhshop.vo.OrdersItemVo;

public interface OrdersItemService {

	List<OrdersItemVo> getListByOrdersNo(String ordersNo);		// 주문번호로 주문상품 리스트

}
