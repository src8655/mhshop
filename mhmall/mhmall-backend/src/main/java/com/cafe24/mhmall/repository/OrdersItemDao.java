package com.cafe24.mhmall.repository;

import java.util.List;

import com.cafe24.mhmall.vo.OrdersItemVo;

public interface OrdersItemDao {

	List<OrdersItemVo> selectListByOrdersNo(String ordersNo);		// 주문번호로 주문상품 리스트
	Integer insert(OrdersItemVo ordersItemVo);						// 주문내역 일괄 추가
	List<OrdersItemVo> getTimeOverList(Long ordersTime);			// 초과된 주문의 번호에 해당하는 옵션번호와 수량 리스트를 받음

}
