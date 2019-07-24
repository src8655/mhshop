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

	// AES키 관리
	private final String aesKey = "mhshop_key";
	

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


	// 현재 장바구니에 같은 옵션 삭제
	@Override
	public Integer deleteByOptionGuest(BasketVo vo) {
		return sqlSession.delete("basket.deleteByOptionGuest", vo);
	}


	// 비회원 장바구니 추가
	@Override
	public Integer insertGuest(BasketVo vo) {
		return sqlSession.insert("basket.insertGuest", vo);
	}


	// 비회원 장바구니 삭제
	@Override
	public Integer deleteGuestByNo(BasketVo vo) {
		return sqlSession.insert("basket.deleteGuestByNo", vo);
	}


	// 비회원 장바구니 정보가 존재하는지 확인하고 가져오기
	@Override
	public BasketVo getByNoGuest(BasketVo vo) {
		return (BasketVo)sqlSession.selectOne("basket.selectByNoGuest", vo);
	}


	// 장바구니 수정
	@Override
	public Integer updateCnt(BasketVo basketVo) {
		return sqlSession.update("basket.updateCnt", basketVo);
	}


	// 수량보다 재고가 없는 리스트 받기(회원)
	@Override
	public List<BasketVo> getMemberListByCnt(BasketVo vo) {
		vo.setAesKey(aesKey);
		return sqlSession.selectList("basket.getMemberListByCnt", vo);
	}


	// 회원 장바구니 리스트
	@Override
	public List<BasketVo> getListByMember(BasketVo vo) {
		vo.setAesKey(aesKey);
		return sqlSession.selectList("basket.getListByMember", vo);
	}

	
}
