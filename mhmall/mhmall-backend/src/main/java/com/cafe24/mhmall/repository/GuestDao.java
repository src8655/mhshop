package com.cafe24.mhmall.repository;

import com.cafe24.mhmall.vo.GuestVo;

public interface GuestDao {

	GuestVo selectOne(GuestVo guestVo);		// 비회원 상세

}
