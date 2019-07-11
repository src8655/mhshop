package com.cafe24.mhshop.service;

import com.cafe24.mhshop.vo.PayKakaoVo;

public interface PayKakaoService {

	PayKakaoVo getByOrdersNo(String ordersNo);		// 카카오결제 상세

}
