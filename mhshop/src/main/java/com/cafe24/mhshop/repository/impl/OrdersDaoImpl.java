package com.cafe24.mhshop.repository.impl;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.cafe24.mhshop.repository.OrdersDao;
import com.cafe24.mhshop.vo.OrdersVo;

@Repository
public class OrdersDaoImpl implements OrdersDao {
	
	@Autowired
	SqlSession sqlSession;

	// AES키 관리
	private final String aesKey = "mhshop_key";
	
	// 주문리스트
	@Override
	public List<OrdersVo> selectList() {
		return sqlSession.selectList("orders.selectList", aesKey);
	}
	
	

}
