package com.cafe24.mhmall.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import com.cafe24.mhmall.repository.OptionDao;
import com.cafe24.mhmall.service.OptionService;
import com.cafe24.mhmall.vo.OptionVo;
import com.cafe24.mhmall.vo.OrdersItemVo;

@Service
public class OptionServiceImpl implements OptionService {
	
	@Autowired
	OptionDao optionDao;
	
	
	

	// 상품번호에 속한 레벨별 옵션 리스트
	@Override
	public List<OptionVo> getListByItemNo(OptionVo optionVo) {
		if(optionVo.getOptionDetailNo1() == -1) optionVo.setOptionDetailNo1(null);
		return optionDao.selectListLevel(optionVo);
	}


	// 옵션추가
	@Override
	public boolean add(OptionVo optionVo) {
		Integer result = optionDao.insert(optionVo);
		return result == 1;
	}

	
	// 옵션 삭제
	@Override
	public boolean delete(Long no) {
		Integer result = optionDao.delete(no);
		return result == 1;
	}


	// 상세옵션번호를 가지는 옵션이 있는지 확인 요청
	@Override
	public boolean hasOptionDetailNo(Long no) {
		Integer count = optionDao.countByOptionDetailNo(no);
		return count != 0;
	}


	// 상품번호에 속한 옵션 리스트
	@Override
	public List<OptionVo> getListByItemNo(Long no) {
		return optionDao.selectList(no);
	}


	// 옵션번호로 옵션하나 받아오기
	@Override
	public OptionVo getByNo(Long no) {
		return optionDao.selectOne(no);
	}



	// 존재하는 옵션들인지 확인
	@Override
	public boolean isExistAllOption(Long[] optionNos) {
		if(optionNos.length == 0) return false;
		
		for(Long no : optionNos) {
			Integer count = optionDao.countByNo(no);
			if(count == 0) return false;
		}
		
		return true;
	}


	
	// 옵션의 재고가 있는지 확인
	// sqlException 발생 시 롤백
	//@Transactional(rollbackFor=Exception.class)
	@Override
	public boolean isExistAllCnt(Long[] optionNos, Integer[] optionCnts) throws Exception {
		// 잘못된 접근은 무조건 없는 재고
		if(optionNos.length == 0) return false;
		if(optionNos.length != optionCnts.length) return false;
		// 옵션 재고량 줄이기
		for(int i=0;i<optionNos.length;i++) {
			Integer count = optionDao.selectCnt(optionNos[i]);
			
			// 비 재고상품이 아닐 때
			if(count != -1) {
				Map<String, Object> map = new HashMap<String, Object>();
				map.put("no", optionNos[i]);
				map.put("cnt", optionCnts[i]);
				Integer result = 0;
				result = optionDao.updateCnt(map);
				
				if(result != 1) {
					// Exception을 발생시켜 롤백시키기
					System.out.println("eeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeee!!!");
					throw new Exception();
				}
			}
		}
		
		return true;
	}


	// 금액계산
	@Override
	public Long moneySum(Long[] optionNos, Integer[] optionCnts) {
		Long moneySum = 0L;
		for(int i=0;i<optionNos.length;i++) {
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("no", optionNos[i]);
			map.put("cnt", optionCnts[i]);
			moneySum += optionDao.selectSumMoney(map);
		}
		
		return moneySum;
	}
	
	
	// 구매한 수량만큼 재고량 복구
	@Override
	public boolean restoreCnt(List<OrdersItemVo> ordersItemList) {
		for(OrdersItemVo ordersItemVo : ordersItemList) {
			// 옵션이 현재 삭제된(null) 옵션이라면 지나감
			if(ordersItemVo.getOptionNo() == null) continue;
			optionDao.updateRestore(ordersItemVo);
		}
		
		return true;
	}


	// 존재하는 옵션인지 확인
	@Override
	public boolean isExistOption(Long optionNo) {
		Integer count = optionDao.countByNo(optionNo);
		return count != 0;
	}


	// 옵션의 재고가 수량만큼 존재하는지 확인
	@Override
	public boolean isExistCnt(Long optionNo, Long cnt) {
		Integer count = optionDao.selectCnt(optionNo);
		
		// 비 재고상품이 아닐 때
		if(count != -1) if(count < cnt) return false;
		return true;
	}


	// 판매중인 상품인지 확인
	@Override
	public boolean isOnSaleAll(Long[] optionNos) {
		if(optionNos.length == 0) return false;
		for(Long optionNo : optionNos) {
			String display = optionDao.getItemDisplay(optionNo);
			if("FALSE".equals(display)) return false;
		}
		
		return true;
	}


}
