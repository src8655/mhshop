package com.cafe24.mhmall.frontend.security;

import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

public class SecurityUser implements UserDetails {
	
	private static final long serialVersionUID = 1L;

	private String mockToken;
	private String name;
	private String zipcode;
	private String addr;
	private String phone;
	
	private String phone1;
	private String phone2;
	private String phone3;

	private String username;
	private String password;
	private Collection<? extends GrantedAuthority> authorities;
	
	// protected String salt;
//	private boolean enabled;
//	private boolean accountNonExpired;
//	private boolean credentialsNonExpired;
//	private boolean accountNonLocked;
	


	public String getName() {
		return name;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
		
		// 000-0000-0000
		if(phone.length() == 11) {
			phone1 = phone.substring(0, 3);
			phone2 = phone.substring(3, 7);
			phone3 = phone.substring(7, 11);
		}
		
		// 000-000-0000
		if(phone.length() == 10) {
			phone1 = phone.substring(0, 3);
			phone2 = phone.substring(3, 6);
			phone3 = phone.substring(6, 10);
		}
	}

	public String getPhone1() {
		return phone1;
	}

	public void setPhone1(String phone1) {
		this.phone1 = phone1;
	}

	public String getPhone2() {
		return phone2;
	}

	public void setPhone2(String phone2) {
		this.phone2 = phone2;
	}

	public String getPhone3() {
		return phone3;
	}

	public void setPhone3(String phone3) {
		this.phone3 = phone3;
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

	public String getMockToken() {
		return mockToken;
	}

	public void setMockToken(String mockToken) {
		this.mockToken = mockToken;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setAuthorities(Collection<? extends GrantedAuthority> authorities) {
		this.authorities = authorities;
	}
	
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return authorities;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Override
	public String getPassword() {
		return password;
	}

	public void setUsername(String username) {
		this.username = username;
	}
	
	@Override
	public String getUsername() {
		return username;
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return true;
	}

	@Override
	public String toString() {
		return "SecurityUser [mockToken=" + mockToken + ", name=" + name + ", zipcode=" + zipcode + ", addr=" + addr
				+ ", phone=" + phone + ", username=" + username + ", password=" + password + ", authorities="
				+ authorities + "]";
	}


}
