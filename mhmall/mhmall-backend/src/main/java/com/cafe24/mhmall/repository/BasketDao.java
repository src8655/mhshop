package com.cafe24.mhmall.repository;

import java.util.List;

import com.cafe24.mhmall.vo.BasketVo;

public interface BasketDao {

	List<BasketVo> getGuestListByCnt(String guestSession);	// 수량보다 재고가 없는 리스트 받기
	Integer deleteByNo(Long no);							// 삭제
	Integer guestNewTime(String guestSession);				// 입력 시간을 현재로 갱신
	List<BasketVo> getListByGuest(String guestSession);		// 장바구니 리스트

}
