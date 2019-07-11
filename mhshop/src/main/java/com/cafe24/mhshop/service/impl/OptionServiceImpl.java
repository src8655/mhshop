package com.cafe24.mhshop.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.cafe24.mhshop.service.OptionService;
import com.cafe24.mhshop.vo.OptionVo;

@Service
public class OptionServiceImpl implements OptionService {
	
	
	
	// 가짜DB
	private List<OptionVo> getOptionTable() {
		List<OptionVo> optionTable = new ArrayList<OptionVo>();
		optionTable.add(new OptionVo(1L, 1L, 1L, 2L, 10));
		optionTable.add(new OptionVo(2L, 1L, 1L, 3L, -1));
		
		return optionTable;
	}
	
	
	
	
	
	// 상품번호에 속한 옵션 삭제
	@Override
	public boolean deleteAllByItemNo(Long itemNo) {
		
		// DAO에 요청
		
		return true;
	}


	// 상품번호에 속한 옵션 리스트
	@Override
	public List<OptionVo> getListByItemNo(Long itemNo) {

		// DAO에 요청
		
		
		// 가짜
		List<OptionVo> optionTable = getOptionTable();
		List<OptionVo> optionList = new ArrayList<OptionVo>();
		for(OptionVo vo : optionTable) if(vo.getItemNo() == itemNo) optionList.add(vo);
		if(optionList.size() == 0) return null;
		return optionList;
	}


	// 상세옵션번호를 가지는 옵션이 있는지 확인
	@Override
	public boolean hasOptionDetailNo(Long optionDetailNo) {

		// DAO에 요청
		
		// 가짜
		List<OptionVo> optionTable = getOptionTable();
		for(OptionVo vo : optionTable)
			if(vo.getOptionDetailNo1() == optionDetailNo || vo.getOptionDetailNo2() == optionDetailNo)
				return true;
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
