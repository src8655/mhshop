package com.cafe24.mhshop.repository.impl;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.cafe24.mhshop.repository.ItemDao;
import com.cafe24.mhshop.vo.ItemVo;

@Repository
public class ItemDaoImpl implements ItemDao {
	
	@Autowired
	SqlSession sqlSession;

	
	// 카테고리번호에 해당하는 아이템 개수
	@Override
	public Integer countByCategory(Long categoryNo) {
		return (Integer)sqlSession.selectOne("item.countByCategory", categoryNo);
	}

	
	// 상품 리스트
	@Override
	public List<ItemVo> selectList() {
		return sqlSession.selectList("item.selectList");
	}


	// 상품 등록
	@Override
	public Integer insert(ItemVo itemVo) {
		return sqlSession.insert("item.insert", itemVo);
	}


	// 상품 삭제
	@Override
	public Integer delete(Long no) {
		return sqlSession.delete("item.delete", no);
	}

}
