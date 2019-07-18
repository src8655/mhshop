package com.cafe24.mhshop.dto;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.NotEmpty;

import com.cafe24.mhshop.vo.ItemVo;

public class RequestItemDisplayDto {

	@NotNull
	private Long no;
	@NotEmpty
	@Pattern(regexp = "TRUE|FALSE")
	private String display;
	
	public ItemVo toVo() {
		return new ItemVo(no, null, null, null, null, display, null, null);
	}
	public Long getNo() {
		return no;
	}
	public void setNo(Long no) {
		this.no = no;
	}
	public String getDisplay() {
		return display;
	}
	public void setDisplay(String display) {
		this.display = display;
	}
	
	
}
