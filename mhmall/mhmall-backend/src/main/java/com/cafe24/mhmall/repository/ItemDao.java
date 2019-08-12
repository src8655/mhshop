package com.cafe24.mhmall.repository;

import java.util.List;
import java.util.Map;

import com.cafe24.mhmall.vo.ItemVo;
import com.cafe24.mhmall.vo.MainImgVo;

public interface ItemDao {

	Integer countByCategory(Long categoryNo);			// 카테고리번호에 해당하는 아이템 개수
	List<ItemVo> selectList(ItemVo itemVo);				// 상품 리스트
	Integer insert(ItemVo itemVo);						// 상품 등록
	Integer delete(Long no);							// 상품 삭제
	ItemVo selectOne(Long no);							// 상품번호로 상품정보
	Integer update(ItemVo itemVo);						// 상품 수정
	Integer updateDisplay(ItemVo itemVo);				// 상품진열여부 수정
	List<ItemVo> selectListU(Map<String, Object> daoMap);// 사용자 상품리스트
	List<ItemVo> selectNewList(ItemVo vo);				// 최근 상품리스트
	List<MainImgVo> getNewItemList(Integer showCnt);	// 최근 메인 이미지 리스트 요청
	Integer countU(Map<String, Object> mapCnt);			// 회원 총 상품 개수

}
