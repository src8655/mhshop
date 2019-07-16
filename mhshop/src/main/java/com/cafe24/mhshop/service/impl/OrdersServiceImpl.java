package com.cafe24.mhshop.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cafe24.mhshop.repository.OrdersDao;
import com.cafe24.mhshop.service.OrdersService;
import com.cafe24.mhshop.vo.OrdersVo;

@Service
public class OrdersServiceImpl implements OrdersService {

	@Autowired
	OrdersDao ordersDao;

	// 가짜DB
	private List<OrdersVo> getOrdersTable() {
		List<OrdersVo> ordersTable = new ArrayList<OrdersVo>();
		ordersTable.add(new OrdersVo("2019-07-11_000256", "2019-07-11", "입금대기", "국민", "123456789", null, 10000L, null, "test_name1", "01000000001", "test_zipcode1", "test_addr1", "test_id1", null));
		ordersTable.add(new OrdersVo("2019-07-11_000257", "2019-07-11", "결제완료", "기업", "987654321", null, 20000L, null, "test_name2", "01000000002", "test_zipcode2", "test_addr2", null, null));
		ordersTable.add(new OrdersVo("2019-07-11_000258", "2019-07-11", "배송중", "국민", "111111111", null, 30000L, null, "test_name3", "01000000003", "test_zipcode3", "test_addr3", null, null));
		
		return ordersTable;
	}
	
	
	
	// 주문리스트
	@Override
	public List<OrdersVo> getList() {
		return ordersDao.selectList();
	}


	// 주문상세
	@Override
	public OrdersVo getByOrdersNo(String ordersNo) {
		OrdersVo ordersVo = new OrdersVo();
		ordersVo.setOrdersNo(ordersNo);
		return ordersDao.selectOne(ordersVo);
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
