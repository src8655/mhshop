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
	
	
	// 가짜DB
	private List<OptionDetailVo> getOptionDetailTable() {
		List<OptionDetailVo> optionDetailTable = new ArrayList<OptionDetailVo>();
		optionDetailTable.add(new OptionDetailVo(1L, "파란색", 1L, 1L));
		optionDetailTable.add(new OptionDetailVo(2L, "L", 2L, 1L));
		optionDetailTable.add(new OptionDetailVo(3L, "XL", 2L, 1L));
		optionDetailTable.add(new OptionDetailVo(4L, "XXL", 2L, 1L));
		
		return optionDetailTable;
	}
	
	
	
	


	// 상품번호에 속하고 레벨에 따른 상세옵션 리스트
	@Override
	public List<OptionDetailVo> getListByItemNoAndLevel(Long itemNo, Long level) {

		// DAO에 요청
		
		
		// 가짜
		List<OptionDetailVo> optionDetailTable = getOptionDetailTable();
		List<OptionDetailVo> newOptionDetailList = new ArrayList<OptionDetailVo>();
		for(OptionDetailVo vo : optionDetailTable) if(vo.getItemNo() == itemNo && vo.getLevel() == level) newOptionDetailList.add(vo);
		if(newOptionDetailList.size() == 0) return null;
		return newOptionDetailList;
	}

	
	// 상세옵션 추가
	@Override
	public boolean add(OptionDetailVo optionDetailVo) {

		// DAO에 요청
		
		return true;
	}


	// 상세옵션 삭제
	@Override
	public boolean delete(Long no) {

		// DAO에 요청
		
		return true;
	}

}
