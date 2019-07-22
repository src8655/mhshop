package com.cafe24.mhmall.dto;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import com.cafe24.mhmall.vo.OptionVo;

public class RequestOptionListDto {
	@NotNull
	private Long itemNo;
	private Long optionDetailNo1;
	
	public OptionVo toVo() {
		return new OptionVo(null, itemNo, optionDetailNo1, null, null, null, null);
	}
	public Long getItemNo() {
		return itemNo;
	}
	public void setItemNo(Long itemNo) {
		this.itemNo = itemNo;
	}
	public Long getOptionDetailNo1() {
		return optionDetailNo1;
	}
	public void setOptionDetailNo1(Long optionDetailNo1) {
		this.optionDetailNo1 = optionDetailNo1;
	}

}
