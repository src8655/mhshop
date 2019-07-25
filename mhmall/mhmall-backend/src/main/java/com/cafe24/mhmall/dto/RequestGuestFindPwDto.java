package com.cafe24.mhmall.dto;

import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;

import com.cafe24.mhmall.vo.GuestVo;

public class RequestGuestFindPwDto {
	@NotEmpty
	private String ordersNo;
	@NotEmpty
	@Length(min=2, max=5)
	private String guestName;
	@NotEmpty
	@Pattern(regexp = "^\\d{2,3}\\d{3,4}\\d{4}$")
	private String guestPhone;
	
	public GuestVo toVo() {
		return new GuestVo(ordersNo, guestName, guestPhone, null, null);
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

	
	
}
