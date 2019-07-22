package com.cafe24.mhmall.dto;

import org.hibernate.validator.constraints.NotEmpty;

import com.cafe24.mhmall.vo.BasketVo;

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
