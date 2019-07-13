package com.cafe24.mhshop.dto;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotEmpty;

import com.cafe24.mhshop.vo.OptionDetailVo;

public class RequestOptionDetailWriteDto {
	@NotEmpty
	private String optionName;
	@NotNull
	@Min(1)
	@Max(2)
	private Long level;
	@NotNull
	private Long itemNo;
	
	public OptionDetailVo toVo() {
		return new OptionDetailVo(null, optionName, level, itemNo);
	}
	public String getOptionName() {
		return optionName;
	}
	public void setOptionName(String optionName) {
		this.optionName = optionName;
	}
	public Long getLevel() {
		return level;
	}
	public void setLevel(Long level) {
		this.level = level;
	}
	public Long getItemNo() {
		return itemNo;
	}
	public void setItemNo(Long itemNo) {
		this.itemNo = itemNo;
	}
	
}
