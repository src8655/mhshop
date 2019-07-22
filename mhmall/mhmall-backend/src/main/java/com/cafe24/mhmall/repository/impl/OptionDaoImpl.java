package com.cafe24.mhmall.repository.impl;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.cafe24.mhmall.repository.OptionDao;
import com.cafe24.mhmall.vo.OptionVo;

@Repository
public class OptionDaoImpl implements OptionDao {

	@Autowired
	SqlSession sqlSession;

	
	// 상품번호에 속한 옵션 리스트
	@Override
	public List<OptionVo> selectListLevel(OptionVo optionVo) {
		return sqlSession.selectList("option.selectListLevel", optionVo);
	}


	// 상세옵션번호를 가지는 옵션이 있는지 확인 요청
	@Override
	public Integer countByOptionDetailNo(Long no) {
		return (Integer)sqlSession.selectOne("option.countByOptionDetailNo", no);
	}


	// 옵션추가
	@Override
	public Integer insert(OptionVo optionVo) {
		return sqlSession.insert("option.insert", optionVo);
	}


	// 옵션 삭제
	@Override
	public Integer delete(Long no) {
		return (Integer)sqlSession.delete("option.delete", no);
	}


	// 상품번호에 속한 옵션 리스트
	@Override
	public List<OptionVo> selectList(Long no) {
		return sqlSession.selectList("option.selectList", no);
	}


	// 옵션번호로 옵션하나 받아오기
	@Override
	public OptionVo selectOne(Long no) {
		return (OptionVo)sqlSession.selectOne("option.selectOne", no);
	}


	// 옵션의 재고 가져오기
	@Override
	public Integer selectCnt(Long no) {
		return (Integer)sqlSession.selectOne("option.countCnt", no);
	}


	// 옵션번호에 해당하는 옵션개수
	@Override
	public Integer countByNo(Long no) {
		return (Integer)sqlSession.selectOne("option.countByNo", no);
	}


	// 옵션 재고량 줄이기
	@Override
	public Integer updateCnt(Map<String, Object> map) {
		return sqlSession.update("option.updateCnt", map);
	}


	// 금액계산
	@Override
	public Long selectSumMoney(Map<String, Object> map) {
		return (Long)sqlSession.selectOne("option.selectSumMoney", map);
	}
}
