package com.cafe24.mhshop.vo;

import org.hibernate.validator.constraints.Length;

public class CategoryVo {
	private Long no;
	@Length(min=1, max=20)
	private String name;
	
	public CategoryVo() {}
	public CategoryVo(Long no, String name) {
		this.no = no;
		this.name = name;
	}
	public Long getNo() {
		return no;
	}
	public void setNo(Long no) {
		this.no = no;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
}
