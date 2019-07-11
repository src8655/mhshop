package com.cafe24.mhshop.vo;

public class OptionDetailVo {
	private Long no;
	private String optionName;
	private Long level;
	private Long itemNo;
	

	public OptionDetailVo() {}
	public OptionDetailVo(Long no, String optionName, Long level, Long itemNo) {
		this.no = no;
		this.optionName = optionName;
		this.level = level;
		this.itemNo = itemNo;
	}
	public Long getNo() {
		return no;
	}
	public void setNo(Long no) {
		this.no = no;
	}
	public String getOptionName() {
		return optionName;
	}
	public void setOptionName(String optionName) {
		this.optionName = optionName;
	}
	public Long getLevel() {
		return level;
	}
	public void setLevel(Long level) {
		this.level = level;
	}
	public Long getItemNo() {
		return itemNo;
	}
	public void setItemNo(Long itemNo) {
		this.itemNo = itemNo;
	}

}
