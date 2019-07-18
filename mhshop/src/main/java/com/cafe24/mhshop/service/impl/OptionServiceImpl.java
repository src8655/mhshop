package com.cafe24.mhshop.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cafe24.mhshop.repository.OptionDao;
import com.cafe24.mhshop.service.OptionService;
import com.cafe24.mhshop.vo.OptionVo;

@Service
public class OptionServiceImpl implements OptionService {
	
	@Autowired
	OptionDao optionDao;
	
	
	

	// 상품번호에 속한 옵션 리스트
	@Override
	public List<OptionVo> getListByItemNo(Long itemNo) {
		return optionDao.selectList(itemNo);
	}


	// 상세옵션번호를 가지는 옵션이 있는지 확인
	@Override
	public boolean hasOptionDetailNo(Long optionDetailNo) {

		return false;
	}


	// 옵션추가
	@Override
	public boolean add(OptionVo optionVo) {

		// DAO에 요청
		
		return true;
	}

	
	// 옵션 삭제
	@Override
	public boolean delete(Long no) {

		// DAO에 요청
		
		return true;
	}

}
