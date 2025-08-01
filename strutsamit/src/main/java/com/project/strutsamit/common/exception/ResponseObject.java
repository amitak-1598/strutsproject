package com.project.strutsamit.common.exception;

public class ResponseObject {

	private String responseCode;
	private String responseMessage;
	private String accountValidationID;
	private String email;
	
	public ResponseObject(){
		
	}
		
	public String getResponseCode() {
		return responseCode;
	}

	public void setResponseCode(String responseCode) {
		this.responseCode = responseCode;
	}
	
	public String getResponseMessage() {
		return responseMessage;
	}
	public void setResponseMessage(String responseMessage) {
		this.responseMessage = responseMessage;
	}

	public String getAccountValidationID() {
		return accountValidationID;
	}

	public void setAccountValidationID(String accountValidationID) {
		this.accountValidationID = accountValidationID;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}	
}
