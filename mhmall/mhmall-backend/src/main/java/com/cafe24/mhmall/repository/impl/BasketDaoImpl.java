package com.cafe24.mhmall.repository.impl;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.sleuth.Tracer;
import org.springframework.stereotype.Repository;

import com.cafe24.mhmall.repository.BasketDao;
import com.cafe24.mhmall.vo.BasketVo;

@Repository
public class BasketDaoImpl implements BasketDao {
	
	@Autowired
	private Tracer tracer;
	public void addTag(String queryId, Object parameter) {
		String query = sqlSession.getConfiguration().getMappedStatement(queryId).getSqlSource().getBoundSql(parameter).getSql();
		tracer.addTag("basket.query", query);
	}

	@Autowired
	SqlSession sqlSession;

	// AES키 관리
	private final String aesKey = "mhshop_key";
	

	// 수량보다 재고가 없는 리스트 받기
	@Override
	public List<BasketVo> getGuestListByCnt(String guestSession) {
		addTag("basket.getGuestListByCnt", guestSession);
		return sqlSession.selectList("basket.getGuestListByCnt", guestSession);
	}
	

	// 삭제
	@Override
	public Integer deleteByNo(Long no) {
		addTag("basket.deleteByNo", no);
		return sqlSession.delete("basket.deleteByNo", no);
	}
	

	// 입력 시간을 현재로 갱신
	@Override
	public Integer guestNewTime(String guestSession) {
		addTag("basket.guestNewTime", guestSession);
		return sqlSession.update("basket.guestNewTime", guestSession);
	}


	// 장바구니 리스트
	@Override
	public List<BasketVo> getListByGuest(String guestSession) {
		addTag("basket.getListByGuest", guestSession);
		return sqlSession.selectList("basket.getListByGuest", guestSession);
	}


	// 현재 장바구니에 같은 옵션 삭제
	@Override
	public Integer deleteByOptionGuest(BasketVo vo) {
		addTag("basket.deleteByOptionGuest", vo);
		return sqlSession.delete("basket.deleteByOptionGuest", vo);
	}


	// 비회원 장바구니 추가
	@Override
	public Integer insertGuest(BasketVo vo) {
		addTag("basket.insertGuest", vo);
		return sqlSession.insert("basket.insertGuest", vo);
	}


	// 비회원 장바구니 삭제
	@Override
	public Integer deleteGuestByNo(BasketVo vo) {
		addTag("basket.deleteGuestByNo", vo);
		return sqlSession.insert("basket.deleteGuestByNo", vo);
	}


	// 비회원 장바구니 정보가 존재하는지 확인하고 가져오기
	@Override
	public BasketVo getByNoGuest(BasketVo vo) {
		addTag("basket.selectByNoGuest", vo);
		return (BasketVo)sqlSession.selectOne("basket.selectByNoGuest", vo);
	}


	// 장바구니 수정
	@Override
	public Integer updateCnt(BasketVo basketVo) {
		addTag("basket.updateCnt", basketVo);
		return sqlSession.update("basket.updateCnt", basketVo);
	}


	// 수량보다 재고가 없는 리스트 받기(회원)
	@Override
	public List<BasketVo> getMemberListByCnt(BasketVo vo) {
		vo.setAesKey(aesKey);
		addTag("basket.getMemberListByCnt", vo);
		return sqlSession.selectList("basket.getMemberListByCnt", vo);
	}


	// 회원 장바구니 리스트
	@Override
	public List<BasketVo> getListByMember(BasketVo vo) {
		vo.setAesKey(aesKey);
		addTag("basket.getListByMember", vo);
		return sqlSession.selectList("basket.getListByMember", vo);
	}


	// 현재 장바구니에 같은 옵션 삭제(회원)
	@Override
	public Integer deleteByOptionMember(BasketVo basketVo) {
		basketVo.setAesKey(aesKey);
		addTag("basket.deleteByOptionMember", basketVo);
		return sqlSession.delete("basket.deleteByOptionMember", basketVo);
	}


	// 회원 장바구니 추가
	@Override
	public Integer insertMember(BasketVo vo) {
		vo.setAesKey(aesKey);
		addTag("basket.insertMember", vo);
		return sqlSession.insert("basket.insertMember", vo);
	}


	// 회원 장바구니 삭제
	@Override
	public Integer deleteMemberByNo(BasketVo basketVo) {
		basketVo.setAesKey(aesKey);
		addTag("basket.deleteMemberByNo", basketVo);
		return sqlSession.delete("basket.deleteMemberByNo", basketVo);
	}


	// 회원 장바구니 정보가 존재하는지 확인하고 가져오기
	@Override
	public BasketVo getByNoMember(BasketVo vo) {
		vo.setAesKey(aesKey);
		addTag("basket.getByNoMember", vo);
		return sqlSession.selectOne("basket.getByNoMember", vo);
	}


	// 시간이 초과된 비회원 장바구니들은 삭제
	@Override
	public Integer deleteTimeOver(Long basketTime) {
		addTag("basket.deleteTimeOver", basketTime);
		return sqlSession.delete("basket.deleteTimeOver", basketTime);
	}


	// 옵션으로 비회원 장바구니 삭제
	@Override
	public Integer deleteAllByOptionNoG(BasketVo basketVo) {
		addTag("basket.deleteAllByOptionNoG", basketVo);
		return sqlSession.delete("basket.deleteAllByOptionNoG", basketVo);
	}


	// 옵션으로 회원 장바구니 삭제
	@Override
	public Integer deleteAllByOptionNoM(BasketVo basketVo) {
		basketVo.setAesKey(aesKey);
		addTag("basket.deleteAllByOptionNoM", basketVo);
		return sqlSession.delete("basket.deleteAllByOptionNoM", basketVo);
	}

	
}
