package com.cafe24.mhmall.vo;

public class OrdersVo {
	private Long no;
	private String orders_no;
	private String ordersNo;
	private String regDate;
	private String status;
	private String bankName;
	private String bankNum;
	private String payDate;
	private Long money;
	private String trackingNum;
	private String toName;
	private String toPhone;
	private String toZipcode;
	private String toAddr;
	private String memberId;
	
	private String aesKey;

	public OrdersVo() {}
	

	public OrdersVo(String ordersNo, String regDate, String status, String bankName, String bankNum, String payDate,
			Long money, String trackingNum, String toName, String toPhone, String toZipcode, String toAddr,
			String memberId, String aesKey) {
		this.ordersNo = ordersNo;
		this.regDate = regDate;
		this.status = status;
		this.bankName = bankName;
		this.bankNum = bankNum;
		this.payDate = payDate;
		this.money = money;
		this.trackingNum = trackingNum;
		this.toName = toName;
		this.toPhone = toPhone;
		this.toZipcode = toZipcode;
		this.toAddr = toAddr;
		this.memberId = memberId;
		this.aesKey = aesKey;
	}
	

	public String getOrders_no() {
		return orders_no;
	}


	public void setOrders_no(String orders_no) {
		this.orders_no = orders_no;
	}


	public Long getNo() {
		return no;
	}
	public void setNo(Long no) {
		this.no = no;
	}
	public String getBankNum() {
		return bankNum;
	}
	public void setBankNum(String bankNum) {
		this.bankNum = bankNum;
	}
	public String getAesKey() {
		return aesKey;
	}
	public void setAesKey(String aesKey) {
		this.aesKey = aesKey;
	}
	public String getBankName() {
		return bankName;
	}
	public void setBankName(String bankName) {
		this.bankName = bankName;
	}
	public String getPayDate() {
		return payDate;
	}
	public void setPayDate(String payDate) {
		this.payDate = payDate;
	}
	public String getMemberId() {
		return memberId;
	}
	public void setMemberId(String memberId) {
		this.memberId = memberId;
	}
	public String getOrdersNo() {
		return ordersNo;
	}
	public void setOrdersNo(String ordersNo) {
		this.ordersNo = ordersNo;
	}
	public String getRegDate() {
		return regDate;
	}
	public void setRegDate(String regDate) {
		this.regDate = regDate;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public Long getMoney() {
		return money;
	}
	public void setMoney(Long money) {
		this.money = money;
	}
	public String getTrackingNum() {
		return trackingNum;
	}
	public void setTrackingNum(String trackingNum) {
		this.trackingNum = trackingNum;
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

}
