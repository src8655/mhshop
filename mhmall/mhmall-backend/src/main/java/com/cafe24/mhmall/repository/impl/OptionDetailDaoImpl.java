package com.cafe24.mhmall.repository.impl;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.cafe24.mhmall.repository.OptionDetailDao;
import com.cafe24.mhmall.vo.OptionDetailVo;

@Repository
public class OptionDetailDaoImpl implements OptionDetailDao {

	@Autowired
	SqlSession sqlSession;

	
	// 상품번호에 속하고 레벨에 따른 상세옵션 리스트
	@Override
	public List<OptionDetailVo> selectList(OptionDetailVo optionDetailVo) {
		return sqlSession.selectList("optiondetail.selectList", optionDetailVo);
	}


	// 상세옵션 추가
	@Override
	public Integer insert(OptionDetailVo optionDetailVo) {
		return sqlSession.insert("optiondetail.insert", optionDetailVo);
	}


	// 상세옵션 삭제
	@Override
	public Integer delete(Long no) {
		return sqlSession.delete("optiondetail.delete", no);
	}


	// 존재하는 상세옵션인지 확인
	@Override
	public Integer count(Long no) {
		return (Integer)sqlSession.selectOne("optiondetail.count", no);
	}

	
	
	
}
