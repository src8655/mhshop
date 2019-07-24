package com.cafe24.mhmall.dto;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotEmpty;

import com.cafe24.mhmall.vo.BasketVo;

public class RequestBasketDelGuestDto {
	@NotNull
	private Long no;
	@NotEmpty
	private String guestSession;

	public BasketVo toVo() {
		return new BasketVo(no, null, null, guestSession, null, null, null, null, null, null, null);
	}
	public Long getNo() {
		return no;
	}
	public void setNo(Long no) {
		this.no = no;
	}
	public String getGuestSession() {
		return guestSession;
	}
	public void setGuestSession(String guestSession) {
		this.guestSession = guestSession;
	}
	
	
}
