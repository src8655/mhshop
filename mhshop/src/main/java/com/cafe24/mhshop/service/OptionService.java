package com.cafe24.mhshop.service;

import java.util.List;

import com.cafe24.mhshop.vo.OptionVo;

public interface OptionService {
	
	boolean add(OptionVo optionVo);						// 옵션추가
	boolean delete(Long no);							// 옵션 삭제
	List<OptionVo> getListByItemNo(OptionVo optionVo);	// 상품번호에 속한 레벨별 옵션 리스트
	boolean hasOptionDetailNo(Long no);					// 상세옵션번호를 가지는 옵션이 있는지 확인 요청
	List<OptionVo> getListByItemNo(Long no);			// 상품번호에 속한 옵션 리스트

}
