package com.cafe24.mhmall.dto;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotEmpty;

import com.cafe24.mhmall.vo.ItemVo;

public class RequestItemWriteDto {
	@NotEmpty(message = "상품명을 입력해 주세요.")
	private String name;
	private String description;
	@NotNull(message = "금액을 입력해 주세요.")
	@Min(value = 0, message = "0보다 커야합니다.")
	private Long money;
	@NotEmpty(message = "썸네일 이미지를 선택해 주세요.")
	private String thumbnail;
	@NotNull(message = "카테고리를 선택해 주세요.")
	@Min(value = 1, message = "잘못된 카테고리 입니다.")
	private Long categoryNo;
	
	public ItemVo toVo() {
		return new ItemVo(null, name, description, money, thumbnail, null, categoryNo, null, null);
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
	public Long getCategoryNo() {
		return categoryNo;
	}
	public void setCategoryNo(Long categoryNo) {
		this.categoryNo = categoryNo;
	}

	
	
}
