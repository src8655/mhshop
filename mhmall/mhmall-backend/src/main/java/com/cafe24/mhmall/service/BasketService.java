package com.cafe24.mhmall.service;

import java.util.List;

import com.cafe24.mhmall.vo.BasketVo;

public interface BasketService {

	boolean guestDeleteByCnt(String guestSession);				// 장바구니 리스트 중에 수량보다 재고가 없는 것 일괄삭제
	boolean guestNewTime(String guestSession);					// 입력 시간을 현재로 갱신(비회원의 장바구니는 30개월간만 유지된다)
	List<BasketVo> getListByGuest(String guestSession);			// 장바구니 리스트
	boolean deleteByOptionGuest(BasketVo vo);					// 현재 장바구니에 같은 옵션 삭제
	boolean addGuest(BasketVo vo);								// 비회원 장바구니 추가
	boolean deleteGuest(BasketVo vo);							// 비회원 장바구니 삭제
	BasketVo getByNoGuest(BasketVo vo);							// 비회원 장바구니 정보가 존재하는지 확인하고 가져오기
	boolean updateCnt(Long no, Long cnt);						// 장바구니 수정
	boolean memberDeleteByCnt(String id);						// 장바구니 리스트 중에 수량보다 재고가 없는 것 일괄삭제(회원)
	List<BasketVo> getListByMember(String id);					// 회원 장바구니 리스트
	boolean deleteByOptionMember(String id, Long optionNo);		// 현재 장바구니에 같은 옵션 삭제(회원)
	boolean addMember(BasketVo vo, String id);					// 회원 장바구니 추가
	boolean deleteMember(Long no, String id);					// 회원 장바구니 삭제
	BasketVo getByNoMember(BasketVo vo, String id);				// 회원 장바구니 정보가 존재하는지 확인하고 가져오기
	Integer deleteTimeOver(Long basketTime);					// 시간이 초과된 비회원 장바구니들은 삭제

}
