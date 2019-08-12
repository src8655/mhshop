package com.cafe24.mhmall.frontend.dto;

import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;

import com.cafe24.mhmall.frontend.vo.GuestVo;
import com.cafe24.mhmall.frontend.vo.OrdersVo;

public class RequestOrdersWriteGuestDto {
	@NotEmpty
	@Length(min=2, max=5)
	private String toName;
	@NotEmpty
	@Pattern(regexp = "^\\d{2,3}\\d{3,4}\\d{4}$")
	private String toPhone;
	@NotEmpty
	private String toZipcode;
	@NotEmpty
	private String toAddr;
	

	@NotEmpty
	private String ordersNo;
	@NotEmpty(message = "최소 8자리에 숫자, 문자, 특수문자 각각 1개 이상 포함")
	@Pattern(regexp = "^(?=.*[A-Za-z])(?=.*\\d)(?=.*[$@$!%*#?&])[A-Za-z\\d$@$!%*#?&]{8,}$")
	private String guestPassword;

	private String toPhone1;
	private String toPhone2;
	private String toPhone3;
	

	public OrdersVo toVo() {
		toPhone = toPhone1 + toPhone2 + toPhone3;
		return new OrdersVo(null, null, null, null, null, null, null, null, toName, toPhone, toZipcode, toAddr, null, null);
	}
	public GuestVo toVo2() {
		return new GuestVo(ordersNo, null, null, guestPassword, null);
	}
	public RequestOrdersWriteGuestDto() {}
	public RequestOrdersWriteGuestDto(String toName, String toPhone, String toZipcode, String toAddr) {
		this.toName = toName;
		this.toPhone = toPhone;
		this.toZipcode = toZipcode;
		this.toAddr = toAddr;
	}
	public String getToName() {
		return toName;
	}
	public void setToName(String toName) {
		this.toName = toName;
	}
	public String getToPhone() {
		return toPhone;
	}
	public void setToPhone(String toPhone) {
		this.toPhone = toPhone;
	}
	public String getToZipcode() {
		return toZipcode;
	}
	public void setToZipcode(String toZipcode) {
		this.toZipcode = toZipcode;
	}
	public String getToAddr() {
		return toAddr;
	}
	public void setToAddr(String toAddr) {
		this.toAddr = toAddr;
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
	public String getToPhone1() {
		return toPhone1;
	}
	public void setToPhone1(String toPhone1) {
		this.toPhone1 = toPhone1;
	}
	public String getToPhone2() {
		return toPhone2;
	}
	public void setToPhone2(String toPhone2) {
		this.toPhone2 = toPhone2;
	}
	public String getToPhone3() {
		return toPhone3;
	}
	public void setToPhone3(String toPhone3) {
		this.toPhone3 = toPhone3;
	}
	
	
}
