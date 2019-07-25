package com.cafe24.mhmall.dto;

import javax.validation.constraints.NotNull;

import com.cafe24.mhmall.vo.ItemVo;

public class RequestItemNewListDto {
	@NotNull
	private Integer showCnt;
	private Long categoryNo;
	
	public ItemVo toVo() {
		return new ItemVo(null, null, null, null, null, null, categoryNo, null, showCnt);
	}

	public Integer getShowCnt() {
		return showCnt;
	}

	public void setShowCnt(Integer showCnt) {
		this.showCnt = showCnt;
	}

	public Long getCategoryNo() {
		return categoryNo;
	}

	public void setCategoryNo(Long categoryNo) {
		this.categoryNo = categoryNo;
	}



}
