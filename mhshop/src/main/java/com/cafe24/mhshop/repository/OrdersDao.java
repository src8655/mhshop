package com.cafe24.mhshop.repository;

import java.util.List;

import com.cafe24.mhshop.vo.OrdersVo;

public interface OrdersDao {

	List<OrdersVo> selectList();			// 주문리스트
	OrdersVo selectOne(OrdersVo ordersVo);	// 주문상세

}
