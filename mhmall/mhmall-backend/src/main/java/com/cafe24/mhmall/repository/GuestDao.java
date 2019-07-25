package com.cafe24.mhmall.repository;

import java.util.List;

import com.cafe24.mhmall.vo.GuestVo;
import com.cafe24.mhmall.vo.OrdersVo;

public interface GuestDao {

	GuestVo selectOne(GuestVo guestVo);			// 비회원 상세
	Integer insert(GuestVo vo);					// 비회원 데이터 추가
	List<OrdersVo> findOrdersNo(GuestVo vo);	// 비회원의 주문리스트 찾기(주문번호, 주문일, 상태)
	Integer findPw(GuestVo vo);					// 조건에 맞는 주문번호가 존재하는지?
	Integer changePw(GuestVo vo);				// 비회원 주문 비밀번호 변경

}
