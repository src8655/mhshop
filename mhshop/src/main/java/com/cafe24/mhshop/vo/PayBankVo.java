package com.cafe24.mhshop.vo;

public class PayBankVo {
	private String ordersNo;
	private String bankName;
	private String bankNumber;
	private String payDate;

	public PayBankVo() {}
	public PayBankVo(String ordersNo, String bankName, String bankNumber, String payDate) {
		this.ordersNo = ordersNo;
		this.bankName = bankName;
		this.bankNumber = bankNumber;
		this.payDate = payDate;
	}
	public String getOrdersNo() {
		return ordersNo;
	}
	public void setOrdersNo(String ordersNo) {
		this.ordersNo = ordersNo;
	}
	public String getBankName() {
		return bankName;
	}
	public void setBankName(String bankName) {
		this.bankName = bankName;
	}
	public String getBankNumber() {
		return bankNumber;
	}
	public void setBankNumber(String bankNumber) {
		this.bankNumber = bankNumber;
	}
	public String getPayDate() {
		return payDate;
	}
	public void setPayDate(String payDate) {
		this.payDate = payDate;
	}

}
