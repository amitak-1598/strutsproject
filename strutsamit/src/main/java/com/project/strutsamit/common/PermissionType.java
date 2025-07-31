package com.project.strutsamit.common;

import java.util.ArrayList;
import java.util.List;

public enum PermissionType {

	LOGIN(1,"login", false, false),
	VIEW_REPORTS(2,"View Reports", true, true),
	CREATEUSER(3,"create", false, false),
	DELETEUSER(4,"delete",false, false),
	VIEW_TRANSACTIONS(5,"View Transactions", true, true),
	CAPTURE(7,"Capture", true, false),
	VOID_REFUND(8,"Void/Refund", true, false),
	CREATE_INVOICE(9,"Create Invoice", true, false),
	VIEW_INVOICE(10,"View Invoice", true, false),
	VIEW_REMITTANCE(11,"View Remittance", true, false),
	CREATE_TICKET(12, "Create Ticket", true, true),
	VIEW_MERCHANT_SETUP(13, "View MerchantSetup", true, false),
	MERCHANT_BLLING(28, "View Merchant Billing", true, false),
	VIEW_ANALYTICS(15, "View Analytics", true, true),
	VIEW_SEARCH_PAYMENT(16, "View SearchPayment", true, true),
	VIEW_RECONCILIATION(17, "View Reconciliation", true, true),
	CREATE_BATCH_OPERATION(18, "Create BatchOperation", true, false),
	FRAUD_PREVENTION(19, "Fraud Prevention", true, false),
	CREATE_BULK_EMAIL(20, "Create BulkEmail", true, false),
	VIEW_CASHBACK(21, "View CashBack", true, true),
	CREATE_HELPTIKECT(22, "Create HelpTicket", true, true),
	CREATE_MAPPING(14, "Create Mapping", true, false),
	CREATE_SURCHARGE(23, "Create Surcharge", true, false),
	CREATE_TDR(24, "Create TDR", true, false),
	CREATE_SERVICE_TAX(25, "Create Service Tax", true, false),
	CREATE_MERCHANT_MAPPING(26, "Create Merchant Mapping", true, false),
	CREATE_RESELLER_MAPPING(27, "Create Reseller Mapping", true, false),
	MERCHANT_EDIT(28, "Edit Merchant Details", true, false),
	VIEW_PENDING_REQUEST(29, "View Pending Request", true, false);
	
	private final String permission;
	private final int id;
	private final boolean isInternal;
	private final boolean isInternalValue;
	
	private PermissionType(int id,String permission,boolean isInternal,boolean isInternalValue){
		this.id = id;
		this.permission = permission;
		this.isInternal = isInternal;
		this.isInternalValue = isInternalValue;
	}
	
	public int getId() {
		return id;
	}
	public String getPermission() {
		return permission;			
	}
	
	public boolean isInternal() {
		return isInternal;
	}
	
	
	public boolean isInternalValue() {
		return isInternalValue;
	}

	public static List<PermissionType> getPermissionType(){
		List<PermissionType> permissionTypes = new ArrayList<PermissionType>();						
		for(PermissionType permissionType:PermissionType.values()){
			if(permissionType.isInternal())
				permissionTypes.add(permissionType);
		}
	  return permissionTypes;
	}
	
	public static PermissionType getInstanceFromName(String code){
		PermissionType[] permissionTypes = PermissionType.values();
		for(PermissionType permissionType : permissionTypes){
			if(String.valueOf(permissionType.getPermission()).toUpperCase().equals(code.toUpperCase())){
				return permissionType;
			}
		}		
		return null;
	}
	
	public static List<PermissionType> getSubAcquirerPermissionType(){
		List<PermissionType> permissionTypes = new ArrayList<PermissionType>();						
		for(PermissionType permissionType:PermissionType.values()){
			if(permissionType.isInternalValue())
				permissionTypes.add(permissionType);
		}
	  return permissionTypes;
	}
	
	
	public static List<PermissionType> getSubUserPermissionType(){
		List<PermissionType> permissionTypes = new ArrayList<PermissionType>();						
		for(PermissionType permissionType:PermissionType.values()){
			if(permissionType.isInternalValue())
				permissionTypes.add(permissionType);
		}
	  return permissionTypes;
	}
	
}
