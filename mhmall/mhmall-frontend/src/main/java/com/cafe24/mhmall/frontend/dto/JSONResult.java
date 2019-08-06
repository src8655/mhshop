package com.cafe24.mhmall.frontend.dto;


public class JSONResult {
	private String result;		// success, fail
	private String message;		// if fail, message set
	private Object data;		// if success, data set
	
	
	public static JSONResult success(Object data) {
		return new JSONResult("success", null, data);
	}
	public static JSONResult fail(String message) {
		return new JSONResult("fail", message, null);
	}
	
	private JSONResult(String result, String message, Object data) {
		this.result = result;
		this.message = message;
		this.data = data;
	}
	public String getResult() {
		return result;
	}
	public String getMessage() {
		return message;
	}
	public Object getData() {
		return data;
	}
	public void setResult(String result) {
		this.result = result;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public void setData(Object data) {
		this.data = data;
	}


	

}
