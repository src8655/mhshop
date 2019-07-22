package com.cafe24.mhmall.vo;

public class BasketVo {
	private Long no;
	private Long optionNo;
	private String memberId;
	private String guestSesstion;
	private String regDate;
	private Long cnt;

	private Long itemNo;
	private String optionNames;
	private String itemName;
	private String thumbnail;
	private String money;
	

	public BasketVo() {}


	public BasketVo(Long no, Long optionNo, String memberId, String guestSesstion, String regDate, Long cnt,
			Long itemNo, String optionNames, String itemName, String thumbnail, String money) {
		this.no = no;
		this.optionNo = optionNo;
		this.memberId = memberId;
		this.guestSesstion = guestSesstion;
		this.regDate = regDate;
		this.cnt = cnt;
		this.itemNo = itemNo;
		this.optionNames = optionNames;
		this.itemName = itemName;
		this.thumbnail = thumbnail;
		this.money = money;
	}


	public String getMoney() {
		return money;
	}
	public void setMoney(String money) {
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
	public String getGuestSesstion() {
		return guestSesstion;
	}
	public void setGuestSesstion(String guestSesstion) {
		this.guestSesstion = guestSesstion;
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
	
	
}
