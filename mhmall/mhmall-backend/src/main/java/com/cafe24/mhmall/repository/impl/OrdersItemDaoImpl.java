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
	

	// 초과된 주문의 번호에 해당하는 옵션번호와 수량 리스트를 받음
	@Override
	public List<OrdersItemVo> getTimeOverList(Long ordersTime) {
		return sqlSession.selectList("ordersitem.getTimeOverList", ordersTime);
	}
	
}
