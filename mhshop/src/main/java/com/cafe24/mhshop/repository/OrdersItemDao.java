package com.cafe24.mhshop.repository;

import java.util.List;

import com.cafe24.mhshop.vo.OrdersItemVo;

public interface OrdersItemDao {

	List<OrdersItemVo> selectListByOrdersNo(String ordersNo);		// 주문번호로 주문상품 리스트

}
