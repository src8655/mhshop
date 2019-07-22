package com.cafe24.mhmall.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cafe24.mhmall.repository.OrdersDao;
import com.cafe24.mhmall.service.OrdersService;
import com.cafe24.mhmall.vo.GuestVo;
import com.cafe24.mhmall.vo.OrdersVo;

@Service
public class OrdersServiceImpl implements OrdersService {

	@Autowired
	OrdersDao ordersDao;

	
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


	// 비회원 주문 데이터 추가
	@Override
	public String guestOrdersAdd(Long money) {
		OrdersVo ordersVo = new OrdersVo();
		ordersVo.setMoney(money);
		
		// 가상계좌정보 랜덤생성
		ordersVo.setBankName("국민");
		ordersVo.setBankNum("123-45-678910");
		ordersVo.setStatus("주문대기");
		
		return ordersDao.insert(ordersVo);
	}


	// 존재하는 주문이고 상태가 "주문대기"인지 확인
	@Override
	public boolean isExistAndValid(GuestVo vo) {
		Integer count = ordersDao.isExistAndValid(vo);
		return count != 0;
	}



}
