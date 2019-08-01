package com.cafe24.mhmall.dto;

import java.util.List;

import com.cafe24.mhmall.vo.OrdersItemVo;
import com.cafe24.mhmall.vo.OrdersVo;

public class ResponseOrdersMemberDto {
	private Long[] optionNos;
	private Integer[] optionCnts;
	
	public Long[] getOptionNos() {
		return optionNos;
	}
	public void setOptionNos(Long[] optionNos) {
		this.optionNos = optionNos;
	}
	public Integer[] getOptionCnts() {
		return optionCnts;
	}
	public void setOptionCnts(Integer[] optionCnts) {
		this.optionCnts = optionCnts;
	}



}
