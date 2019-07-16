package com.cafe24.mhshop.vo;

public class GuestVo {
	private String ordersNo;
	private String guestName;
	private String guestPhone;
	private String guestPassword;
	
	private String aesKey;

	public GuestVo() {}
	public GuestVo(String ordersNo, String guestName, String guestPhone, String guestPassword, String aesKey) {
		this.ordersNo = ordersNo;
		this.guestName = guestName;
		this.guestPhone = guestPhone;
		this.guestPassword = guestPassword;
		this.aesKey = aesKey;
	}

	public String getAesKey() {
		return aesKey;
	}
	public void setAesKey(String aesKey) {
		this.aesKey = aesKey;
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
