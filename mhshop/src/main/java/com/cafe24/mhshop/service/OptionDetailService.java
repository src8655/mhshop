package com.cafe24.mhshop.service;

import java.util.List;

import com.cafe24.mhshop.vo.OptionDetailVo;

public interface OptionDetailService {

	boolean deleteAllByItemNo(Long itemNo);				// 상품번호에 속한 상세옵션 삭제
	List<OptionDetailVo> getListByItemNoAndLevel(Long itemNo, Long level);	// 상품번호에 속하고 레벨에 따른 상세옵션 리스트
	boolean add(OptionDetailVo optionDetailVo);			// 상세옵션 추가
	boolean delete(Long no);							// 상세옵션 삭제

}
