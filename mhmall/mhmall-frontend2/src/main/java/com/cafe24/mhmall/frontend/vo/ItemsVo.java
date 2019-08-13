package com.cafe24.mhmall.frontend.vo;

import java.util.List;

public class ItemsVo {
	private List<ItemVo> itemList;
	private Paging paging;
	

	public ItemsVo() {}
	public ItemsVo(List<ItemVo> itemList, Paging paging) {
		this.itemList = itemList;
		this.paging = paging;
	}
	
	public List<ItemVo> getItemList() {
		return itemList;
	}
	public void setItemList(List<ItemVo> itemList) {
		this.itemList = itemList;
	}
	public Paging getPaging() {
		return paging;
	}
	public void setPaging(Paging paging) {
		this.paging = paging;
	}
	
}
