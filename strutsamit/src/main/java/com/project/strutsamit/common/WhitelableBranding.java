package com.project.strutsamit.common;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import org.hibernate.annotations.Proxy;


@Entity
@Proxy(lazy = false)
public class WhitelableBranding implements Serializable {

	private static final long serialVersionUID = -113599138763110681L;
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long   id;
	private String appId;
	private String brandName;
	private String brandURL;
	private String pgURL;
	private String logoutURL;
	private String smsSenderId;
	private String emailSenderId;
	private boolean poweredByBrandingFlag;
	private boolean emailHeaderLogoFlag;
	private boolean paymentPageHeaderLogoFlag;
	private boolean brandImagesFlag;
	private boolean smsSenderIdFlag;
	private boolean senderEmailFlag;
	// reseller
	private String  senderEmail;
	private String supportEmail;
	private String supportContactNumber;
	
	private byte []   logo;
	private byte [] loginImage;
	private byte [] errorImage;
	private byte [] paymentPageImage;
	

	

	public String getBrandName() {
		return brandName;
	}

	public void setBrandName(String brandName) {
		this.brandName = brandName;
	}

	public String getBrandURL() {
		return brandURL;
	}

	public void setBrandURL(String brandURL) {
		this.brandURL = brandURL;
	}

	public String getPgURL() {
		return pgURL;
	}

	public void setPgURL(String pgURL) {
		this.pgURL = pgURL;
	}

	public String getLogoutURL() {
		return logoutURL;
	}

	public void setLogoutURL(String logoutURL) {
		this.logoutURL = logoutURL;
	}

	public String getSmsSenderId() {
		return smsSenderId;
	}

	public void setSmsSenderId(String smsSenderId) {
		this.smsSenderId = smsSenderId;
	}

	public String getEmailSenderId() {
		return emailSenderId;
	}

	public void setEmailSenderId(String emailSenderId) {
		this.emailSenderId = emailSenderId;
	}

	public byte [] getLoginImage() {
		return loginImage;
	}

	public void setLoginImage(byte [] loginImage) {
		this.loginImage = loginImage;
	}

	public byte [] getLogo() {
		return logo;
	}

	public void setLogo(byte [] logo) {
		this.logo = logo;
	}

	public boolean isPoweredByBrandingFlag() {
		return poweredByBrandingFlag;
	}

	public void setPoweredByBrandingFlag(boolean poweredByBrandingFlag) {
		this.poweredByBrandingFlag = poweredByBrandingFlag;
	}

	public boolean isEmailHeaderLogoFlag() {
		return emailHeaderLogoFlag;
	}

	public void setEmailHeaderLogoFlag(boolean emailHeaderLogoFlag) {
		this.emailHeaderLogoFlag = emailHeaderLogoFlag;
	}

	public boolean isPaymentPageHeaderLogoFlag() {
		return paymentPageHeaderLogoFlag;
	}

	public void setPaymentPageHeaderLogoFlag(boolean paymentPageHeaderLogoFlag) {
		this.paymentPageHeaderLogoFlag = paymentPageHeaderLogoFlag;
	}

	public boolean isBrandImagesFlag() {
		return brandImagesFlag;
	}

	public void setBrandImagesFlag(boolean brandImagesFlag) {
		this.brandImagesFlag = brandImagesFlag;
	}

	public boolean isSmsSenderIdFlag() {
		return smsSenderIdFlag;
	}

	public void setSmsSenderIdFlag(boolean smsSenderIdFlag) {
		this.smsSenderIdFlag = smsSenderIdFlag;
	}

	public boolean isSenderEmailFlag() {
		return senderEmailFlag;
	}

	public void setSenderEmailFlag(boolean senderEmailFlag) {
		this.senderEmailFlag = senderEmailFlag;
	}

	public String getSenderEmail() {
		return senderEmail;
	}

	public void setSenderEmail(String senderEmail) {
		this.senderEmail = senderEmail;
	}

	public String getSupportEmail() {
		return supportEmail;
	}

	public void setSupportEmail(String supportEmail) {
		this.supportEmail = supportEmail;
	}

	public String getSupportContactNumber() {
		return supportContactNumber;
	}

	public void setSupportContactNumber(String supportContactNumber) {
		this.supportContactNumber = supportContactNumber;
	}

	public byte [] getErrorImage() {
		return errorImage;
	}

	public void setErrorImage(byte [] errorImage) {
		this.errorImage = errorImage;
	}

	public byte [] getPaymentPageImage() {
		return paymentPageImage;
	}

	public void setPaymentPageImage(byte [] paymentPageImage) {
		this.paymentPageImage = paymentPageImage;
	}

	public String getAppId() {
		return appId;
	}

	public void setAppId(String appId) {
		this.appId = appId;
	}
}
