package com.project.strutsamit.common;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;


import com.project.strutsamit.util.Helper;
import com.project.strutsamit.util.PropertiesManager;

public enum TransactionType {

	NEWORDER			("NEWORDER", "NEW"),
	AUTHORISE			("AUTHORISE", "AUTH"),
	SALE				("SALE", "SALE"),
	CAPTURE				("CAPTURE", "CAPT"),
	SETTLE				("SETTLE", "SETT"),
	ENROLL				("ENROLL", "ENRO"),
	STATUS				("STATUS", "STAT"),
	REFUND				("REFUND", "REFU"), 
	ENQUIRY				("ENQUIRY", "ENQY"),
	INVALID				("INVALID", "INV"),
	RECO				("RECO", "RECO"),
	REFUNDRECO			("REFUNDRECO", "REFRECO"),
	VERIFY				("VERIFY", "VER");
	
	private final String name;
	private final String code;
	
	private TransactionType(String name, String code){
		this.name = name;
		this.code = code;
	}

	public String getName() {
		return name;
	}
	
	public String getCode(){
		return code;
	}
	
	public static TransactionType getInstance(String name){
		TransactionType[] transactionTypes = TransactionType.values();
		for(TransactionType transactionType : transactionTypes){
			if(transactionType.getName().equals(name)){
				return transactionType;
			}
		}
		
		return null;
	}
	
	public static TransactionType getInstanceFromCode(String code){
		TransactionType[] transactionTypes = TransactionType.values();
		for(TransactionType transactionType : transactionTypes){
			if(transactionType.getCode().equals(code)){
				return transactionType;
			}
		}
		
		return null;
	}

	public static Set<MopTransaction> makeMopTxnSet(String[] txns){
		Set<MopTransaction> moptxns = new HashSet<MopTransaction>();
		
		for(String txnType:txns){
			MopTransaction moptxn = new MopTransaction();
			moptxn.setTransactionType(TransactionType.getInstance(txnType));
			moptxns.add(moptxn);
		}
		return moptxns;
	}
	
	public static List<TransactionType> chargableMopTxn(){
		List<TransactionType> txnTypes = new ArrayList<TransactionType>();
		PropertiesManager propertiesManager= new PropertiesManager();
		
		List<String> txnTypeStringList= (List<String>) Helper.parseFields(propertiesManager.getAcquirerMopType("TXNTYPE"));
					
		for(String txnType:txnTypeStringList){
			TransactionType txnTypeInstance = getInstanceFromCode(txnType);
			txnTypes.add(txnTypeInstance);
		}
		return txnTypes;
	}
}
