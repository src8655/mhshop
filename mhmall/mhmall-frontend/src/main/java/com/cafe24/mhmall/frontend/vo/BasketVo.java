package com.cafe24.mhmall.frontend.vo;

public class BasketVo {
	private Long no;
	private Long optionNo;
	private String memberId;
	private String guestSession;
	private String regDate;
	private Long cnt;

	private Long itemNo;
	private String optionNames;
	private String itemName;
	private String thumbnail;
	private Long money;

	private String aesKey;
	

	public BasketVo() {}
	public BasketVo(Long no, Long optionNo, String memberId, String guestSession, String regDate, Long cnt, Long itemNo,
			String optionNames, String itemName, String thumbnail, Long money) {
		this.no = no;
		this.optionNo = optionNo;
		this.memberId = memberId;
		this.guestSession = guestSession;
		this.regDate = regDate;
		this.cnt = cnt;
		this.itemNo = itemNo;
		this.optionNames = optionNames;
		this.itemName = itemName;
		this.thumbnail = thumbnail;
		this.money = money;
	}


	public Long getMoney() {
		return money;
	}
	public void setMoney(Long money) {
		this.money = money;
	}
	public Long getItemNo() {
		return itemNo;
	}

	public void setItemNo(Long itemNo) {
		this.itemNo = itemNo;
	}

	public String getOptionNames() {
		return optionNames;
	}

	public void setOptionNames(String optionNames) {
		this.optionNames = optionNames;
	}

	public String getItemName() {
		return itemName;
	}

	public void setItemName(String itemName) {
		this.itemName = itemName;
	}

	public String getThumbnail() {
		return thumbnail;
	}

	public void setThumbnail(String thumbnail) {
		this.thumbnail = thumbnail;
	}

	public Long getNo() {
		return no;
	}
	public void setNo(Long no) {
		this.no = no;
	}
	public Long getOptionNo() {
		return optionNo;
	}
	public void setOptionNo(Long optionNo) {
		this.optionNo = optionNo;
	}
	public String getMemberId() {
		return memberId;
	}
	public void setMemberId(String memberId) {
		this.memberId = memberId;
	}
	public String getRegDate() {
		return regDate;
	}
	public void setRegDate(String regDate) {
		this.regDate = regDate;
	}
	public Long getCnt() {
		return cnt;
	}
	public void setCnt(Long cnt) {
		this.cnt = cnt;
	}
	public String getGuestSession() {
		return guestSession;
	}
	public void setGuestSession(String guestSession) {
		this.guestSession = guestSession;
	}
	public String getAesKey() {
		return aesKey;
	}
	public void setAesKey(String aesKey) {
		this.aesKey = aesKey;
	}
	
	
	
}
