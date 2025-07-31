package com.project.strutsamit.util;

import java.util.ArrayList;
import java.util.List;

public enum PaymentType {

	CREDIT_CARD("Credit Card", "CC"), 
	DEBIT_CARD("Debit Card", "DC"), 
	NET_BANKING("Net Banking", "NB"),
	EMI("EMI", "EM"), 
	WALLET("Wallet", "WL"), 
	UPI("UPI", "UP"), 
	RECURRING_PAYMENT("Recurring Payment", "RP"),
	EXPRESS_PAY("Express Pay", "EX");

	private final String name;
	private final String code;

	private PaymentType(String name, String code) {
		this.name = name;
		this.code = code;
	}

	public String getName() {
		return name;
	}

	public String getCode() {
		return code;
	}

	public static String getpaymentName(String paymentCode) {
		String payment = null;
		if (null != paymentCode) {
			for (PaymentType pay : PaymentType.values()) {
				if (paymentCode.equals(pay.getCode().toString())) {
					payment = pay.getName();
					break;
				}
			}
		}
		return payment;
	}

	public static PaymentType getInstance(String name) {
		PaymentType[] paymentTypes = PaymentType.values();
		for (PaymentType paymentType : paymentTypes) {
			if (paymentType.getName().toString().equals(name)) {
				return paymentType;
			}
		}
		return null;
	}

	public static PaymentType getInstanceUsingStringValue(String value) {
		PaymentType[] paymentTypes = PaymentType.values();
		for (PaymentType paymentType : paymentTypes) {
			if (paymentType.toString().equals(value)) {
				return paymentType;
			}
		}
		return null;
	}

	public static PaymentType getInstanceUsingCode(String paymentCode) {
		PaymentType payment = null;
		if (null != paymentCode) {
			for (PaymentType pay : PaymentType.values()) {
				if (paymentCode.equals(pay.getCode().toString())) {
					payment = pay;
					break;
				}
			}
		}
		return payment;
	}

	public static List<PaymentType> getGetPaymentsFromSystemProp(String acquirerCode) {
		PropertiesManager propertiesManager = new PropertiesManager();

		List<String> paymentCodeStringList = (List<String>) Helper
				.parseFields(propertiesManager.getAcquirerMopType(acquirerCode));

		List<PaymentType> paymentTypes = new ArrayList<PaymentType>();

		for (String paymentCode : paymentCodeStringList) {
			PaymentType pay = getInstance(getpaymentName(paymentCode));
			paymentTypes.add(pay);
		}
		return paymentTypes;
	}

	public static PaymentType getInstanceIgnoreCase(String name) {

		PaymentType[] paymentTypes = PaymentType.values();
		for (PaymentType paymentType : paymentTypes) {
			if (paymentType.getName().equalsIgnoreCase(name.replace("_", " "))) {
				return paymentType;
			}
		}
		return null;
	}

}
