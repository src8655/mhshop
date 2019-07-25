package com.cafe24.mhmall.vo;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotEmpty;

public class ItemVo {
	private Long no;
	private String name;
	private String description;
	private Long money;
	private String thumbnail;
	private String display;
	private Long categoryNo;
	
	private String categoryName;
	private Integer showCnt;

	public ItemVo() {}


	public ItemVo(Long no, String name, String description, Long money, String thumbnail, String display,
			Long categoryNo, String categoryName, Integer showCnt) {
		this.no = no;
		this.name = name;
		this.description = description;
		this.money = money;
		this.thumbnail = thumbnail;
		this.display = display;
		this.categoryNo = categoryNo;
		this.categoryName = categoryName;
		this.showCnt = showCnt;
	}


	public Integer getShowCnt() {
		return showCnt;
	}
	public void setShowCnt(Integer showCnt) {
		this.showCnt = showCnt;
	}
	public Long getNo() {
		return no;
	}

	public void setNo(Long no) {
		this.no = no;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Long getMoney() {
		return money;
	}

	public void setMoney(Long money) {
		this.money = money;
	}

	public String getThumbnail() {
		return thumbnail;
	}

	public void setThumbnail(String thumbnail) {
		this.thumbnail = thumbnail;
	}

	public String getDisplay() {
		return display;
	}

	public void setDisplay(String display) {
		this.display = display;
	}

	public Long getCategoryNo() {
		return categoryNo;
	}

	public void setCategoryNo(Long categoryNo) {
		this.categoryNo = categoryNo;
	}

	public String getCategoryName() {
		return categoryName;
	}

	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}
	
	
}
