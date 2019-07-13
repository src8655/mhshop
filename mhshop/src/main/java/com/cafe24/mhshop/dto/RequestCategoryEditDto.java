package com.cafe24.mhshop.dto;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;

import com.cafe24.mhshop.vo.CategoryVo;

public class RequestCategoryEditDto {
	@NotNull
	private Long no;
	@Length(min=1, max=20)
	private String name;

	public CategoryVo toVo() {
		return new CategoryVo(no, name);
	}
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	public Long getNo() {
		return no;
	}
	public void setNo(Long no) {
		this.no = no;
	}
	
	
}
