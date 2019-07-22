package com.cafe24.mhmall.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cafe24.mhmall.repository.OrdersDao;
import com.cafe24.mhmall.service.OrdersService;
import com.cafe24.mhmall.vo.OrdersVo;

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
	public boolean changeStatus(String ordersNo, String status) {
		Map<String, String> map = new HashMap<String, String>();
		map.put("ordersNo", ordersNo);
		map.put("status", status);
		Integer result = ordersDao.updateStatus(map);
		return result == 1;
	}


	// 운송장번호 변경
	@Override
	public boolean changeTrackingNum(String ordersNo, String trackingNum) {
		Map<String, String> map = new HashMap<String, String>();
		map.put("ordersNo", ordersNo);
		map.put("trackingNum", trackingNum);
		Integer result = ordersDao.updateTrackingNum(map);
		return result == 1;
	}

}
