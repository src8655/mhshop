package com.cafe24.mhmall.vo;

public class MainImgVo {
	private Long no;
	private String name;
	private String itemImg;

	public MainImgVo() {}
	public MainImgVo(Long no, String name, String itemImg) {
		this.no = no;
		this.name = name;
		this.itemImg = itemImg;
	}
	public Long getNo() {
		return no;
	}
	public void setNo(Long no) {
		this.no = no;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getItemImg() {
		return itemImg;
	}
	public void setItemImg(String itemImg) {
		this.itemImg = itemImg;
	}
	
	
}
