package com.project.strutsamit.common;

import java.io.Serializable;

public class Merchants implements Serializable {

	public Merchants(){
		
	}	
	private static final long serialVersionUID = -5829924589073475754L;
	
	private String emailId;
	private String appId;
	private String businessName;
	private String firstName;
	private String lastName;
	private String mobile;
	private Boolean isActive;

	public void setMerchant(User user){
		setEmailId(user.getEmailId());
		setBusinessName(user.getBusinessName());
		setAppId(user.getAppId());
	}
	
	public String getEmailId() {
		return emailId;
	}

	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}

	

	public String getBusinessName() {
		return businessName;
	}

	public void setBusinessName(String businessName) {
		this.businessName = businessName;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public Boolean getIsActive() {
		return isActive;
	}

	public void setIsActive(Boolean isActive) {
		this.isActive = isActive;
	}

	public String getAppId() {
		return appId;
	}

	public void setAppId(String appId) {
		this.appId = appId;
	}

	
}
