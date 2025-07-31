package com.project.strutsamit.common;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Transient;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.Proxy;

import com.project.strutsamit.util.MopType;
import com.project.strutsamit.util.PaymentType;
import com.project.strutsamit.util.TDRStatus;



@Entity
@Proxy(lazy= false)@Cache(usage=CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class ChargingDetails implements Serializable,Comparable<ChargingDetails>{

	private static final long serialVersionUID = 3440046069273849470L;

	public ChargingDetails(){
		
	}

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;

	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}

	@Enumerated(EnumType.STRING)
	private MopType mopType;

	@Enumerated(EnumType.STRING)
	private PaymentType paymentType;

	@Enumerated(EnumType.STRING)
	private TransactionType transactionType;

	//Ceiling for fix charge
	private double fixChargeLimit;
	private boolean allowFixCharge;

	// Bank charges
	private double bankServiceTax;
	private double bankTDR;
	private double bankFixCharge;
	private double bankFixChargeAFC;
	private double bankTDRAFC;

	//Total charges taken from merchant
	private double merchantTDR;
	private double merchantFixCharge;	
	private double merchantServiceTax;
	private double merchantFixChargeAFC;
	private double merchantTDRAFC;

	//Charges by payment gateway
	private double pgTDR;
	private double pgFixCharge;	
	private double pgServiceTax;
	private double pgFixChargeAFC;
	private double pgTDRAFC;

	//User details
	private String acquirerName;
	private String appId;

	//relevent currency
	private String currency;
	
	@Transient
	private String response;
	
	@Transient
	private String merchantName;

	@Transient
	private String businessName;
	
	private Date createdDate;
	private Date updatedDate;
	private String updateBy;

	@Enumerated(EnumType.STRING)
	private TDRStatus status;

	public Date getCreatedDate() {
		return createdDate;
	}
	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}
	public Date getUpdatedDate() {
		return updatedDate;
	}
	public void setUpdatedDate(Date updatedDate) {
		this.updatedDate = updatedDate;
	}
	public TDRStatus getStatus() {
		return status;
	}
	public void setStatus(TDRStatus status) {
		this.status = status;
	}	
	public PaymentType getPaymentType() {
		return paymentType;
	}
	public void setPaymentType(PaymentType paymentType) {
		this.paymentType = paymentType;
	}	
	public String getMerchantName() {
		return merchantName;
	}
	public void setMerchantName(String merchantName) {
		this.merchantName = merchantName;
	}
	public MopType getMopType() {
		return mopType;
	}
	public void setMopType(MopType mopType) {
		this.mopType = mopType;
	}	
	public TransactionType getTransactionType() {
		return transactionType;
	}
	public void setTransactionType(TransactionType transactionType) {
		this.transactionType = transactionType;
	}		
	
	public double getMerchantFixCharge() {
		return merchantFixCharge;
	}
	public void setMerchantFixCharge(double merchantFixCharge) {
		this.merchantFixCharge = merchantFixCharge;
	}
	public double getBankTDR() {
		return bankTDR;
	}
	public void setBankTDR(double bankTDR) {
		this.bankTDR = bankTDR;
	}
	public double getBankFixCharge() {
		return bankFixCharge;
	}
	public void setBankFixCharge(double bankFixCharge) {
		this.bankFixCharge = bankFixCharge;
	}
	public double getBankServiceTax() {
		return bankServiceTax;
	}
	public void setBankServiceTax(double bankServiceTax) {
		this.bankServiceTax = bankServiceTax;
	}
	public double getMerchantTDR() {
		return merchantTDR;
	}
	public void setMerchantTDR(double merchantTDR) {
		this.merchantTDR = merchantTDR;
	}
	public double getMerchantServiceTax() {
		return merchantServiceTax;
	}
	public void setMerchantServiceTax(double merchantServiceTax) {
		this.merchantServiceTax = merchantServiceTax;
	}
	public double getPgTDR() {
		return pgTDR;
	}
	public void setPgTDR(double pgTDR) {
		this.pgTDR = pgTDR;
	}
	public double getPgFixCharge() {
		return pgFixCharge;
	}
	public void setPgFixCharge(double pgFixCharge) {
		this.pgFixCharge = pgFixCharge;
	}
	public double getPgServiceTax() {
		return pgServiceTax;
	}
	public void setPgServiceTax(double pgServiceTax) {
		this.pgServiceTax = pgServiceTax;
	}
	public String getAcquirerName() {
		return acquirerName;
	}
	public void setAcquirerName(String acquirerName) {
		this.acquirerName = acquirerName;
	}
	public double getFixChargeLimit() {
		return fixChargeLimit;
	}
	public void setFixChargeLimit(double fixChargeLimit) {
		this.fixChargeLimit = fixChargeLimit;
	}
	public boolean isAllowFixCharge() {
		return allowFixCharge;
	}
	public void setAllowFixCharge(boolean allowFixCharge) {
		this.allowFixCharge = allowFixCharge;
	}

	@Override
	public int compareTo(ChargingDetails ChargingDetails) {
		if(transactionType == null){
			String compareString = ChargingDetails.getCurrency()+(getMopType().toString());
			return ((this.currency+this.mopType.toString()).compareToIgnoreCase(compareString));	
		}
		StringBuilder compareString = new StringBuilder();
		compareString.append(ChargingDetails.getCurrency());
		compareString.append(ChargingDetails.getMopType().getName());
		compareString.append(ChargingDetails.getTransactionType().getName());		
		return (this.currency+this.mopType.getName()+this.transactionType.getName()).compareToIgnoreCase(compareString.toString());	
	}
	public String getCurrency() {
		return currency;
	}
	public void setCurrency(String currency) {
		this.currency = currency;
	}
	public double getBankFixChargeAFC() {
		return bankFixChargeAFC;
	}
	public void setBankFixChargeAFC(double bankFixChargeAFC) {
		this.bankFixChargeAFC = bankFixChargeAFC;
	}
	public double getBankTDRAFC() {
		return bankTDRAFC;
	}
	public void setBankTDRAFC(double bankTDRAFC) {
		this.bankTDRAFC = bankTDRAFC;
	}
	public double getMerchantFixChargeAFC() {
		return merchantFixChargeAFC;
	}
	public void setMerchantFixChargeAFC(double merchantFixChargeAFC) {
		this.merchantFixChargeAFC = merchantFixChargeAFC;
	}
	public double getMerchantTDRAFC() {
		return merchantTDRAFC;
	}
	public void setMerchantTDRAFC(double merchantTDRAFC) {
		this.merchantTDRAFC = merchantTDRAFC;
	}
	public double getPgChargeAFC() {
		return pgFixChargeAFC;
	}
	public void setPgFixChargeAFC(double pgFixChargeAFC) {
		this.pgFixChargeAFC = pgFixChargeAFC;
	}
	public double getPgTDRAFC() {
		return pgTDRAFC;
	}
	public void setPgTDRAFC(double pgTDRAFC) {
		this.pgTDRAFC = pgTDRAFC;
	}
	public String getResponse() {
		return response;
	}
	public void setResponse(String response) {
		this.response = response;
	}
	public String getUpdateBy() {
		return updateBy;
	}
	public void setUpdateBy(String updateBy) {
		this.updateBy = updateBy;
	}
	public String getBusinessName() {
		return businessName;
	}
	public void setBusinessName(String businessName) {
		this.businessName = businessName;
	}
	public String getAppId() {
		return appId;
	}
	public void setAppId(String appId) {
		this.appId = appId;
	}


}
