package com.cafe24.mhmall.vo;

import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.web.bind.annotation.RequestParam;

public class MemberVo {
	private String id;
	private String password;
	private String name;
	private String phone;
	private String email;
	private String zipcode;
	private String addr;
	private String regDate;
	private String role;
	
	private String aesKey;
	private String mockToken;

	public MemberVo() {}
	
	public MemberVo(String id, String password, String name, String phone, String email, String zipcode, String addr,
			String regDate, String role, String aesKey) {
		this.id = id;
		this.password = password;
		this.name = name;
		this.phone = phone;
		this.email = email;
		this.zipcode = zipcode;
		this.addr = addr;
		this.regDate = regDate;
		this.role = role;
		this.aesKey = aesKey;
	}


	public String getMockToken() {
		return mockToken;
	}

	public void setMockToken(String mockToken) {
		this.mockToken = mockToken;
	}

	public String getAesKey() {
		return aesKey;
	}
	public void setAesKey(String aesKey) {
		this.aesKey = aesKey;
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
	public String getRegDate() {
		return regDate;
	}
	public void setRegDate(String regDate) {
		this.regDate = regDate;
	}
	public String getRole() {
		return role;
	}
	public void setRole(String role) {
		this.role = role;
	}
	
	
}
