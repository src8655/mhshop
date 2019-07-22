package com.cafe24.mhmall.dto;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotEmpty;

import com.cafe24.mhmall.vo.ItemImgVo;

public class RequestItemImgWriteDto {
	@NotNull
	private Long itemNo;
	@NotEmpty
	private String itemImg;
	
	public ItemImgVo toVo() {
		return new ItemImgVo(null, itemNo, itemImg);
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
