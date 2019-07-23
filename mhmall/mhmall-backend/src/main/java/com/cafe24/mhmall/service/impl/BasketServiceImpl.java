package com.cafe24.mhmall.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cafe24.mhmall.repository.BasketDao;
import com.cafe24.mhmall.service.BasketService;
import com.cafe24.mhmall.vo.BasketVo;

@Service
public class BasketServiceImpl implements BasketService {
	
	@Autowired
	BasketDao basketDao;

	
	// 장바구니 리스트 중에 수량보다 재고가 없는 것 일괄삭제
	@Override
	public boolean guestDeleteByCnt(String guestSession) {
		// 수량보다 재고가 없는 리스트 받기
		List<BasketVo> basketList = basketDao.getGuestListByCnt(guestSession);
		
		// 삭제
		for(BasketVo basketVo : basketList) {
			Integer result = basketDao.deleteByNo(basketVo.getNo());
			if(result != 1) return false;
		}
		
		return true;
	}

	
	// 입력 시간을 현재로 갱신(비회원의 장바구니는 30개월간만 유지된다)
	@Override
	public boolean guestNewTime(String guestSession) {
		Integer result = basketDao.guestNewTime(guestSession);
		return result != 0;
	}


	// 장바구니 리스트
	@Override
	public List<BasketVo> getListByGuest(String guestSession) {
		return basketDao.getListByGuest(guestSession);
	}

}
