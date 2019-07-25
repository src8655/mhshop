package com.cafe24.mhmall.dto;

import javax.validation.constraints.NotNull;

import com.cafe24.mhmall.vo.ItemVo;

public class RequestItemCategoryDto {
	private Long categoryNo;

	public ItemVo toVo() {
		return new ItemVo(null, null, null, null, null, null, categoryNo, null);
	}
	public Long getCategoryNo() {
		return categoryNo;
	}

	public void setCategoryNo(Long categoryNo) {
		this.categoryNo = categoryNo;
	}


}
