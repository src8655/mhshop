package com.cafe24.mhshop.dto;

import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.NotEmpty;

import com.cafe24.mhshop.vo.MemberVo;

public class RequestMemberIdDto {
	@NotEmpty(message = "시작은 영문으로만, '_'를 제외한 특수문자 안되며 영문, 숫자, '_'으로만 이루어진 5 ~ 12자 이하")
	@Pattern(regexp = "^[a-zA-Z]{1}[a-zA-Z0-9_]{4,11}$")
	private String id;
	public MemberVo toVo() {
		return new MemberVo(id, null, null, null, null, null, null, null, null, null);
	}
	
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}
	
}
