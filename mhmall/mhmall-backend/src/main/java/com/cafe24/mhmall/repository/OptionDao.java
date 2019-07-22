package com.cafe24.mhmall.repository;

import java.util.List;

import com.cafe24.mhmall.vo.OptionVo;

public interface OptionDao {

	List<OptionVo> selectListLevel(OptionVo optionVo);	// 상품번호에 속한 옵션 리스트
	Integer countByOptionDetailNo(Long no);				// 상세옵션번호를 가지는 옵션이 있는지 확인 요청
	Integer insert(OptionVo optionVo);					// 옵션추가
	Integer delete(Long no);							// 옵션 삭제
	List<OptionVo> selectList(Long no);					// 상품번호에 속한 옵션 리스트
	OptionVo selectOne(Long no);						// 옵션번호로 옵션하나 받아오기

}
