package com.cafe24.mhshop.repository.impl;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.cafe24.mhshop.repository.OrdersItemDao;
import com.cafe24.mhshop.vo.OrdersItemVo;

@Repository
public class OrdersItemDaoImpl implements OrdersItemDao {

	@Autowired
	SqlSession sqlSession;

	@Override
	public List<OrdersItemVo> selectListByOrdersNo(String ordersNo) {
		return sqlSession.selectList("ordersitem.selectList", ordersNo);
	}
	
	
	
}
