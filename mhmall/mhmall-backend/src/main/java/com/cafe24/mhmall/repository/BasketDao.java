package com.cafe24.mhmall.repository;

import java.util.List;

import com.cafe24.mhmall.vo.BasketVo;

public interface BasketDao {

	List<BasketVo> getGuestListByCnt(String guestSession);	// 수량보다 재고가 없는 리스트 받기
	Integer deleteByNo(Long no);							// 삭제
	Integer guestNewTime(String guestSession);				// 입력 시간을 현재로 갱신
	List<BasketVo> getListByGuest(String guestSession);		// 장바구니 리스트
	Integer deleteByOptionGuest(BasketVo vo);				// 현재 장바구니에 같은 옵션 삭제
	Integer insertGuest(BasketVo vo);						// 비회원 장바구니 추가
	Integer deleteGuestByNo(BasketVo vo);					// 비회원 장바구니 삭제
	BasketVo getByNoGuest(BasketVo vo);						// 비회원 장바구니 정보가 존재하는지 확인하고 가져오기
	Integer updateCnt(BasketVo basketVo);					// 장바구니 수정

}
