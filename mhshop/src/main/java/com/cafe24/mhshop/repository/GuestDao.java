package com.cafe24.mhshop.repository;

import com.cafe24.mhshop.vo.GuestVo;

public interface GuestDao {

	GuestVo selectOne(GuestVo guestVo);		// 비회원 상세

}
