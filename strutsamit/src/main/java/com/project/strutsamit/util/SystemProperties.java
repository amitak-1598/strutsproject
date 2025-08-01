package com.project.strutsamit.util;

import java.util.Collection;

public class SystemProperties {

	private static final boolean debug = checkDebug();
	private static final Collection<String> responseFields = createResponseFields();
	private static final Collection<String> secureRequestFields = getSecureRequestFieldsFromConfigFile();
	private static final Collection<String> requestFields = getRequestFields();
	private static final Collection<String> fssEnrollMandatoryFields = getFssEnrollMandatoryFields();
	
	public SystemProperties() {
	}

	private static Collection<String> getSecureRequestFieldsFromConfigFile() {
		return Helper.parseFields(ConfigurationConstants.SECURE_REQUEST_FIELDS.getValue());
	}
	private static boolean checkDebug(){
		if(ConfigurationConstants.IS_DEBUG.getValue().equals("1")){
			return true;
		} else {
			return false;
		}
	}
	
	public static Collection<String> getAllDBRequestFields() {
		return Helper.parseFields(ConfigurationConstants.DB_ALLREQUESTSTRINGFIELDS.getValue());
	}
	
	private static Collection<String> getRequestFields() {
		return Helper.parseFields(ConfigurationConstants.REQUEST_FIELDS.getValue());
	}
	
	private static Collection<String> getFssEnrollMandatoryFields(){
		return Helper.parseFields(ConfigurationConstants.FSS_MANDATORY_FIELDS_ENROLL.getValue());
	}
	
	public static Collection<String> getSecureRequestFields() {
		return secureRequestFields;
	}
	
	public static Collection<String> getSecurerequestfields() {
		return secureRequestFields;
	}

	public static boolean isDebug() {
		return debug;
	}
	
	private static Collection<String> createResponseFields() {
		return Helper.parseFields(ConfigurationConstants.RESPONSE_FIELDS.getValue());
	}

	public static Collection<String> getResponseFields() {
		return responseFields;
	}

	public static Collection<String> getRequestfields() {
		return requestFields;
	}

	public static Collection<String> getFssenrollmandatoryfields() {
		return fssEnrollMandatoryFields;
	}
}
