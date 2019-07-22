package com.cafe24.mhmall.dto;

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
