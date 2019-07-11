package com.cafe24.mhshop.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.cafe24.mhshop.service.OrdersItemService;
import com.cafe24.mhshop.vo.OrdersItemVo;

@Service
public class OrdersItemServiceImpl implements OrdersItemService {

	
	// 가짜DB
	private List<OrdersItemVo> getOrdersItemTable() {
		List<OrdersItemVo> ordersItemTable = new ArrayList<OrdersItemVo>();
		ordersItemTable.add(new OrdersItemVo(1L, "2019-07-11_000256", 1L, "test_item1", "test_thmbnail1", "파란색", "L", 10000L, 1L));
		ordersItemTable.add(new OrdersItemVo(2L, "2019-07-11_000257", 2L, "test_item2", "test_thmbnail2", "파란색", "XL", 20000L, 1L));
		ordersItemTable.add(new OrdersItemVo(3L, "2019-07-11_000258", 1L, "test_item1", "test_thmbnail3", "파란색", "L", 10000L, 1L));
		ordersItemTable.add(new OrdersItemVo(4L, "2019-07-11_000258", 2L, "test_item2", "test_thmbnail4", "파란색", "XL", 20000L, 1L));
		
		return ordersItemTable;
	}
	
	
	// 주문번호로 주문상품 리스트
	@Override
	public List<OrdersItemVo> getListByOrdersNo() {
		
		// DAO에 요청
		
		// 가짜
		return getOrdersItemTable();
	}

}
