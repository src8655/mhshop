package com.cafe24.mhmall.dto;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import com.cafe24.mhmall.vo.OptionVo;

public class RequestOptionWriteDto {
	@NotNull
	private Long itemNo;
	private Long optionDetailNo1;
	private Long optionDetailNo2;
	@NotNull
	@Min(-1)
	private Integer cnt;
	
	public OptionVo toVo() {
		return new OptionVo(null, itemNo, optionDetailNo1, optionDetailNo2, cnt, null, null);
	}
	public Long getItemNo() {
		return itemNo;
	}
	public void setItemNo(Long itemNo) {
		this.itemNo = itemNo;
	}
	public Long getOptionDetailNo1() {
		return optionDetailNo1;
	}
	public void setOptionDetailNo1(Long optionDetailNo1) {
		this.optionDetailNo1 = optionDetailNo1;
	}
	public Long getOptionDetailNo2() {
		return optionDetailNo2;
	}
	public void setOptionDetailNo2(Long optionDetailNo2) {
		this.optionDetailNo2 = optionDetailNo2;
	}
	public Integer getCnt() {
		return cnt;
	}
	public void setCnt(Integer cnt) {
		this.cnt = cnt;
	}
	

}
