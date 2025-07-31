package com.project.strutsamit.common;

public enum CrmFieldConstants {

	LAST_ACTION_NAME					("actionName"),
	ALL_MERCHANTS						("ALL MERCHANTS"),
	ALL_USERS							("ALL USERS"),
	ALL									("ALL"),
	SELECT_CURRENCY						("Select Currency"),
	SELECT_MERCHANT						("Select Merchant"),
	TOTAL_CREDIT_SUCCESS				("totalCreditSuccess"),
	TOTAL_CREDIT_FAILED					("totalCreditFailed"),
	TOTAL_CREDIT_TRANSCTION				("totalCreditTransaction"),
	TOTAL_CREDIT_BAUNCED				("totalCreditBaunced"),
	TOTAL_CREDIT_CANCELLED				("totalCreditCancelled"),
	TOTAL_CREDIT_DROPPED				("totalCreditDropped"),

	TOTAL_DEBIT_SUCCESS					("totalDebitSuccess"),
	TOTAL_DEBIT_FAILED					("totalDebitFailed"),
	TOTAL_DEBIT_TRANSCTION				("totalDebitTransaction"),
	TOTAL_DEBIT_BAUNCED					("totalDebitBaunced"),
	TOTAL_DEBIT_CANCELLED				("totalDebitCancelled"),
	TOTAL_DEBIT_DROPPED					("totalDebitDropped"),
	
	TOTAL_NET_BANK_TRANSACTION			("totalNetBankTransaction"),
	TOTAL_NET_BANK_SUCCESS				("totalNetBankSuccess"),
	TOTAL_NET_BANK_FAILED				("totalNetBankFailed"),
	TOTAL_NET_BANK_DROPPED				("totalNetBankDropped"),
	TOTAL_NET_BANK_CANCELLED			("totalNetBankCancelled"),
	
	TOTAL_WALLET_TRANSACTIONS			("totalWalletTransaction"),
	TOTAL_WALLET_SUCCESS				("totalWalletSuccess"),
	TOTAL_WALLET_FAILED				    ("totalWalletFailed"),
	TOTAL_WALLET_DROPPED				("totalWalletDropped"),
	TOTAL_WALLET_CANCELLED			    ("totalWalletCancelled"),
	
	//credit card mopType analytics Payments Method
	TOTAL_VISAS							("totalVisa"),
	TOTAL_SUCCESS_VISA					("totalSuccessVisa"),
	TOTAL_FAILED_VISA					("totalFailedVisa"),
	TOTAL_DROPPED_VISA					("totalDroppedVisa"),
	TOTAL_CANCELLED_VISA				("totalCancelledVisa"),
	TOTAL_AMEXS							("totalAmex"),
	TOTAL_SUCCESS_AMEX					("totalSuccessAmex"),
	TOTAL_FAILED_AMEX					("totalFailedAmex"),
	TOTAL_DROPPED_AMEX					("totalDroppedAmex"),
	TOTAL_CANCELLED_AMEX				("totalCancelledAmex"),
	TOTAL_MASTERS						("totalMaster"),
	TOTAL_SUCCESS_MASTER				("totalSuccessMaster"),
	TOTAL_FAILED_MASTER					("totalFailedMaster"),
	TOTAL_DROPPED_MASTER				("totalDroppedMaster"),
	TOTAL_CANCELLED_MASTER				("totalCancelledMaster"),
	TOTAL_MESTRO						("totalMestro"),
	TOTAL_SUCCESS_MESTRO				("totalSuccessMestro"),
	TOTAL_FAILED_MESTRO					("totalFailedMestro"),
	TOTAL_DROPPED_MESTRO				("totalDroppedMestro"),
	TOTAL_CANCELLED_MESTRO				("totalCancelledMestro"),


	TOTAL_DINER							("totalDiner"),
	TOTAL_SUCCESS_DINER					("totalSuccessDiner"),
	TOTAL_FAILED_DINER					("totalFailedDiner"),
	TOTAL_DROPPED_DINER					("totalDroppedDiner"),
	TOTAL_CANCELLED_DINER				("totalCancelledDiner"),
	

	TOTAL_NETBANKING_TRANSACTION		("totalNetBankingTransaction"),
	TOTAL_CREDITCARDS_TRANSACTION		("totalCreditCardsTransaction"),
	TOTAL_DEBIT_TRANSACTION				("totalDebitCardsTransaction"),
	TOTAL_WALLET_TRANSACTION			("totalWalletTransaction"),


	TOTAL_CANCELL				    	("totalCancel"),
	TOTAL_CANCELL_AMOUNT				("totalCancelAmount"),
	TOTAL_INVALID				    	("totalInvalid"),
	TOTAL_INVALID_AMOUNT				("totalInvalidAmount"),
	TOTAL_CANCELLED						("totalCancelled"),
	TOTAL_BAUNCED						("totalBaunced"),
	TOTAL_DROPPED						("totalDropped"),
	TOTAL_TRANSACTION					("totalTransaction"),
	TOTAL_TRANSACTIONS					("totalTxns"),
	TOTAL_PENDING						("totalPending"),
	TOTAL_SUCCESS						("totalSuccess"),
	TOTAL_NEW_USER						("totalNewUser"),
	TOTAL_FAILED						("totalFailed"),
	TOTAL_FAILED_AMOUNT					("totalFailedAmount"),
	TOTAL_PENDING_AMOUNT				("totalPendingAmount"),
	TOTAL_REFUNDED						("totalRefunded"),
	REFUNDED_AMOUNT						("refundedAmount"),
	PAYMENT_METHOD						("paymentMethod"),
	CAPTURED_AMOUNT						("CAPTURED_AMOUNT"),
	REFUND_AMOUNT						("REFUND_AMOUNT"),
	CHARGEBACK_AMOUNT					("CHARGEBACK_AMOUNT"),
	APPROVED_AMOUNT						("approvedAmount"),
	TXN_AMOUNT							("TXN_AMOUNT"),	
	CREATE_DATE							("CREATE_DATE"),
	TXN_DATE							("txndate"),
	REFUND_DATE							("REFUND_DATE"),
	CAPTURE_TXN_ID						("CAPTURE_TXN_ID"),
	REFUNDED							("Refunded"),
	INTERNAL_REQUEST_FIELDS				("INTERNAL_REQUEST_FIELDS"),
	INVOICE_URL							("invoiceURL"),
	INVOICE_PROMOTIONAL_URL				("invoicePromotionalURL"),
	INVOICE_RETURN_URL					("invoiceReturnUrl"),
	TOTAL_VISA							("visa"),
	TOTAL_MASTER						("mastercard"),
	TOTAL_AMEX							("amex"),
	TOTAL_NETBANKING					("net"),
	TOTAL_MESTRO_CARDS					("maestro"),
	TOTAL_EZEECLICK					    ("ezeeClick"),
	TOTAL_OTHER							("other"),
	TOTAL_CREDIT						("totalCredit"),
	TOTAL_DEBIT							("totalDebit"),
	REFUNDABLE_AMOUNT					("refundableAmount"),
	TOTAL_NEW_ORDER						("totalNewOder"),
	TOTAL_ENROLLED						("totalEnrolled"),
	MODE_TYPE							("modeType"),
	ACCOUNTS							("accounts"),
	EMAIL_VALIDATION_FLAG				("emailValidationFlag"),
	EXPRESS_PAY_FLAG					("expressPayFlag"),
	MERCHANT_HOSTED_FALAG				("merchantHostedFlag"),
	TRANSACTION_AUTHENTICATION_EMAIL_FLAG("transactionAuthenticationEmailFlag"),
	TRANSACTION_CUSTOMER_EMAIL_FLAG		("transactionCustomerEmailFlag"),
	REFUND_TXN_CUSTOMER_EMAIL_FLAG		("refundTransactionCustomerEmailFlag"),
	REFUND_TXN_MERCHANT_EMAIL_FLAG		("refundTransactionMerchantEmailFlag"),
	RETRY_TRANSACTION_FLAG				("retryTransactionCustomeFlag"),
	IFRAME_PAYMENT_FLAG					("iframePaymentFlag"),
	TRANSACTION_EMAILER_FLAG			("transactionEmailerFlag"),
	TRANSACTION_SMS_FLAG				("transactionSmsFlag"),
	USER_TYPE							("userType"),
	SURCHARGE_FLAG						("surchargeFlag"),

	//Request Fields
	SERVICE_USER_AGENT					("WebService"),
	USER_AGENT							("User-Agent"),
	CONFIRM_PASSWORD					("confirmPassword"),
	OLD_PASSWORD						("oldPassword"),
	NEW_PASSWORD						("newPassword"),
	CONFIRM_NEW_PASSWORD				("confirmNewPassword"),
	MERCHANTS							("MERCHANTS"),
	USER_RESELLER_TYPE					("reseller"),
	
	//General Configurations    
	RUN_SCHEDULAR_FALG					("RunSchedular"),


	//Action return strings
	SUPERADMIN                          ("superAdmin"),
	ADMIN								("admin"),
	NEW_USER							("newuser"),
	MERCHANT							("merchant"),
	SUBUSER								("subuser"),	
	SIGNUP_PROFILE						("signupProfile"),
	ACQUIRER							("acquirer"),
	SUBADMIN							("subAdmin"),
	ACQUIRER_SUBUSER					("acquirerSubuser"),


	//Messages
	RESELLER_DETAILS_UPDATED			("Reseller mapped successfully"),
	USER_DETAILS_UPDATED				("User details updated successfully"),
	DETAILS_UPDATE_REQUEST              ("Update request submitted"),
	PENDING_REQUEST_EXIST               ("Update request already exists!"),
	DETAILS_SAVED_SUCCESSFULLY			("Details saved successfully"),
	PROCESS_INITIATED_SUCCESSFULLY		("Request processed successfully"),
	UTR_ALREADY_EXCITED					("Sorry!! You can not proceed as UTR already exists"),
	AMOUNT_CAN_NOT_BE_BLANK				("Please try again, Amount can not be zero"),
	PLEASE_SELECT_MERCHANT				("Please select merchant name"),
	NETBANKING_VALIDATION				("Netbanking transactions can only be refunded after 2 days of authorisation !! "),
	FROMTO_DATE_VALIDATION				("From date must be before the To date"),
	DATE_RANGE							("No. of days can not be more than 31"),
	INVALID_CAPTCHA						("Invalid Captcha"),
	PLEASE_SAVE							("Please click Save to use default theme"),
	SELECT_PRIMARY_CARD					("Please select primary card"),
	SELECT_SALE							("Please select payment mode as SALE for Yes Bank"),
	SELECT_DIRECPAY_YES					("Please select Direcpay or YesBank as acquirer"),
	SELECT_DIRECPAY_BILLDESK		    ("Please select SafexPay or BillDesk as acquirer"),
	SELECT_DIRECPAY_ATOM		        ("Please select ISG or Atom as acquirer"),
	SELECT_ONLY_ONE						("Select only one acquirer as primary card"),
	SELECT_ONE_NETBANKING				("Please select primary netbanking"),
	SELECT_ONE_WALLET				    ("Please select primary wallet"),
	SELECT_ONE_UPI				        ("Please select primary upi"),
	SELECT_DIRECPAY_CASHFREE			("Please select PAYU or CASHFREE as acquirer"),
	
	//Status
	INITIATED							("INITIATED"),
	STATUS								("STATUS"),

	USER_CHARGINGDETAILS_NOT_SET_MSG	("Please set atleast one tdr for this merchant"),

	//Retry flag message
	RETRY_TRANSACTION					("Your transaction has been failed. Please retry"),

	//Date format
	INPUT_DATE_FORMAT					("dd-MM-yyyy"),
	OUTPUT_DATE_FORMAT					("YYYY-MM-dd"),
	OUTPUT_DATE_FORMAT_DB				("YYYY-MM-dd HH:mm:ss"),
	DATE_TIME_FORMAT					("dd-MM-yyyy 'T' HH:mm:ss"),
	INPUT_DATE_FOR_MIS_REPORT			("dd/MM/yyyy"),
	COMMA								(","),

	//Transaction fields
	ORIG_TXN_ID							("origTxnId"),
	RESPONSE							("response"),
	CURRENCY_CODE						("currencyCode"),
	NOT_AVAILABLE						("Not available"),

	//Website Signup
	WEBSITE_SIGNUP_appId				("websiteSignupappId"),
	WEBSITE_SIGNUP_RETURNURL			("websiteSignupReturnUrl"),
	WEBSITE_PACKAGE_CURRENCY			("packageCurrency"),

	//Interceptor fields
	HTTP_POST_METHOD					("POST"),

	//Sub User Permission Fields
	VIEW_TRANSACTIONS					("View Transactions"),
	VIEW_REPORTS						("View Reports"),
	CREATE_INVOICE						("Create Invoice"),
	VIEW_INVOICE						("View Invoice"),
	VIEW_REMITTANCE						("View Remittance"),
	VIEW_MERCHANT_SETUP					("View MerchantSetup"),
	CREATE_MAPPING					    ("Create Mapping"),
	VIEW_ANALYTICS					    ("View Analytics"),
	VIEW_SEARCH_PAYMENT					("View SearchPayment"),
	VIEW_RECONCILIATION					("View Reconciliation"),
	CREATE_BATCH_OPERATION				("Create BatchOperation"),
	FRAUD_PREVENTION				    ("Fraud Prevention"),
	CREATE_BULK_EMAIL				    ("Create BulkEmail"),
	VIEW_CASHBACK				        ("View CashBack"),
	CREATE_HELPTIKECT				    ("Create HelpTicket"),
	//Customer Transaction Authentication
	COUNTRY_CODE						("CountryCode"),
	INDIA_REGION_CODE					("India"),

	//Yes bank Refund
	ROW_NUMBER							("rowNumber"),
	IFSC_CODE							("YESB"),

	//Currency Code
	INR									("356"),
	USD									("840"),
	AED									("784"),
	GBP									("826"),
	EUR									("978"),
	AUD									("036"),

	//default currency success message
	DEFAUL_CURRENCY_UPDATE				("Default currency updated successfully!!"),
	
	//Acquirer BankType for misReport
	FSS_BANK                            ("HDFC BANK LIMITED"),
	DIREC_PAY_BANK                      ("KOTAK BANK LIMITED"),
	AMEX_BANK                           ("AMERICAN EXPRESS"),
	MOBIKWIK_BANK                       ("ICICI BANK LTD"),
	CITRUS_PAY_BANK                     ("Yes Bank"),
	
	// transaction types
	WALLET                              ("Wallet"),
	NET_BANKING                         ("Net-Banking"),
		
	SETTLEMENT_PROCESSED  				("PROCESSED"),
	
	//Heading in transaction emailer
	MERCHANT_HEADING					("Merchant"),
	CUSTOMER_HEADING					("Customer"),
	MERCHANT_MESSAGE					("The payment has been successfully processed by the customer."),
	CUSTOMER_MESSAGE					("Thank you for paying with Paylogic. Your Payment has been successfully processed."),
	CUSTOMER							("CUSTOMER"),
	
	//Chargeback
	GENERATED_SUCCESSFULLY 				("Chargeback generated successfully."),
	
	PAYMENT_TYPE_DC					    ("Please Try Only Debit Card"),
	PAYMENT_TYPE_CC					    ("Please Try Only Credit Card"),


	//Ticketing System
	TICKET_ID							("Ticket id"),
	TICKET_MESSAGE						("Ticket Message"),
	TICKET_ASSIGNED_TO					("Ticket Asssigned To"),
	TICKET_STATUS						("Ticket Stauts"),
	TICKET_TYPE							("Ticket Type"),
	TICKET_MOBILE						("Mobile No"),
	ASSIGNED_TICKET_SUCCESS				("Agent assigned update successfully!!"),	
	ASSIGNED_TICKET_FAILED				("Agent assigned update failed!!"),
	TICKET_DETAILS_UPDATE				("Ticket details updated successfully"),
	TICKET_CREATE_SUCCESSFULLY			("Ticket generated successfully."),
	ADMIN_TICKET_HEADING				("Admin"),
	AGENT_TICKET_HEADING				("Agent"),
	MERCHANT_TICKET_MESSAGE				("TicketCreate successfully"),
	ADMIN_TICKET_MESSAGE				("TicketCreate successfully"),
	ADMIN_UPDATE_TICKET					("Ticket update successfully"),
	MESSAGE_SEND_SUCCESSFULLY			("Message send successfully"),	
	
	
	//URL SHORTENER
	GOOGLE_URL_SHORTENER				("GoogleURLShortner");
	
	private final String value; 

	private CrmFieldConstants(String value){
		this.value = value;
	}

	public String getValue() {
		return value;
	}	
}
