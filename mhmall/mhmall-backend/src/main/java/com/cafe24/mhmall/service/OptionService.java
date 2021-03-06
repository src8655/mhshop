package com.cafe24.mhmall.service;

import java.util.List;

import com.cafe24.mhmall.vo.OptionVo;
import com.cafe24.mhmall.vo.OrdersItemVo;

public interface OptionService {
	
	boolean add(OptionVo optionVo);									// 옵션추가
	boolean delete(Long no);										// 옵션 삭제
	List<OptionVo> getListByItemNo(OptionVo optionVo);				// 상품번호에 속한 레벨별 옵션 리스트
	boolean hasOptionDetailNo(Long no);								// 상세옵션번호를 가지는 옵션이 있는지 확인 요청
	List<OptionVo> getListByItemNo(Long no);						// 상품번호에 속한 옵션 리스트
	OptionVo getByNo(Long no);										// 옵션번호로 옵션하나 받아오기
	boolean isExistAllOption(Long[] optionNos);						// 존재하는 옵션들인지 확인
	boolean isExistAllCnt(Long[] optionNos, Integer[] optionCnts);	// 옵션의 재고가 있는지 확인
	Long moneySum(Long[] optionNos, Integer[] optionCnts);			// 금액계산
	boolean restoreCnt(List<OrdersItemVo> ordersItemList);			// 구매한 수량만큼 재고량 복구
	boolean isExistOption(Long optionNos);							// 존재하는 옵션인지 확인
	boolean isExistCnt(Long optionNos, Long optionCnts);			// 옵션의 재고가 수량만큼 존재하는지 확인
	boolean isExistOption(Long[] optionNos);						// 존재하는 옵션인지 확인(여러개로 변경)
	boolean isExistCnt(Long[] optionNos, Long[] optionCnts);		// 옵션의 재고가 수량만큼 존재하는지 확인(여러개로 변경)
	boolean isOnSaleAll(Long[] optionNos);							// 판매중인 상품들 인지 확인

}
