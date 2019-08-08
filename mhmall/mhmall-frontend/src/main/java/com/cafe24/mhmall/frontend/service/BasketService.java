package com.cafe24.mhmall.frontend.service;

import java.util.ArrayList;

import com.cafe24.mhmall.frontend.dto.ResponseJSONResult;
import com.cafe24.mhmall.frontend.vo.BasketVo;

public interface BasketService {
	
	public static class ListBasketVo extends ArrayList<BasketVo> {}

	ResponseJSONResult<Boolean> guestAdd(Long optionNo, Long cnt, String guestSession);		// 비회원 장바구니 추가 요청
	ResponseJSONResult<Boolean> memberAdd(String mockToken, Long optionNo, Long cnt);		// 회원장바구니 추가 요청
	ResponseJSONResult<ListBasketVo> guestList(String guestSession);						// 비회원일 때 비회원장바구니 리스트 요청
	ResponseJSONResult<ListBasketVo> memberList(String mockToken);							// 회원일 때 회원장바구니 리스트 요청
	ResponseJSONResult<Boolean> guestUpdate(Long no, Long cnt, String guestSession);		// 비회원일 때 비회원장바구니 수정 요청
	ResponseJSONResult<Boolean> memberUpdate(String mockToken, Long no, Long cnt);			// 회원일 때 회원장바구니 수정 요청
	ResponseJSONResult<Boolean> guestDelete(Long no, String guestSession);					// 비회원일 때 비회원장바구니 삭제 요청
	ResponseJSONResult<Boolean> memberDelete(String mockToken, Long no);					// 회원일 때 회원장바구니 삭제 요청

}
