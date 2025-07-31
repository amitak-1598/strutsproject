package com.project.strutsamit.common.exception;

public enum ErrorType {
	SUCCESS 						("000", "SUCCESS"),
	ACQUIRER_ERROR					("001", "Acquirer Error"),
	DENIED							("002", "Denied"),
	TIMEOUT							("003", "Timeout"),
	DECLINED						("004", "Declined"),
	AUTHENTICATION_UNAVAILABLE 		("005", "Authentication not available"),
	PROCESSING						("006", "Transaction processing"),
	REJECTED						("007", "Rejected by acquirer"),
	DUPLICATE						("008", "Duplicate"),
	SIGNATURE_MISMATCH				("009","Response signature did not match"),
	CANCELLED						("010", "Cancelled by user"),	
	RECURRING_PAYMENT_UNSUCCESSFULL	("011", "Authorization success but error processing recurring payment"),
	DENIED_BY_FRAUD					("012", "Denied due to fraud detection"),
	INVALID_REQUEST             	("013", "Invalid request not available"),
	REFUND_INSUFFICIENT_BALANCE     ("014", "Refund amount you requested is greater than transaction amount Your balance is insufficient to cover the Refund Amount"),
	TXN_FAILED				        ("007", "Failed"),
	PENDING_FROM_BANK               ("015", " Status is yet to be received from Bank"),
	AUTO_REVERSAL			        ("016", "Auto Reversal"),
	FAILED_AT_ACQUIRER              ("007", "Failed by acquirer"), 
	INVALID_VPA              		("017", "Invalid vpa address"), 
	
	
	USER_NOT_FOUND 					("100", "100", "User not found", "Incorrect User ID or Password!"),
	USER_PASSWORD_INCORRECT 		("101", "100", "Password incorrect", "Incorrect User ID or Password!" ),
	USER_INACTIVE 					("102", "100", "User inactive", "Incorrect User ID or Password!" ),
	INVALID_INPUT 					("103", "100", "Validation failed", "Incorrect User ID or Password!" ),
	NOT_APPROVED_FROM_ACQUIRER		("104", "105", "User not approved from any acquirer", "User not approved from any acquirer" ),
	ACQUIRER_NOT_FOUND				("105","106","Account not present for this acquirer","Account not present for this acquirer"),
	MAPPING_NOT_FETCHED				("105","107","Unable to fetch mapping from database","Unable to fetch mapping from database"),	
	MAPPING_NOT_SAVED	    		("106","108","Error!! Unable to save mapping to database","Error!! Unable to save mapping to database"),	
	MAPPING_SAVED		   	 		("107","109","Mapping saved successfully!!","Mapping saved successfully!!"),	
	CHARGINGDETAIL_NOT_FETCHED		("108","110","Charging detail not present for this acquirer","Charging detail not present for this acquirer"),
	CHARGINGDETAIL_NOT_SAVED		("109","111","Charging detail not saved to database","Charging detail not saved to database"),
	CURRENCY_NOT_MAPPED	    		("110","112","Merchant not mapped for this currency","Merchant not mapped for this currency"),
	SUCCESSFULLY_SAVED     			("111","Details Successfully Saved"),
	USER_PASSWORD_NOT_SET   		("112", "100", "Password not set", "Incorrect User ID or Password!" ),
	PAYMENT_OPTION_NOT_SUPPORTED    ("113", "Payment option not supported!!"),
	SERVICE_TAX_DEATILS_NOT_AVAILABLE("114", "Service Tax Details Not Set!!"),
	MAPPING_SAVED_FOR_APPROVAL		("115", "Mapping Request saved for approval"),
	MAPPING_REQUEST_ALREADY_PENDING	("116", "Mapping Request already pending for this merchant"),
	MAPPING_REQUEST_APPROVAL_DENIED	("117", "Mapping Request cannot be approved as user is not permitted"),
	MAPPING_REQUEST_NOT_FOUND		("118", "Mapping Request not found"),
	MAPPING_REQUEST_REJECTED		("119", "Mapping Request has been rejected"),
	SERVICE_TAX_DETAILS_SAVED		("120", "Service Tax Details Saved!!"),
	SERVICE_TAX_REQUEST_ALREADY_PENDING		("121", "Service Tax Request already pending for this business type!!"),
	SERVICE_TAX_REQUEST_SAVED_FOR_APPROVAL		("122", "Service Tax Details Saved for approval!!"),
	MERCHANT_SURCHARGE_REQUEST_ALREADY_PENDING ("123", "Merchant Surcharge update request already pending !!"),
	MERCHANT_SURCHARGE_UPDATED ("124", "Merchant Surcharge updated !!"),
	MERCHANT_SURCHARGE_REQUEST_SENT_FOR_APPROVAL ("125", "Merchant Surcharge request saved for approval !!"),
	BANK_SURCHARGE_REQUEST_SENT_FOR_APPROVAL ("126", "Bank Surcharge request saved for approval !!"),
	BANK_SURCHARGE_UPDATED ("127", "Bank Surcharge updated !!"),
	BANK_SURCHARGE_REQUEST_ALREADY_PENDING ("128", "Bank Surcharge update request already pending !!"),
	BANK_SURCHARGE_REJECTED ("128", "Bank Surcharge request rejected !!"),
	TDR_REQUEST_ALREADY_PENDING ("129", "TDR request is already pending for this merchant !!"),

	//User transaction operations
	ALREADY_CAPTURED_TRANSACTION 	("200","This transaction is already setteled with Transaction Id: "),
	CAPTURE_SUCCESSFULL 			("201","Capture processed successfully order ID: "),
	CAPTURE_NOT_SUCCESSFULL 		("202","Capture not processed successfully for order ID: "),
	REFUND_SUCCESSFULL 				("203","Refund processed successfully order ID: "),
	REFUND_NOT_SUCCESSFULL 			("204","Refund not processed successfully order ID: "),
	VOID_NOT_SUCCESSFULL 			("205","Void not processed successfully order ID: "),
	VOID_SUCCESSFULL 				("206","Transaction void processed successfully order ID: "),
	REFUND_FAILED 					("207","Refund Amount should be less than today's Captured Amount "),
	SETTLEMENT_SUCCESSFULL 			("351","Settlement processed successfully order ID: "),
	SETTLEMENT_NOT_SUCCESSFULL 		("352","Settlement not processed successfully order ID: "),

	
	VALIDATION_FAILED		("300", "Invalid Request" ),
	BLACKLISTED_IP			("301", "300", "Blacklisted IP address", "Invalid Request"),
	NO_SUCH_TRANSACTION		("302", "No Such Transaction Found"),
	EMPTY_FIELD             ("303", "This field is manadatory"),
	EMPTY_FIELDS            ("304", "Please provide a valid value for manadatory fields"),
	INVALID_FIELD_VALUE     ("305", "Enter valid value"),
	USER_UNAVAILABLE        ("306", "This user id is already existing"),
	USER_AVAILABLE          ("307", "USER ID is available proceed to signup!!"),
	PASSWORD_MISMATCH       ("308", "Password mismatch"),
	OLD_PASSWORD_MATCH      ("309", "Use a password which has not been used 4 recent times by you"),
	PASSWORD_CHANGED 	    ("310", "Password changed successfully, login to continue"),
	EMAIL_ERROR 	    	("311", "Error!! Unable to send email Emailer fail"),
	NEW_PASSWORD            ("312", "New password should not be blank!"),
	CONFIRM_NEW_PASSWORD    ("313", "Confirm new password should not be blank!"),
	PASSWORD_RESET 	        ("314", "Password reset successfully, login to continue"),
	ALREADY_PASSWORD_RESET 	("315", "You have already used this link, login to continue"),
	RESET_LINK_SENT         ("316", "Reset password link sent to your email id"),
	ALREADY_VALIDATE_EMAIL 	("317", "You have already validate this link, login to continue"),
	INVALID_EMAIL 			("318", "Please provide valid email to reset your account's password"),
	INVALID_CURRENCY_CODE   ("319","301","Invalid currency code","Invalid Request" ),
	INVALID_AMOUNT          ("320","Invalid Request"),
	INVALID_ORDER_ID        ("321","Invalid Request"),
	INVALID_TXN_TYPE        ("322","Invalid Request"),
	INVALID_HASH            ("323","Invalid Request"),
	INVALID_APPID_ATTEMPT   ("324","Request with invalid appId"),
	INVALID_RETURN_URL      ("325","Invalid Request"),
	INVALID_FIELD           ("326","Invalid value"),
	//Remittance 
	ACC_HOLDER_NAME           ("327","Invalid Account Holder Name"),
	ACCOUNT_NO                ("328","Invalid Account Number"),
	BANK_NAME                 ("329","Invalid Bank Name"),
	CURRENCY                  ("330","Invalid Currency"),
	DATE_FROM                 ("331","Invalid Transaction Date"),
	IFSC_CODE                 ("332","Invalid IFSC Code"),
	MERCHANT                  ("333","Invalid Merchant"),
	NET_AMOUNT                ("334","Invalid Net amount"),
	APP_ID                    ("335","Invalid appId"),
	STATUS                    ("336","Invalid Status"),
	UTR                       ("337","Invalid UTR"),
	
	//defaultCurrency Error
	INVALID_DEFAULT_CURRENCY	("338","Operation not successfull, please try again later!!"),	
	UNMAPPED_CURRENCY_ERROR		("339","No currency mapped!!"),
	AUTO_RECONCIL_PROCESSED 		("353","Reconciled successfully"),
	AUTO_RECONCIL_NOT_PROCESSED 	("354","Reconciliation already processed"),

	TXN_UPDATED						("355","Transaction updated successfully"),
	TXN_NOT_UPDATED					("356","Transaction not updated"),
	//fraud prevention sys Errors
			
	FRAUD_RULE_ALREADY_EXISTS	("340", "Fraud rule already exist"),
	FRAUD_RULE_NOT_EXIST		("341", "Fraud rule doesn't exist"),
	FRAUD_RULE_SUCCESS			("342", "Fraud Rule added successfully"),
	FRAUD_RULE_SINGLE_ENTRY_ERROR	("343", "You can add only one rule of this type"),
	COMMON_ERROR				("344", "Something went wrong."),
	
	//ticketing System
	TICKET_SUCCESSFULLY_SAVED	("345","Ticket create successfully!!"),
	COMMENT_SUCCESSFULLY_ADDED	("346","Comment successfully added!!"),
	
	//Surcharge Module
	SURCHARGEDETAIL_NOT_FETCHED	("351","351","Surcharge detail not present for this payment type","Surcharge detail not present for this payment type"),
	SURCHARGEDETAIL_NOT_SAVED	("352","352","Surcharge details not saved to database","Surcharge details not saved to database"),
	SERVICETAX_NOT_SET 			("353","353","Service tax not set for this industry type","Service tax not set for this industry type"),
	
	PERMISSION_DENIED		("400", "Permission Denied"),
	INTERNAL_SYSTEM_ERROR	("900", "900", "Internal System Error", "Operation could not be completed, please try again later!"),
	CRYPTO_ERROR 			("901", "900", "Crypto Issue", "Operation could not be completed, please try again later!" ),
	DATABASE_ERROR			("902", "900", "Database Error", "Operation could not be completed, please try again later!"),
	UNKNOWN 				("999", "Unknown Error"),
	
	JMS_EMAIL_ERROR 		("0001", "Jms Email error"),
	
	CSV_NOT_SUCCESSFULLY_UPLOAD	("354","entry not  saved successfully!!");
	
	
	
	//Response code for user
	private final String responseCode;
	
	//This code contains more details about this error - it may be internal
	private final String code;
	
	//This message contains more details about the error - it may be internal
	private final String internalMessage;
	
	//message for displaying to user
	private final String responseMessage;
	
	private ErrorType(String code, String responseCode, String internalMessage, String userMessage){
		this.code = code;
		this.responseCode = responseCode;
		this.internalMessage = internalMessage;
		this.responseMessage = userMessage;
	}
	private ErrorType(String code, String userMessage){
		this.code = this.responseCode = code;
		this.internalMessage = this.responseMessage = userMessage;
	}
	private ErrorType(String code, String responseCode, String userMessage){
		this.code = code;
		this.responseCode = responseCode;
		this.internalMessage = this.responseMessage = userMessage;
	}

	public String getCode() {
		return code;
	}

	public String getInternalMessage() {
		return internalMessage;
	}

	public String getResponseMessage() {
		return responseMessage;
	}

	public String getResponseCode() {
		return responseCode;
	}
	
	public static ErrorType getInstanceFromCode(String code) {
		ErrorType[] ErrorTypes = ErrorType.values();
		for (ErrorType errorType : ErrorTypes) {
			if (String.valueOf(errorType.getCode()).toUpperCase().equals(code)) {
				return errorType;
			}
		}
		return null;

	}
}
