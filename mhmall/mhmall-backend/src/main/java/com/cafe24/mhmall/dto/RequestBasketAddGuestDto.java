package com.cafe24.mhmall.dto;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotEmpty;

import com.cafe24.mhmall.vo.BasketVo;

public class RequestBasketAddGuestDto {
	@NotNull
	private Long optionNo;
	@NotEmpty
	private String guestSession;
	@NotNull
	@Min(1)
	private Long cnt;

	public BasketVo toVo() {
		return new BasketVo(null, optionNo, null, guestSession, null, cnt, null, null, null, null, null);
	}
	public Long getOptionNo() {
		return optionNo;
	}
	public void setOptionNo(Long optionNo) {
		this.optionNo = optionNo;
	}

	public String getGuestSession() {
		return guestSession;
	}
	public void setGuestSession(String guestSession) {
		this.guestSession = guestSession;
	}
	public Long getCnt() {
		return cnt;
	}
	public void setCnt(Long cnt) {
		this.cnt = cnt;
	}

	
}
