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


	// 주문 데이터 추가
	@Override
	public String guestOrdersAdd(Long money, String memberId) {
		OrdersVo ordersVo = new OrdersVo();
		ordersVo.setMoney(money);
		ordersVo.setMemberId(memberId);
		
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


	// 주문에 받는사람 정보를 변경하고 상태를 "입금대기"로 변경
	@Override
	public boolean ordersPost(String ordersNo, OrdersVo vo) {
		vo.setOrdersNo(ordersNo);
		vo.setStatus("입금대기");
		Integer result = ordersDao.orderUpdate(vo);
		return result == 1;
	}


	// 존재하는 주문이고 상태가 "주문대기"인지 확인(회원)
	@Override
	public boolean isExistAndValidMember(String ordersNo, String id) {
		OrdersVo ordersVo = new OrdersVo();
		ordersVo.setOrdersNo(ordersNo);
		ordersVo.setMemberId(id);
		Integer count = ordersDao.isExistAndValidMember(ordersVo);
		return count != 0;
	}

	
	// 존재하고 주문대기 상태가 아닌 것이 존재하는지
	@Override
	public boolean isExistAndEnable(GuestVo vo) {
		Integer count = ordersDao.isExistAndEnable(vo);
		return count != 0;
	}



}
