package com.cafe24.mhshop.dto;

import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;

import com.cafe24.mhshop.vo.GuestVo;

public class RequestGuestOrdersViewDto {
	@NotEmpty
	private String ordersNo;
	@NotEmpty(message = "최소 8자리에 숫자, 문자, 특수문자 각각 1개 이상 포함")
	@Pattern(regexp = "^(?=.*[A-Za-z])(?=.*\\d)(?=.*[$@$!%*#?&])[A-Za-z\\d$@$!%*#?&]{8,}$")
	private String guestPassword;
	
	
	public GuestVo toVo() {
		return new GuestVo(ordersNo, null, null, guestPassword, null);
	}
	public RequestGuestOrdersViewDto() {}
	public RequestGuestOrdersViewDto(String ordersNo, String guestPassword) {
		this.ordersNo = ordersNo;
		this.guestPassword = guestPassword;
	}
	public String getOrdersNo() {
		return ordersNo;
	}
	public void setOrdersNo(String ordersNo) {
		this.ordersNo = ordersNo;
	}
	public String getGuestPassword() {
		return guestPassword;
	}
	public void setGuestPassword(String guestPassword) {
		this.guestPassword = guestPassword;
	}

	
}
