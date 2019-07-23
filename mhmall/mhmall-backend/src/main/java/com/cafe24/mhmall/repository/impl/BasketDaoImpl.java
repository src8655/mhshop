package com.cafe24.mhmall.repository.impl;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.cafe24.mhmall.repository.BasketDao;
import com.cafe24.mhmall.vo.BasketVo;

@Repository
public class BasketDaoImpl implements BasketDao {

	@Autowired
	SqlSession sqlSession;

	

	// 수량보다 재고가 없는 리스트 받기
	@Override
	public List<BasketVo> getGuestListByCnt(String guestSession) {
		return sqlSession.selectList("basket.getGuestListByCnt", guestSession);
	}
	

	// 삭제
	@Override
	public Integer deleteByNo(Long no) {
		return sqlSession.delete("basket.deleteByNo", no);
	}
	

	// 입력 시간을 현재로 갱신
	@Override
	public Integer guestNewTime(String guestSession) {
		return sqlSession.update("basket.guestNewTime", guestSession);
	}


	// 장바구니 리스트
	@Override
	public List<BasketVo> getListByGuest(String guestSession) {
		return sqlSession.selectList("basket.getListByGuest", guestSession);
	}



	
	
}
