package com.cafe24.mhshop.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.cafe24.mhshop.service.OrdersService;
import com.cafe24.mhshop.vo.OrdersVo;

@Service
public class OrdersServiceImpl implements OrdersService {


	// 가짜DB
	private List<OrdersVo> getOrdersTable() {
		List<OrdersVo> ordersTable = new ArrayList<OrdersVo>();
		ordersTable.add(new OrdersVo("2019-07-11_000256", "2019-07-11", "입금대기", "무통장입금", 10000L, null, "test_name1", "01000000001", "test_zipcode1", "test_addr1", "test_id1"));
		ordersTable.add(new OrdersVo("2019-07-11_000257", "2019-07-11", "결제완료", "카카오페이", 20000L, null, "test_name2", "01000000002", "test_zipcode2", "test_addr2", null));
		ordersTable.add(new OrdersVo("2019-07-11_000258", "2019-07-11", "배송중", "카카오페이", 30000L, null, "test_name3", "01000000003", "test_zipcode3", "test_addr3", null));
		
		return ordersTable;
	}
	
	
	
	// 주문리스트
	@Override
	public List<OrdersVo> getList() {
		
		// DAO에 요청
		
		// 가짜
		return getOrdersTable();
	}


	// 주문상세
	@Override
	public OrdersVo getByOrdersNo(String ordersNo) {
		
		// DAO에 요청
		
		// 가짜
		List<OrdersVo> ordersTable =  getOrdersTable();
		for(OrdersVo vo : ordersTable) if(ordersNo.equals(vo.getOrdersNo())) return vo;
		return null;
	}


	// 상태 변경
	@Override
	public boolean changeStatus(String ordersNo, String string) {

		// DAO에 요청
		
		// 가짜
		return true;
	}


	// 운송장번호 변경
	@Override
	public boolean changeTrackingNum(String ordersNo, String trackingNum) {

		// DAO에 요청
		
		// 가짜
		return true;
	}

}
