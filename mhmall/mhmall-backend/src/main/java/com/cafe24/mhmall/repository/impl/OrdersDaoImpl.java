package com.cafe24.mhmall.repository.impl;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.cafe24.mhmall.repository.OrdersDao;
import com.cafe24.mhmall.vo.GuestVo;
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


	// 주문작성
	@Override
	public String insert(OrdersVo ordersVo) {
		return (String)sqlSession.selectOne("orders.insert", ordersVo);
	}


	// 존재하는 주문이고 상태가 "주문대기"인지 확인
	@Override
	public Integer isExistAndValid(GuestVo vo) {
		return (Integer)sqlSession.selectOne("orders.isExistAndValid", vo);
	}


	// 주문에 받는사람 정보를 변경하고 상태를 "입금대기"로 변경
	@Override
	public Integer orderUpdate(OrdersVo vo) {
		vo.setAesKey(aesKey);
		return sqlSession.update("orders.orderUpdate", vo);
	}


	// 존재하는 주문이고 상태가 "주문대기"인지 확인(회원)
	@Override
	public Integer isExistAndValidMember(OrdersVo ordersVo) {
		ordersVo.setAesKey(aesKey);
		return (Integer)sqlSession.selectOne("orders.isExistAndValidMember", ordersVo);
	}


	// 존재하고 주문대기 상태가 아닌 것이 존재하는지
	@Override
	public Integer isExistAndEnable(GuestVo vo) {
		return (Integer)sqlSession.selectOne("orders.isExistAndEnable", vo);
	}
	
	

}
