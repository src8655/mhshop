package com.cafe24.mhmall.service;

import com.cafe24.mhmall.vo.GuestVo;

public interface GuestService {

	GuestVo getByOrdersNo(String ordersNo);		// 비회원 상세
	boolean add(String ordersNo, GuestVo vo);	// 비회원 데이터 추가

}
