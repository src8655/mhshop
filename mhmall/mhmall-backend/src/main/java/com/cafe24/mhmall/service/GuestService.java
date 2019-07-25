package com.cafe24.mhmall.service;

import java.util.List;

import com.cafe24.mhmall.vo.GuestVo;
import com.cafe24.mhmall.vo.OrdersVo;

public interface GuestService {

	GuestVo getByOrdersNo(String ordersNo);		// 비회원 상세
	boolean add(String ordersNo, GuestVo vo);	// 비회원 데이터 추가
	List<OrdersVo> findOrdersNo(GuestVo vo);	// 비회원의 주문리스트 찾기(주문번호, 주문일, 상태)
	boolean findPw(GuestVo vo);					// 조건에 맞는 주문번호가 존재하는지?
	boolean changePw(GuestVo vo);				// 비회원 주문 비밀번호 변경

}
