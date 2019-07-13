package com.cafe24.mhshop.dto;

import org.hibernate.validator.constraints.Length;

import com.cafe24.mhshop.vo.CategoryVo;

public class RequestCategoryWriteDto {
	@Length(min=1, max=20)
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
