package com.cafe24.mhshop.vo;

public class PayKakaoVo {
	private String ordersNo;
	private String tid;
	private String aid;
	private String payDate;

	public PayKakaoVo() {}
	public PayKakaoVo(String ordersNo, String tid, String aid, String payDate) {
		this.ordersNo = ordersNo;
		this.tid = tid;
		this.aid = aid;
		this.payDate = payDate;
	}
	public String getOrdersNo() {
		return ordersNo;
	}
	public void setOrdersNo(String ordersNo) {
		this.ordersNo = ordersNo;
	}
	public String getTid() {
		return tid;
	}
	public void setTid(String tid) {
		this.tid = tid;
	}
	public String getAid() {
		return aid;
	}
	public void setAid(String aid) {
		this.aid = aid;
	}
	public String getPayDate() {
		return payDate;
	}
	public void setPayDate(String payDate) {
		this.payDate = payDate;
	}
	
}
