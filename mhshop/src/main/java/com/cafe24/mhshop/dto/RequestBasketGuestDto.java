package com.cafe24.mhshop.dto;

import org.hibernate.validator.constraints.NotEmpty;

import com.cafe24.mhshop.vo.BasketVo;

public class RequestBasketGuestDto {
	@NotEmpty
	private String guestSesstion;

	
	public BasketVo toVo() {
		return new BasketVo(null, null, null, guestSesstion, null, null, null, null, null, null, null);
	}
	public RequestBasketGuestDto() {}
	public RequestBasketGuestDto(String guestSesstion) {
		this.guestSesstion = guestSesstion;
	}

	public String getGuestSesstion() {
		return guestSesstion;
	}

	public void setGuestSesstion(String guestSesstion) {
		this.guestSesstion = guestSesstion;
	}
	
}
