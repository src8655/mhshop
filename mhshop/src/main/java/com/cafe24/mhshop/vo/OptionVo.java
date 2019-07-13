package com.cafe24.mhshop.vo;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

public class OptionVo {
	private Long no;
	private Long itemNo;
	private Long optionDetailNo1;
	private Long optionDetailNo2;
	private Integer cnt;
	

	public OptionVo() {}
	public OptionVo(Long no, Long itemNo, Long optionDetailNo1, Long optionDetailNo2, Integer cnt) {
		this.no = no;
		this.itemNo = itemNo;
		this.optionDetailNo1 = optionDetailNo1;
		this.optionDetailNo2 = optionDetailNo2;
		this.cnt = cnt;
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
