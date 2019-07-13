package com.cafe24.mhshop.dto;

import javax.validation.constraints.NotNull;

public class RequestNoDto {
	@NotNull
	private Long no;

	public Long getNo() {
		return no;
	}

	public void setNo(Long no) {
		this.no = no;
	}
}
