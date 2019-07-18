package com.cafe24.mhshop.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cafe24.mhshop.repository.OptionDetailDao;
import com.cafe24.mhshop.service.OptionDetailService;
import com.cafe24.mhshop.vo.OptionDetailVo;

@Service
public class OptionDetailServiceImpl implements OptionDetailService {

	@Autowired
	OptionDetailDao optionDetailDao;
		


	// 상품번호에 속하고 레벨에 따른 상세옵션 리스트
	@Override
	public List<OptionDetailVo> getListByItemNoAndLevel(Long itemNo, Long level) {
		OptionDetailVo optionDetailVo = new OptionDetailVo(null, null, level, itemNo);
		return optionDetailDao.selectList(optionDetailVo);
	}

	
	// 상세옵션 추가
	@Override
	public boolean add(OptionDetailVo optionDetailVo) {
		Integer result = optionDetailDao.insert(optionDetailVo);
		return result == 1;
	}


	// 상세옵션 삭제
	@Override
	public boolean delete(Long no) {

		// DAO에 요청
		
		return true;
	}

}
