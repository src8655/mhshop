package com.cafe24.mhmall.dto;

import org.hibernate.validator.constraints.NotEmpty;

import com.cafe24.mhmall.vo.BasketVo;

public class RequestBasketGuestDto {
	@NotEmpty
	private String guestSession;

	
	public BasketVo toVo() {
		return new BasketVo(null, null, null, guestSession, null, null, null, null, null, null, null);
	}
	public RequestBasketGuestDto() {}
	public RequestBasketGuestDto(String guestSession) {
		this.guestSession = guestSession;
	}
	public String getGuestSession() {
		return guestSession;
	}
	public void setGuestSession(String guestSession) {
		this.guestSession = guestSession;
	}


	
}
