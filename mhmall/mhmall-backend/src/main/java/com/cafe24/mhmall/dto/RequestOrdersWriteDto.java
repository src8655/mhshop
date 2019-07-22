package com.cafe24.mhmall.dto;

import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;

import com.cafe24.mhmall.vo.OrdersVo;

public class RequestOrdersWriteDto {
	@NotEmpty
	@Length(min=2, max=5)
	private String toName;
	@NotEmpty
	@Pattern(regexp = "^\\d{2,3}\\d{3,4}\\d{4}$")
	private String toPhone;
	@NotEmpty
	private String toZipcode;
	@NotEmpty
	private String toAddr;
	
	
	public OrdersVo toVo() {
		return new OrdersVo(null, null, null, null, null, null, null, null, toName, toPhone, toZipcode, toAddr, null, null);
	}
	public RequestOrdersWriteDto() {}
	public RequestOrdersWriteDto(String toName, String toPhone, String toZipcode, String toAddr) {
		this.toName = toName;
		this.toPhone = toPhone;
		this.toZipcode = toZipcode;
		this.toAddr = toAddr;
	}
	public String getToName() {
		return toName;
	}
	public void setToName(String toName) {
		this.toName = toName;
	}
	public String getToPhone() {
		return toPhone;
	}
	public void setToPhone(String toPhone) {
		this.toPhone = toPhone;
	}
	public String getToZipcode() {
		return toZipcode;
	}
	public void setToZipcode(String toZipcode) {
		this.toZipcode = toZipcode;
	}
	public String getToAddr() {
		return toAddr;
	}
	public void setToAddr(String toAddr) {
		this.toAddr = toAddr;
	}
	
	
}
