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
	public List<OptionVo> selectList(Long itemNo) {
		return sqlSession.selectList("option.selectList", itemNo);
	}

	
	
	
	
	
}
