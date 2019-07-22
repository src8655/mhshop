package com.cafe24.mhmall.repository.impl;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.cafe24.mhmall.repository.OrdersItemDao;
import com.cafe24.mhmall.vo.OrdersItemVo;

@Repository
public class OrdersItemDaoImpl implements OrdersItemDao {

	@Autowired
	SqlSession sqlSession;

	
	// 주문내역 리스트
	@Override
	public List<OrdersItemVo> selectListByOrdersNo(String ordersNo) {
		return sqlSession.selectList("ordersitem.selectList", ordersNo);
	}

	
	// 주문내역 일괄 추가
	@Override
	public Integer insert(OrdersItemVo ordersItemVo) {
		return sqlSession.insert("ordersitem.insert", ordersItemVo);
	}
	
	
	
}
