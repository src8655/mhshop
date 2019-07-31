package com.cafe24.mhmall.dto;

import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.NotEmpty;

import com.cafe24.mhmall.vo.MemberVo;

public class RequestMemberLoginDto {
	@NotEmpty(message = "시작은 영문으로만, '_'를 제외한 특수문자 안되며 영문, 숫자, '_'으로만 이루어진 5 ~ 12자 이하")
	@Pattern(regexp = "^[a-zA-Z]{1}[a-zA-Z0-9_]{4,11}$", message = "시작은 영문으로만, '_'를 제외한 특수문자 안되며 영문, 숫자, '_'으로만 이루어진 5 ~ 12자 이하")
	private String id;
	@NotEmpty(message = "최소 8자리에 숫자, 문자, 특수문자 각각 1개 이상 포함")
	@Pattern(regexp = "^(?=.*[A-Za-z])(?=.*\\d)(?=.*[$@$!%*#?&])[A-Za-z\\d$@$!%*#?&]{8,}$", message = "최소 8자리에 숫자, 문자, 특수문자 각각 1개 이상 포함")
	private String password;
	
	public MemberVo toVo() {
		return new MemberVo(id, password, null, null, null, null, null, null, null, null);
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}
	
}
