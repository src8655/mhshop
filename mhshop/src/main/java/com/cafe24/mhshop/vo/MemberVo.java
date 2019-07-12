package com.cafe24.mhshop.vo;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.web.bind.annotation.RequestParam;

public class MemberVo {
	// 정규식 
	// (시작은 영문으로만, '_'를 제외한 특수문자 안되며 영문, 숫자, '_'으로만 이루어진 5 ~ 12자 이하)
	public static final String REGX_ID = "^[a-zA-Z]{1}[a-zA-Z0-9_]{4,11}$";
	// (최소 8자리에 숫자, 문자, 특수문자 각각 1개 이상 포함)
	public static final String REGX_PASSWORD = "^(?=.*[A-Za-z])(?=.*\\d)(?=.*[$@$!%*#?&])[A-Za-z\\d$@$!%*#?&]{8,}$";
	public static final String REGX_PHONE = "^\\d{2,3}\\d{3,4}\\d{4}$";
	public static final String REGX_EMAIL = "^[_a-zA-Z0-9-\\.]+@[\\.a-zA-Z0-9-]+\\.[a-zA-Z]+$";

	
	private String id;
	private String password;
	@NotEmpty
	@Length(min=2, max=5)
	private String name;
	@NotEmpty
	private String phone;
	private String email;
	@NotEmpty
	private String zipcode;
	@NotEmpty
	private String addr;
	private String regDate;
	private String role;
	
	private String aesKey;

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
