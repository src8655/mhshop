package com.cafe24.mhmall.dto;

import org.hibernate.validator.constraints.Length;

import com.cafe24.mhmall.vo.CategoryVo;

public class RequestCategoryWriteDto {
	@Length(min=1, max=20, message = "길이는 1이상 20이하만 가능합니다.")
	private String name;

	public CategoryVo toVo() {
		return new CategoryVo(null, name);
	}
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	
}
