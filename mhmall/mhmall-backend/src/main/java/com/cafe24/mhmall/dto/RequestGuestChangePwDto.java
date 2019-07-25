package com.cafe24.mhmall.dto;

import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;

import com.cafe24.mhmall.vo.GuestVo;

public class RequestGuestChangePwDto {
	@NotEmpty
	private String ordersNo;
	@NotEmpty
	@Length(min=2, max=5)
	private String guestName;
	@NotEmpty
	@Pattern(regexp = "^\\d{2,3}\\d{3,4}\\d{4}$")
	private String guestPhone;
	@NotEmpty(message = "최소 8자리에 숫자, 문자, 특수문자 각각 1개 이상 포함")
	@Pattern(regexp = "^(?=.*[A-Za-z])(?=.*\\d)(?=.*[$@$!%*#?&])[A-Za-z\\d$@$!%*#?&]{8,}$")
	private String guestPassword;
	
	public GuestVo toVo() {
		return new GuestVo(ordersNo, guestName, guestPhone, guestPassword, null);
	}
	public String getOrdersNo() {
		return ordersNo;
	}
	public void setOrdersNo(String ordersNo) {
		this.ordersNo = ordersNo;
	}
	public String getGuestName() {
		return guestName;
	}
	public void setGuestName(String guestName) {
		this.guestName = guestName;
	}
	public String getGuestPhone() {
		return guestPhone;
	}
	public void setGuestPhone(String guestPhone) {
		this.guestPhone = guestPhone;
	}
	public String getGuestPassword() {
		return guestPassword;
	}
	public void setGuestPassword(String guestPassword) {
		this.guestPassword = guestPassword;
	}
	
}
