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


	// 현재 장바구니에 같은 옵션 삭제
	@Override
	public boolean deleteByOptionGuest(BasketVo vo) {
		Integer result = basketDao.deleteByOptionGuest(vo);
		return result != 0;
	}


	// 비회원 장바구니 추가
	@Override
	public boolean addGuest(BasketVo vo) {
		Integer result = basketDao.insertGuest(vo);
		return result != 0;
	}


	// 비회원 장바구니 삭제
	@Override
	public boolean deleteGuest(BasketVo vo) {
		Integer result = basketDao.deleteGuestByNo(vo);
		return result == 1;
	}


	// 비회원 장바구니 정보가 존재하는지 확인하고 가져오기
	@Override
	public BasketVo getByNoGuest(BasketVo vo) {
		return basketDao.getByNoGuest(vo);
	}


	// 장바구니 수정
	@Override
	public boolean updateCnt(Long no, Long cnt) {
		BasketVo basketVo = new BasketVo();
		basketVo.setNo(no);
		basketVo.setCnt(cnt);
		Integer result = basketDao.updateCnt(basketVo);
		return result != 0;
	}


	// 장바구니 리스트 중에 수량보다 재고가 없는 것 일괄삭제(회원)
	@Override
	public boolean memberDeleteByCnt(String id) {
		// 수량보다 재고가 없는 리스트 받기(회원)
		BasketVo vo = new BasketVo();
		vo.setMemberId(id);
		List<BasketVo> basketList = basketDao.getMemberListByCnt(vo);
		
		// 삭제
		for(BasketVo basketVo : basketList) {
			Integer result = basketDao.deleteByNo(basketVo.getNo());
			if(result != 1) return false;
		}
		
		return true;
	}


	// 회원 장바구니 리스트
	@Override
	public List<BasketVo> getListByMember(String id) {
		BasketVo vo = new BasketVo();
		vo.setMemberId(id);
		return basketDao.getListByMember(vo);
	}


	// 현재 장바구니에 같은 옵션 삭제(회원)
	@Override
	public boolean deleteByOptionMember(String id, Long optionNo) {
		BasketVo basketVo = new BasketVo();
		basketVo.setMemberId(id);
		basketVo.setOptionNo(optionNo);
		Integer result = basketDao.deleteByOptionMember(basketVo);
		return result != 0;
	}


	// 회원 장바구니 추가
	@Override
	public boolean addMember(BasketVo vo, String id) {
		vo.setMemberId(id);
		Integer result = basketDao.insertMember(vo);
		return result == 1;
	}


	// 회원 장바구니 삭제
	@Override
	public boolean deleteMember(Long no, String id) {
		BasketVo basketVo = new BasketVo();
		basketVo.setNo(no);
		basketVo.setMemberId(id);
		Integer result = basketDao.deleteMemberByNo(basketVo);
		return result == 1;
	}


	// 회원 장바구니 정보가 존재하는지 확인하고 가져오기
	@Override
	public BasketVo getByNoMember(BasketVo vo, String id) {
		vo.setMemberId(id);
		return basketDao.getByNoMember(vo);
	}



}
