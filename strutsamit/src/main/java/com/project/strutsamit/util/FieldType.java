package com.project.strutsamit.util;

import java.util.HashMap;
import java.util.Map;

/*
 * This type serves as the central validation information for different fields which can be submitted in a request
 */
public enum FieldType {

	//Internal use fields
	ACQUIRER_TYPE				("ACQUIRER_TYPE", 3, 10, false, FieldFormatType.ALPHA, false),
	INTERNAL_ACQUIRER_TYPE		("INTERNAL_ACQUIRER_TYPE", 3, 20, false, FieldFormatType.ALPHA, false),
	INTERNAL_VALIDATE_HASH_YN	("INTERNAL_VALIDATE_HASH_YN", 1, 1, false, FieldFormatType.NONE, false),	
	INTERNAL_ORIG_TXN_TYPE		("INTERNAL_ORIG_TXN_TYPE", 3, 20, false, FieldFormatType.NONE, false),
	INTERNAL_CUST_IP			("INTERNAL_CUST_IP", 7, 15, false, FieldFormatType.NONE, true),
	INTERNAL_CUST_COUNTRY_NAME	("INTERNAL_CUST_COUNTRY_NAME", 2, 50, false, FieldFormatType.ALPHANUM, true),
	INTERNAL_CUSTOM_MDC			("INTERNAL_CUSTOM_MDC", 1, 256, false, FieldFormatType.NONE, false),
	INTERNAL_REQUEST_FIELDS		("INTERNAL_REQUEST_FIELDS", 1, 6000, false, FieldFormatType.NONE, true),
	INTERNAL_INVALID_HASH_YN	("INTERNAL_INVALID_HASH_YN", 1, 1, false, FieldFormatType.NONE, false),
	INTERNAL_ORIG_TXN_ID		("INTERNAL_ORIG_TXN_ID", 8, 16, false, FieldFormatType.SPECIAL, false),
	INTERNAL_SHOPIFY_YN 		("INTERNAL_SHOPIFY_YN", 1, 1, false, FieldFormatType.NONE, false),
	OID							("OID", 8, 16, false, FieldFormatType.NUMBER, false),
	INTERNAL_TXN_AUTHENTICATION	("INTERNAL_TXN_AUTHENTICATION", 0, 16, false, FieldFormatType.ALPHA, false),
	CREATE_DATE                 ("CREATE_DATE", 19, 19, false, FieldFormatType.SPECIAL, false),
	UPDATE_DATE                 ("UPDATE_DATE", 19, 19, false, FieldFormatType.SPECIAL, false),
	
	//Response fields
	DUPLICATE_YN				("DUPLICATE_YN", 1, 1, false, FieldFormatType.ALPHA, false),
	RESPONSE_CODE				("RESPONSE_CODE", 1, 10, false, FieldFormatType.ALPHANUM, false),		
	RESPONSE_MESSAGE			("RESPONSE_MESSAGE", 1, 256,false, FieldFormatType.ALPHANUM, false),
	ORIG_TXN_ID					("ORIG_TXN_ID", 8, 16, false, FieldFormatType.SPECIAL, false),
	STATUS						("STATUS", 5, 30, false, FieldFormatType.SPECIAL, false),
	ACS_URL						("ACS_URL", 5, 256, false, FieldFormatType.SPECIAL, false),
	PAREQ						("PAREQ", 1, 1000, false, FieldFormatType.SPECIAL, false),
	PAYMENT_ID					("PAYMENT_ID", 1, 19, false, FieldFormatType.SPECIAL, false),
	ECI							("ECI", 0, 2, false, FieldFormatType.SPECIAL, false),
	AUTH_CODE					("AUTH_CODE", 0, 6, false, FieldFormatType.ALPHANUM, false),
	RRN							("RRN", 0, 50, false, FieldFormatType.ALPHANUM, false),
	AVR							("AVR", 0, 3, false, FieldFormatType.ALPHANUM, false),
	POST_DATE					("POST_DATE", 0, 20, false, FieldFormatType.SPECIAL, false),
	ACQ_ID						("ACQ_ID", 0, 50, false, FieldFormatType.ALPHANUM, false),
	MD							("MD", 0, 19, false, FieldFormatType.ALPHANUM, false),
	RESPONSE_DATE				("RESPONSE_DATE", 10, 10, false, FieldFormatType.SPECIAL, false),
	RESPONSE_TIME				("RESPONSE_TIME", 6, 10, false, FieldFormatType.SPECIAL, false),
	RESPONSE_DATE_TIME			("RESPONSE_DATE_TIME", 19, 19, false, FieldFormatType.SPECIAL, false),
	RESPONSE_FIELDS		        ("RESPONSE_FIELDS", 1, 6000, false, FieldFormatType.NONE, true),
	INTERNAL_CURRENCY_CHANGE_RATE  ("INTERNAL_CURRENCY_CHANGE_RATE", 3, 12, false, FieldFormatType.AMOUNT, true),

	//Card Information
	//Card number minimum length is 13, because FSS UAT contains a test case with card length 13
	CARD_NUMBER					("CARD_NUMBER", 15, 19, false, FieldFormatType.NUMBER, false),
	CARD_MASK					("CARD_MASK", 15, 19, false, FieldFormatType.SPECIAL, true),
	S_CARD_NUMBER				("S_CARD_NUMBER", 28, 28, false, FieldFormatType.SPECIAL, false),
	H_CARD_NUMBER				("H_CARD_NUMBER", 64, 64, false, FieldFormatType.ALPHANUM, false),
	CARD_EXP_DT					("CARD_EXP_DT", 6, 6, false, FieldFormatType.NUMBER, false),
	S_CARD_EXP_DT				("S_CARD_EXP_DT", 28, 28, false, FieldFormatType.SPECIAL, false),
	H_CARD_EXP_DT				("H_CARD_EXP_DT", 64, 64, false, FieldFormatType.ALPHANUM, false),
	CVV							("CVV", 3, 4, false, FieldFormatType.NUMBER, false),
	PARES						("PARES", 1, 10000, false, FieldFormatType.SPECIAL, false),
	INTERNAL_CARD_ISSUER_BANK	("INTERNAL_CARD_ISSUER_BANK", 1, 256, false, FieldFormatType.ALPHANUM, true),
	INTERNAL_CARD_ISSUER_COUNTRY("INTERNAL_CARD_ISSUER_COUNTRY", 2, 100, false, FieldFormatType.ALPHANUM, true),
	UPI					        ("UPI", 15, 200, false,  FieldFormatType.SPECIAL,false),
	CARD_ISSUER_BANK	        ("CARD_ISSUER_BANK", 1, 256, false, FieldFormatType.ALPHANUM, true),
	CARD_ISSUER_COUNTRY	        ("INTERNAL_CARD_ISSUER_BANK", 1, 256, false, FieldFormatType.ALPHANUM, true),
	PAYMENTS_REGION	            ("PAYMENTS_REGION", 1, 256, false, FieldFormatType.ALPHANUM, true),
	MERCHANT_PAYMENT_TYPE	    ("MERCHANT_PAYMENT_TYPE", 2, 2, false, FieldFormatType.ALPHA, false),


	//Customer billing information
	CUST_ID						("CUST_ID", 5, 256, false, FieldFormatType.SPECIAL, true),
	CUST_NAME					("CUST_NAME", 1, 150, false, FieldFormatType.ALPHA, true),
	CUST_FIRST_NAME				("CUST_FIRST_NAME", 2, 150, false, FieldFormatType.ALPHA, true),
	CUST_LAST_NAME				("CUST_LAST_NAME", 2, 150, false, FieldFormatType.ALPHA, true),
	CUST_PHONE					("CUST_PHONE", 8, 15, false, FieldFormatType.NUMBER, true),
	CUST_STREET_ADDRESS1		("CUST_STREET_ADDRESS1", 2, 250, false, FieldFormatType.ALPHANUM, true),
	CUST_STREET_ADDRESS2		("CUST_STREET_ADDRESS2", 2, 250, false, FieldFormatType.ALPHANUM, true),
	CUST_CITY					("CUST_CITY", 2, 50, false, FieldFormatType.ALPHANUM, true),
	CUST_STATE					("CUST_STATE", 2, 100, false, FieldFormatType.ALPHANUM, true),
	CUST_COUNTRY				("CUST_COUNTRY", 2, 100, false, FieldFormatType.ALPHANUM, true),
	CUST_EMAIL					("CUST_EMAIL", 6, 120, true, FieldFormatType.EMAIL, false),
	INTERNAL_USER_EMAIL			("INTERNAL_USER_EMAIL", 6, 120, false, FieldFormatType.EMAIL, true),
	CUST_ZIP					("CUST_ZIP", 6, 9, false, FieldFormatType.ALPHANUM, true),	

	//Customer shipping information
	CUST_SHIP_NAME				("CUST_SHIP_NAME", 2, 150, false, FieldFormatType.ALPHA, true),
	CUST_SHIP_FIRST_NAME		("CUST_SHIP_FIRST_NAME", 2, 150, false, FieldFormatType.ALPHA, true),
	CUST_SHIP_LAST_NAME			("CUST_SHIP_LAST_NAME", 2, 150, false, FieldFormatType.ALPHA, true),
	CUST_SHIP_PHONE				("CUST_SHIP_PHONE", 8, 15, false, FieldFormatType.NUMBER, true),
	CUST_SHIP_STREET_ADDRESS1	("CUST_SHIP_STREET_ADDRESS1", 2, 250, false, FieldFormatType.ALPHANUM, true),
	CUST_SHIP_STREET_ADDRESS2	("CUST_SHIP_STREET_ADDRESS2", 2, 250, false, FieldFormatType.ALPHANUM, true),
	CUST_SHIP_CITY				("CUST_SHIP_CITY", 2, 50, false, FieldFormatType.ALPHANUM, true),
	CUST_SHIP_STATE				("CUST_SHIP_STATE", 2, 100, false, FieldFormatType.ALPHANUM, true),
	CUST_SHIP_COUNTRY			("CUST_SHIP_COUNTRY", 2, 100, false, FieldFormatType.ALPHANUM, true),
	CUST_SHIP_EMAIL				("CUST_SHIP_EMAIL", 6, 120, false, FieldFormatType.EMAIL, true),
	CUST_SHIP_ZIP				("CUST_SHIP_ZIP", 6, 9, false, FieldFormatType.NUMBER, true),	

	//Order information
	REQUEST_URL				("REQUEST_URL", 5, 1024, false, FieldFormatType.URL, false),
	RETURN_URL				("RETURN_URL", 5, 1024, false, FieldFormatType.URL, false),
	CANCEL_URL				("CANCEL_URL", 5, 1024, false, FieldFormatType.URL, false),
	CURRENCY_CODE			("CURRENCY_CODE", 3, 3, true,  FieldFormatType.NUMBER, false),
	KEY_ID					("KEY_ID", 1, 2, false, FieldFormatType.NUMBER, false),
	ORDER_ID 				("ORDER_ID", 1, 50, true, FieldFormatType.SPECIAL, false),
	AMOUNT					("AMOUNT", 3, 12, true, FieldFormatType.AMOUNT, false),
	REFUNDAMOUNT			("REFUNDAMOUNT", 3, 12, false, FieldFormatType.NUMBER, false),
	REFUND_DATE_TIME		("REFUND_DATE_TIME", 1, 50, false, FieldFormatType.SPECIAL, false),
	TXNTYPE					("TXNTYPE", 4, 50, true, FieldFormatType.ALPHA, false),
	MERCHANT_TXN_ID			("MERCHANT_TXN_ID", 6, 50, false, FieldFormatType.SPECIAL, false),
	TXN_ID					("TXN_ID", 8, 16, false, FieldFormatType.NUMBER, false),
	APP_ID					("APP_ID", 2, 36, true, FieldFormatType.NUMBER, false),
	MERCHANT_ID				("MERCHANT_ID", 15, 1000, false, FieldFormatType.SPECIAL, false),
	MOP_TYPE				("MOP_TYPE", 2, 5, false, FieldFormatType.ALPHANUM, false),
	PASSWORD				("PASSWORD", 6, 2000, false, FieldFormatType.SPECIAL, false),
	ACCT_ID					("ACCT_ID", 1, 20, false, FieldFormatType.NUMBER, false),
	PAYMENT_TYPE			("PAYMENT_TYPE", 2, 2, false, FieldFormatType.ALPHA, false),
	PG_REF_NUM				("PG_REF_NUM", 1, 100, false, FieldFormatType.ALPHANUM, false),
	PG_RESP_CODE			("PG_RESP_CODE", 1, 10, false, FieldFormatType.ALPHANUM, false),
	PG_TXN_MESSAGE			("PG_TXN_MESSAGE", 1, 255, false, FieldFormatType.ALPHANUM, false),
	PG_TXN_STATUS			("PG_TXN_STATUS", 1, 50, false, FieldFormatType.ALPHANUM, false),	
	PG_DATE_TIME			("PG_DATE_TIME", 1, 50, false, FieldFormatType.SPECIAL, false),
	PG_GATEWAY				("PG_GATEWAY", 1, 100, false, FieldFormatType.ALPHANUM, false),	
	HASH					("HASH", 64, 64, false, FieldFormatType.ALPHANUM, false),
	INTERNAL_BANK_NAME		("BANK_NAME", 3, 255, false, FieldFormatType.ALPHA, false),
	INTERNAL_BANK_CODE		("BANK_CODE", 3, 6, false, FieldFormatType.NUMBER, false),
	WALLET_NAME				("WALLET_NAME", 3, 6, false, FieldFormatType.ALPHA, false),
	PRODUCT_DESC			("PRODUCT_DESC", 1, 1024, false, FieldFormatType.SPECIAL, true),
	TXN_KEY				    ("TXN_KEY", 6, 1000, false, FieldFormatType.SPECIAL, false),
	TOKEN_ID				("TOKEN_ID", 16, 16, false, FieldFormatType.SPECIAL, false),
	//merchant Captured and Refunded Amount 
	TODAY_CAPTURED_AMOUNT	("todayCapturedAmount",2,12,false,FieldFormatType.NUMBER,false),
	TODAY_REFUND 			("todayRefundAmount",2,12,false,FieldFormatType.NUMBER,false),
	//Txn Information
	IS_MERCHANT_HOSTED                      ("IS_MERCHANT_HOSTED", 1, 1, false, FieldFormatType.ALPHA, false),
	IS_RECURRING     						("IS_RECURRING", 1, 1, false, FieldFormatType.ALPHA, true),
	RECURRING_TRANSACTION_INTERVAL    		("RECURRING_TRANSACTION_INTERVAL", 1, 25, false, FieldFormatType.ALPHA, false),
	RECURRING_TRANSACTION_COUNT			    ("RECURRING_TRANSACTION_COUNT", 1, 2, false, FieldFormatType.NUMBER, false),
	RETRY_FLAG								("RETRY_FLAG",1,1,false, FieldFormatType.ALPHA, false),
	NUMBER_OF_RETRY							("NUMBER_OF_RETRY",1,1,false, FieldFormatType.NUMBER, false),
	COUNT			                        ("recordsTotal", 0, 100000000, false, FieldFormatType.NUMBER, false),
	RECURRING_TRANSACTION_ID				("RECURRING_TRANSACTION_ID",16,16,false, FieldFormatType.NUMBER, false),
	
	SURCHARGE_FLAG							("SURCHARGE_FLAG",1,1,false, FieldFormatType.ALPHA, false),
	CC_SURCHARGE							("CC_SURCHARGE", 3, 12, false, FieldFormatType.AMOUNT, false),
	DC_SURCHARGE							("DC_SURCHARGE", 3, 12, false, FieldFormatType.AMOUNT, false),
	NB_SURCHARGE							("NB_SURCHARGE", 3, 12, false, FieldFormatType.AMOUNT, false),
	SURCHARGE_CC_AMOUNT						("SURCHARGE_CC_AMOUNT", 3, 12, false, FieldFormatType.AMOUNT, false),
	SURCHARGE_DC_AMOUNT						("SURCHARGE_DC_AMOUNT", 3, 12, false, FieldFormatType.AMOUNT, false),
	SURCHARGE_NB_AMOUNT						("SURCHARGE_NB_AMOUNT", 3, 12, false, FieldFormatType.AMOUNT, false),
	SURCHARGE_AMOUNT						("SURCHARGE_AMOUNT", 3, 12, false, FieldFormatType.AMOUNT, false),
	//txn channel
	INTERNAL_TXN_CHANNEL					("INTERNAL_TXN_CHANNEL",3,3,false, FieldFormatType.NUMBER, false);

	private final String name;
	private final int minLength;
	private final int maxLength;
	private final boolean required;
	private final FieldFormatType type;
	private final String responseMessage;
	private final boolean isSpecialCharReplacementAllowed;
	
	private FieldType(String name, int minLength, int maxLength, boolean required, String responseMessage, FieldFormatType type, boolean isSpecialCharReplacementAllowed){
		this.name = name;
		this.minLength = minLength;
		this.maxLength = maxLength;
		this.required = required;
		this.responseMessage = responseMessage;
		this.type = type;
		this.isSpecialCharReplacementAllowed = isSpecialCharReplacementAllowed;
	}
	
	private FieldType(String name, int minLength, int maxLength, boolean required, FieldFormatType type, boolean isSpecialCharReplacementAllowed){
		this.name = name;
		this.minLength = minLength;
		this.maxLength = maxLength;
		this.required = required;
		this.responseMessage = "Invalid " + name;
		this.type = type;
		this.isSpecialCharReplacementAllowed = isSpecialCharReplacementAllowed;
	}
	
	public static Map<String, FieldType> getFieldsMap(){
		Map<String, FieldType> fields = new HashMap<String, FieldType>();
		
		FieldType[] fieldTypes = FieldType.values();
		for(FieldType fieldType: fieldTypes){
			fields.put(fieldType.getName(), fieldType);
		}
		
		return fields;
	}
	
	public static Map<String, FieldType> getMandatoryRequestFields(){
		Map<String, FieldType> fields = new HashMap<String, FieldType>();
		
		FieldType[] fieldTypes = FieldType.values();
		for(FieldType fieldType: fieldTypes){
			if(fieldType.isRequired()){
				fields.put(fieldType.getName(), fieldType);	
			}			
		}
		
		return fields;
	}
	
	public static Map<String, FieldType> getMandatorSupportFields(){
		Map<String, FieldType> fields = new HashMap<String, FieldType>();
		
		fields.put(FieldType.ORIG_TXN_ID.getName(), FieldType.ORIG_TXN_ID);
		fields.put(FieldType.TXNTYPE.getName(), FieldType.TXNTYPE);
		fields.put(FieldType.AMOUNT.getName(), FieldType.AMOUNT);
		fields.put(FieldType.APP_ID.getName(), FieldType.APP_ID);
		fields.put(FieldType.HASH.getName(), FieldType.HASH);
		
		return fields;
	}
	
	public String getName() {
		return name;
	}
	public int getMinLength() {
		return minLength;
	}
	public int getMaxLength() {
		return maxLength;
	}
	public boolean isRequired() {
		return required;
	}
	public String getResponseMessage() {
		return responseMessage;
	}
	public FieldFormatType getType() {
		return type;
	}

	public boolean isSpecialCharReplacementAllowed() {
		return isSpecialCharReplacementAllowed;
	}
}
