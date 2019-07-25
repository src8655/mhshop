package com.cafe24.mhmall.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cafe24.mhmall.repository.GuestDao;
import com.cafe24.mhmall.service.GuestService;
import com.cafe24.mhmall.vo.GuestVo;
import com.cafe24.mhmall.vo.OrdersVo;

@Service
public class GuestServiceImpl implements GuestService {

	@Autowired
	GuestDao guestDao;
	
	
	
	// 비회원 상세
	@Override
	public GuestVo getByOrdersNo(String ordersNo) {
		GuestVo guestVo = new GuestVo();
		guestVo.setOrdersNo(ordersNo);
		
		return guestDao.selectOne(guestVo);
	}


	// 비회원 데이터 추가
	@Override
	public boolean add(String ordersNo, GuestVo vo) {
		vo.setOrdersNo(ordersNo);
		Integer result = guestDao.insert(vo);
		return result == 1;
	}


	// 비회원의 주문리스트 찾기(주문번호, 주문일, 상태)
	@Override
	public List<OrdersVo> findOrdersNo(GuestVo vo) {
		return guestDao.findOrdersNo(vo);
	}


	// 조건에 맞는 주문번호가 존재하는지?
	@Override
	public boolean findPw(GuestVo vo) {
		Integer count = guestDao.findPw(vo);
		return count != 0;
	}


	// 비회원 주문 비밀번호 변경
	@Override
	public boolean changePw(GuestVo vo) {
		Integer result = guestDao.changePw(vo);
		return result != 0;
	}

	
}
