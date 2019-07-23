package com.cafe24.mhmall.service;

import java.util.List;

import com.cafe24.mhmall.vo.OrdersItemVo;

public interface OrdersItemService {

	List<OrdersItemVo> getListByOrdersNo(String ordersNo);					// 주문번호로 주문상품 리스트
	boolean add(String ordersNo, Long[] optionNos, Integer[] optionCnts);	// 주문내역 일괄 추가
	List<OrdersItemVo> getTimeOverList(Long ordersTime);					// 초과된 주문의 번호에 해당하는 옵션번호와 수량 리스트를 받음

}
