package com.cafe24.mhmall.dto;

import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;

import com.cafe24.mhmall.vo.MemberVo;

public class RequestMemberJoinDto {
	@NotEmpty(message = "시작은 영문으로만, '_'를 제외한 특수문자 안되며 영문, 숫자, '_'으로만 이루어진 5 ~ 12자 이하")
	@Pattern(regexp = "^[a-zA-Z]{1}[a-zA-Z0-9_]{4,11}$")
	private String id;
	@NotEmpty(message = "최소 8자리에 숫자, 문자, 특수문자 각각 1개 이상 포함")
	@Pattern(regexp = "^(?=.*[A-Za-z])(?=.*\\d)(?=.*[$@$!%*#?&])[A-Za-z\\d$@$!%*#?&]{8,}$")
	private String password;
	@NotEmpty
	@Length(min=2, max=5)
	private String name;
	@NotEmpty
	@Pattern(regexp = "^\\d{2,3}\\d{3,4}\\d{4}$")
	private String phone;
	@Pattern(regexp = "^[_a-zA-Z0-9-\\.]+@[\\.a-zA-Z0-9-]+\\.[a-zA-Z]+$")
	private String email;
	@NotEmpty
	private String zipcode;
	@NotEmpty
	private String addr;
	
	public MemberVo toVo() {
		return new MemberVo(id, password, name, phone, email, zipcode, addr, null, null, null);
	}
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getZipcode() {
		return zipcode;
	}
	public void setZipcode(String zipcode) {
		this.zipcode = zipcode;
	}
	public String getAddr() {
		return addr;
	}
	public void setAddr(String addr) {
		this.addr = addr;
	}

}
