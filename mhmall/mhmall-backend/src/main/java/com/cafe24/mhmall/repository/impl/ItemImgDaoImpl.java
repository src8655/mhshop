package com.cafe24.mhmall.repository.impl;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.cafe24.mhmall.repository.ItemImgDao;
import com.cafe24.mhmall.vo.ItemImgVo;

@Repository
public class ItemImgDaoImpl implements ItemImgDao {

	@Autowired
	SqlSession sqlSession;

	
	// 상품번호에 속한 상품이미지 리스트
	@Override
	public List<ItemImgVo> selectList(Long itemNo) {
		return sqlSession.selectList("itemimg.selectList", itemNo);
	}


	// 상품이미지 추가
	@Override
	public Integer insert(ItemImgVo itemImgVo) {
		return sqlSession.insert("itemimg.insert", itemImgVo);
	}


	// 상품이미지 삭제
	@Override
	public Integer delete(Long no) {
		return sqlSession.delete("itemimg.delete", no);
	}


	
	
}
