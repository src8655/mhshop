package com.cafe24.mhshop.repository.impl;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.cafe24.mhshop.repository.OptionDao;
import com.cafe24.mhshop.vo.OptionVo;

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

	
	
	
	
	
}
