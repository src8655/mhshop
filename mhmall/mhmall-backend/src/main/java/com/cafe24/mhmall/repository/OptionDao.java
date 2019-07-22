package com.cafe24.mhmall.repository;

import java.util.List;
import java.util.Map;

import com.cafe24.mhmall.vo.OptionVo;

public interface OptionDao {

	List<OptionVo> selectListLevel(OptionVo optionVo);	// 상품번호에 속한 옵션 리스트
	Integer countByOptionDetailNo(Long no);				// 상세옵션번호를 가지는 옵션이 있는지 확인 요청
	Integer insert(OptionVo optionVo);					// 옵션추가
	Integer delete(Long no);							// 옵션 삭제
	List<OptionVo> selectList(Long no);					// 상품번호에 속한 옵션 리스트
	OptionVo selectOne(Long no);						// 옵션번호로 옵션하나 받아오기
	Integer selectCnt(Long no);							// 옵션의 재고 가져오기
	Integer countByNo(Long no);							// 옵션번호에 해당하는 옵션개수
	Integer updateCnt(Map<String, Object> map);			// 옵션 재고량 줄이기
	Long selectSumMoney(Map<String, Object> map);		// 금액계산

}
