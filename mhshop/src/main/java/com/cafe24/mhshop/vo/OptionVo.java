package com.cafe24.mhshop.vo;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

public class OptionVo {
	private Long no;
	private Long itemNo;
	private Long optionDetailNo1;
	private Long optionDetailNo2;
	private Integer cnt;

	private String optionDetailName1;
	private String optionDetailName2;
	

	public OptionVo() {}

	public OptionVo(Long no, Long itemNo, Long optionDetailNo1, Long optionDetailNo2, Integer cnt,
			String optionDetailName1, String optionDetailName2) {
		this.no = no;
		this.itemNo = itemNo;
		this.optionDetailNo1 = optionDetailNo1;
		this.optionDetailNo2 = optionDetailNo2;
		this.cnt = cnt;
		this.optionDetailName1 = optionDetailName1;
		this.optionDetailName2 = optionDetailName2;
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
	public String getOptionDetailName1() {
		return optionDetailName1;
	}
	public void setOptionDetailName1(String optionDetailName1) {
		this.optionDetailName1 = optionDetailName1;
	}
	public String getOptionDetailName2() {
		return optionDetailName2;
	}
	public void setOptionDetailName2(String optionDetailName2) {
		this.optionDetailName2 = optionDetailName2;
	}

}
