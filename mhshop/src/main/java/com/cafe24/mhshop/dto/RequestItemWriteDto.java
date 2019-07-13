package com.cafe24.mhshop.dto;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotEmpty;

import com.cafe24.mhshop.vo.ItemVo;

public class RequestItemWriteDto {
	@NotEmpty
	private String name;
	private String description;
	@NotNull
	@Min(0)
	private Long money;
	@NotEmpty
	private String thumbnail;
	@NotNull
	@Min(1)
	private Long categoryNo;
	
	public ItemVo toVo() {
		return new ItemVo(null, name, description, money, thumbnail, null, categoryNo, null);
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
