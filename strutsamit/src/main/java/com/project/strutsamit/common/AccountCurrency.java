package com.project.strutsamit.common;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.Proxy;

/**
 * @author Neeraj
 *
 */
@Entity
@Proxy(lazy= true)@Cache(usage=CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class AccountCurrency implements Serializable{

	private static final long serialVersionUID = 5796272112495882669L;
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;

	private String currencyCode;
	private String merchantId;
	private String password;
	private String txnKey;
	private String acqappId;
	private boolean directTxn;
	
	public String getCurrencyCode() {
		return currencyCode;
	}
	public void setCurrencyCode(String currencyCode) {
		this.currencyCode = currencyCode;
	}
	public String getMerchantId() {
		return merchantId;
	}
	public void setMerchantId(String merchantId) {
		this.merchantId = merchantId;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getTxnKey() {
		return txnKey;
	}
	public void setTxnKey(String txnKey) {
		this.txnKey = txnKey;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getAcqappId() {
		return acqappId;
	}
	public void setAcqappId(String acqappId) {
		this.acqappId = acqappId;
	}
	public boolean isDirectTxn() {
		return directTxn;
	}
	public void setDirectTxn(boolean directTxn) {
		this.directTxn = directTxn;
	}
}
