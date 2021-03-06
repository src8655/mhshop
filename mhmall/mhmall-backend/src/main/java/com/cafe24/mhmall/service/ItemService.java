package com.cafe24.mhmall.service;

import java.util.List;
import java.util.Optional;

import com.cafe24.mhmall.vo.ItemVo;
import com.cafe24.mhmall.vo.ItemsVo;
import com.cafe24.mhmall.vo.MainImgVo;

public interface ItemService {

	boolean hasItemByCategory(Long categoryNo);		// 카테고리번호에 해당하는 아이템이 있는지?
	List<ItemVo> getList(ItemVo itemVo);			// 상품 리스트
	boolean add(ItemVo itemVo);						// 상품 등록
	boolean delete(Long no);						// 상품 삭제
	ItemVo getByNo(Long no);						// 상품번호로 상품정보
	boolean edit(ItemVo itemVo);					// 상품 수정
	boolean editDisplay(Long no, String display);	// 상품진열여부 수정
	ItemsVo getListU(Optional<Long> categoryNo, Optional<Integer> pages, Optional<String> kwd);			// 사용자 상품리스트
	List<ItemVo> getNewList(ItemVo vo);				// 최근 상품리스트
	List<MainImgVo> getNewImgList(Integer showCnt);	// 최근 메인 이미지 리스트 요청

}
