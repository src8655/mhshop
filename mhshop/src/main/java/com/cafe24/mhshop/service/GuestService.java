package com.cafe24.mhshop.service;

import com.cafe24.mhshop.vo.GuestVo;

public interface GuestService {

	GuestVo getByOrdersNo(String ordersNo);		// 비회원 상세

}
