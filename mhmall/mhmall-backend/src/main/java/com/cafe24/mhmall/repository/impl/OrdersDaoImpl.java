package com.cafe24.mhmall.repository.impl;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.cafe24.mhmall.repository.OrdersDao;
import com.cafe24.mhmall.vo.OrdersVo;

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

	
	// 주문상세
	@Override
	public OrdersVo selectOne(OrdersVo ordersVo) {
		ordersVo.setAesKey(aesKey);
		return (OrdersVo)sqlSession.selectOne("orders.selectOne", ordersVo);
	}
	
	
	// 상태 변경
	@Override
	public Integer updateStatus(Map<String, String> map) {
		return sqlSession.update("orders.updateStatus", map);
	}


	// 운송장번호 변경
	@Override
	public Integer updateTrackingNum(Map<String, String> map) {
		return sqlSession.update("orders.updateTrackingNum", map);
	}
	
	

}
