package com.cafe24.mhmall.dto;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotEmpty;

import com.cafe24.mhmall.vo.BasketVo;

public class RequestBasketAddDto {
	@NotNull
	private Long[] optionNos;
	@NotNull
	private Long[] optionCnts;
	public Long[] getOptionNos() {
		return optionNos;
	}
	public void setOptionNos(Long[] optionNos) {
		this.optionNos = optionNos;
	}
	public Long[] getOptionCnts() {
		return optionCnts;
	}
	public void setOptionCnts(Long[] optionCnts) {
		this.optionCnts = optionCnts;
	}



	
}
