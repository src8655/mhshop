package com.cafe24.mhmall.frontend.dto;

import java.util.Arrays;

import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.web.bind.annotation.RequestParam;

import com.cafe24.mhmall.frontend.vo.GuestVo;


public class RequestGuestOrdersStartDto {
	private String guestName;
	private String guestPhone;
	private String guestPassword;
	

	private String phone1;
	private String phone2;
	private String phone3;
	

	private Long[] optionNos;
	private Integer[] optionCnts;
	

	public GuestVo toVo() {
		guestPhone = phone1 + phone2 + phone3;
		return new GuestVo(null, guestName, guestPhone, guestPassword, null);
	}
	
	public String getGuestName() {
		return guestName;
	}
	public void setGuestName(String guestName) {
		this.guestName = guestName;
	}
	public String getGuestPhone() {
		return guestPhone;
	}
	public void setGuestPhone(String guestPhone) {
		this.guestPhone = guestPhone;
	}
	public String getGuestPassword() {
		return guestPassword;
	}
	public void setGuestPassword(String guestPassword) {
		this.guestPassword = guestPassword;
	}
	public Long[] getOptionNos() {
		return optionNos;
	}
	public void setOptionNos(Long[] optionNos) {
		this.optionNos = optionNos;
	}
	public Integer[] getOptionCnts() {
		return optionCnts;
	}
	public void setOptionCnts(Integer[] optionCnts) {
		this.optionCnts = optionCnts;
	}

	public String getPhone1() {
		return phone1;
	}

	public void setPhone1(String phone1) {
		this.phone1 = phone1;
	}

	public String getPhone2() {
		return phone2;
	}

	public void setPhone2(String phone2) {
		this.phone2 = phone2;
	}

	public String getPhone3() {
		return phone3;
	}

	public void setPhone3(String phone3) {
		this.phone3 = phone3;
	}

	@Override
	public String toString() {
		return "RequestGuestOrdersStartDto [guestName=" + guestName + ", guestPhone=" + guestPhone + ", guestPassword="
				+ guestPassword + ", phone1=" + phone1 + ", phone2=" + phone2 + ", phone3=" + phone3 + ", optionNos="
				+ Arrays.toString(optionNos) + ", optionCnts=" + Arrays.toString(optionCnts) + "]";
	}
	
}
