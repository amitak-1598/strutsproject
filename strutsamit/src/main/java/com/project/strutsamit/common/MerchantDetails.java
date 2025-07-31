package com.project.strutsamit.common;

import com.project.strutsamit.util.UserStatusType;

public class MerchantDetails {

	public MerchantDetails() {

	}

	public MerchantDetails(String appId, String resellerId, String businessName, String emailId, UserStatusType status,
			String mobile, String registrationDate, String userType, String businessType) {
		this.setAppId(appId);
		this.resellerId = resellerId;
		this.setBusinessName(businessName);
		this.emailId = emailId;
		this.mobile = mobile;
		this.registrationDate = registrationDate;
		this.status = status;
		this.userType = userType;
		this.businessType = businessType;
	}

	private String appId;
	private String resellerId;
	private String businessName;
	private String emailId;
	private String mobile;
	private String registrationDate;
	private UserStatusType status;
	private String userType;
	private String businessType;

	public UserStatusType getStatus() {
		return status;
	}

	public void setStatus(UserStatusType status) {
		this.status = status;
	}

	public String getBusinessName() {
		return businessName;
	}

	public void setBusinessName(String businessName) {
		this.businessName = businessName;
	}

	public String getEmailId() {
		return emailId;
	}

	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getRegistrationDate() {
		return registrationDate;
	}

	public void setRegistrationDate(String registrationDate) {
		this.registrationDate = registrationDate;
	}

	public String getUserType() {
		return userType;
	}

	public void setUserType(String userType) {
		this.userType = userType;
	}

	public String getResellerId() {
		return resellerId;
	}

	public void setResellerId(String resellerId) {
		this.resellerId = resellerId;
	}

	public String getBusinessType() {
		return businessType;
	}

	public void setBusinessType(String businessType) {
		this.businessType = businessType;
	}

	public String getAppId() {
		return appId;
	}

	public void setAppId(String appId) {
		this.appId = appId;
	}

}
