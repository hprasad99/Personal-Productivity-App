package com.cap.ppa.exception;


public class ErrorMessage {

	private String message;
	private int errorCode;
	public ErrorMessage(String message, int errorCode) {
		super();
		this.message = message;
		this.errorCode = errorCode;
	}
	public ErrorMessage() {
		super();
		
	}
	public String getErrorMessage() {
		return message;
	}
	public void setErrorMessage(String message) {
		this.message = message;
	}
	public int getErrorCode() {
		return errorCode;
	}
	public void setErrorCode(int errorCode) {
		this.errorCode = errorCode;
	}
	
	
}

