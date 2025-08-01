package com.project.strutsamit.util;

import java.util.ArrayList;
import java.util.List;


public enum StatusType {

	APPROVED				(0, "Approved",       false),
	DECLINED				(1, "Declined",       false),
	REJECTED				(2, "Rejected",       false),
	PENDING					(3, "Pending",        true),
	CAPTURED				(4, "Captured",       false),
	ERROR					(5, "Error",          false),	
	TIMEOUT					(6, "Timeout",        false),
	SETTLED					(7, "Settled",        false),
	BROWSER_CLOSED			(8, "Browser Closed", false),
	CANCELLED				(9, "Cancelled",      false),
	DENIED					(10, "Denied by risk",false),
	ENROLLED				(11, "Enrolled",      true),	
	DUPLICATE				(12, "Duplicate",     false),
	FAILED					(13, "Failed",        false),
	INVALID					(14, "Invalid",       false),
	AUTHENTICATION_FAILED	(15, "Authentication Failed", false),
	SENT_TO_BANK           	(16, "Sent to Bank", false),
	DENIED_BY_FRAUD		   	(17, "Denied due to fraud", false),
	FAILED_AT_ACQUIRER		(18, "Failed at acquire", false),
	AUTO_REVERSAL		    (19, " Auto Reversal", false);

	private final int code;
	private final String name;
	private final boolean isInternal;
	
	private StatusType(int code, String name,boolean isInternal){
		this.code = code;
		this.name = name;
		this.isInternal = isInternal;
	}

	public int getCode() {
		return code;
	}

	public String getName() {
		return name;
	}
	
	public boolean isInternal() {
		return isInternal;
	}	
	
	public static List<StatusType> getStatusType(){
		List<StatusType> statusTypes = new ArrayList<StatusType>();						
		for(StatusType statusType:StatusType.values()){
			if(!statusType.isInternal())
			statusTypes.add(statusType);
		}
	  return statusTypes;
	}
	
	public static StatusType getInstanceFromName(String code){
		StatusType[] statusTypes = StatusType.values();
		for(StatusType statusType : statusTypes){
			if(String.valueOf(statusType.getName()).toUpperCase().equals(code)){
				return statusType;
			}
		}		
		return null;
	}
}
