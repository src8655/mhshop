package com.cafe24.mhshop.repository;

import java.util.List;

import com.cafe24.mhshop.vo.OptionDetailVo;

public interface OptionDetailDao {

	List<OptionDetailVo> selectList(OptionDetailVo optionDetailVo);			// 상품번호에 속하고 레벨에 따른 상세옵션 리스트
	Integer insert(OptionDetailVo optionDetailVo);							// 상세옵션 추가
	Integer delete(Long no);												// 상세옵션 삭제
	Integer count(Long no);													// 존재하는 상세옵션인지 확인

}
