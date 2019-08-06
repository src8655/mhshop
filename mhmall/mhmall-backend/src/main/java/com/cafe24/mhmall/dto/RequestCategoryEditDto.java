package com.cafe24.mhmall.dto;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;

import com.cafe24.mhmall.vo.CategoryVo;

public class RequestCategoryEditDto {
	@NotNull(message = "잘못된 접근입니다.")
	private Long no;
	@Length(min=1, max=20, message = "길이는 1이상 20이하만 가능합니다.")
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
