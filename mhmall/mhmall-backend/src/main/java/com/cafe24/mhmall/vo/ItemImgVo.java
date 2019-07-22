package com.cafe24.mhmall.vo;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotEmpty;

public class ItemImgVo {
	private Long no;
	private Long itemNo;
	private String itemImg;
	
	public ItemImgVo() {}
	public ItemImgVo(Long no, Long itemNo, String itemImg) {
		this.no = no;
		this.itemNo = itemNo;
		this.itemImg = itemImg;
	}
	
	public Long getNo() {
		return no;
	}
	public void setNo(Long no) {
		this.no = no;
	}
	public Long getItemNo() {
		return itemNo;
	}
	public void setItemNo(Long itemNo) {
		this.itemNo = itemNo;
	}
	public String getItemImg() {
		return itemImg;
	}
	public void setItemImg(String itemImg) {
		this.itemImg = itemImg;
	}
	
}
