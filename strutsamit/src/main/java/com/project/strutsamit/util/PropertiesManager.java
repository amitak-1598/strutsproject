package com.project.strutsamit.util;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

//import org.apache.log4j.Logger;

public class PropertiesManager {

	//private static Logger logger = Logger.getLogger(PropertiesManager.class.getName());
	
   //private static final String systemPropertiesFile = "/usr/local/props/system.properties";
   private static final String systemPropertiesFile = "C://PayV//PayvProps//system.properties";
	
	private static final String currencyFile = "currency.properties";
	private static final String currencyAlphabaticToNumericFile = "alphabatic-to-numeric.properties";
	private static final String emailPropertiesFile = "emailer.properties";
	private static Map<String, String> saltStore = new HashMap<String, String>();
	private static final String currencyNameFile = "alphabatic-currencycode.properties";
	private static final String acquirerMop = "acquirer-mop.properties";
	private static final String amexPropertiesFile ="amex.properties";
	private static final String subUserPermissionPropertiesFile ="subUserPermission.properties";
	private static final String subAdminPermissionPropertiesFile ="subAdminPermission.properties";
	
	private static final String netbankingPropertiesFile ="netbanking.properties";
	private static final String recurringPaymentsPropertiesFile ="citrusPayRecurringPayments.properties";
	private static final String industrySubcategoryPropertiesFile ="industry_sub_category.properties";
	private static final String misReportPropertiesFile ="documentdirectory .properties";
	private static final String exportMisReportPropertiesFile ="misSettlement .properties";

	public PropertiesManager() {
	}

	/**
	 * It's implementation has been changed.
	 * Now we load salt file from relocated path
	 * @param key
	 * @return
	 */
	public String getKey(String key) {
		String keyPropertiesFile = getSystemProperty(Constants.KEY_FILE_PATH_NAME.getValue());
		return getPropertyFromFileSystem(key, keyPropertiesFile);
	}


	/**
	 * system.properties has been relocated to out of project base directory
	 * to meet PCI-DSS compliance
	 * @param key
	 * @return
	 */
	public String getSystemProperty(String key){
		return getPropertyFromFileSystem(key, systemPropertiesFile);
	}

	
	public String getEmailProperty(String key){
		return getProperty(key, emailPropertiesFile);
	}

	public String getResetPasswordProperty(String key){
		return getProperty(key, emailPropertiesFile);
	}

	public String getCurrencyPlaces(String currencyCode){
		return getProperty(currencyCode, currencyFile);
	}

	public String getAmexProperty(String key){
		return getProperty(key, amexPropertiesFile);
	}

	public String getSubUserPermissionProperty(String key){
		return getProperty(key, subUserPermissionPropertiesFile);
	}

	public String getSuAdminPermissionProperty(String key){
		return getProperty(key, subAdminPermissionPropertiesFile);
	}
	
	public String getAlphabaticCurrencyCode(String numericCurrencyCode){
		return getProperty(numericCurrencyCode, currencyNameFile);
	}

	public String getNumericCurrencyCode(String alphabeticCode){
		return getProperty(alphabeticCode, currencyAlphabaticToNumericFile);
	}

	public String getAcquirerMopType(String key){
		return getProperty(key, acquirerMop);
	}

	public String getNetbankingProperty(String key){
		return getProperty(key, netbankingPropertiesFile);
	}

	public String getCitrusRecurringPaymentProperty(String key){
		return getProperty(key, recurringPaymentsPropertiesFile);
	}

	public String getIndustrySubcategories(String category){
		return getProperty(category, industrySubcategoryPropertiesFile);
	}
    public String getMisReports(String file ){
        return getProperty(file, misReportPropertiesFile);
    }
    
    public String getMisAllAcquireExportCSV(String file ){
        return getProperty(file, exportMisReportPropertiesFile);
    }
    
    /**
	 * It's implementation has been changed.
	 * Now we load salt file from relocated path
	 * @param appId
	 * @return
	 */
	public String getSalt(String appId){
		String salt = saltStore.get(appId);
		if(null == salt || salt.isEmpty()){
			String saltPropertiesFile = getSystemProperty(Constants.SALT_FILE_PATH_NAME.getValue());
			salt = getPropertyFromFileSystem(appId, saltPropertiesFile);
			if(null != salt && !salt.isEmpty()){
				saltStore.put(appId, salt);
			}
		}
		
		return salt;
	}
	
	public boolean setKey(String key, String value){
		String keyPropertiesFile = getSystemProperty(Constants.KEY_FILE_PATH_NAME.getValue());
		return setProperty(key, value, keyPropertiesFile);
	}
	
	
	protected boolean setProperty(String key, String value, String fileName) {

		boolean result = true;
		Properties prop = new Properties();
		OutputStream output = null;

		try {
			
			FileInputStream input = new FileInputStream(fileName);
			prop.load(input);
			input.close();
			
			output = new FileOutputStream(fileName);
			prop.setProperty(key, value);
			
			prop.store(output, null);

		} catch (IOException ioException) {
			//logger.error("Unable to update properties file = " + fileName + ", Details = " + ioException.getMessage(), ioException);			
			result = false;
		} finally {
			if (output != null) {
				try {
					output.close();
				} catch (IOException ioException) {
				//	logger.error("Unable to update properties file = " + fileName + ", Details = " + ioException.getMessage(), ioException);
					result = false;
				}
			}
		}
		
		return result;
	}

	
	public Map<String, String> getAllProperties(String fileName){
		Map<String, String> responseMap = new HashMap<String, String>();
		Properties prop = new Properties();
		InputStream input = null;

		try {

			input = this.getClass().getClassLoader().getResourceAsStream(fileName);

			// load a properties file
			prop.load(input);
			
			for(Object key : prop.keySet()){
				responseMap.put(key.toString(), prop.getProperty(key.toString()));
			}

		} catch (IOException ioException) {
	//		logger.error("Unable to update properties file = " + fileName + ", Details = " + ioException.getMessage(), ioException);
		} finally {
			if (input != null) {
				try {
					input.close();
				} catch (IOException ioException) {
	//				logger.error("Unable to update properties file = " + fileName + ", Details = " + ioException.getMessage(), ioException);
				}
			}
		}

		return responseMap;
	}//getAllProperties()

	private String getProperty(String key, String fileName) {

		Properties prop = new Properties();
		InputStream input = null;
		String value = null;

		try {

			input = this.getClass().getClassLoader().getResourceAsStream(fileName);
			
			if(null == input && fileName.contains("/")){
				//This is to handle the case when complete filename is present
				int index = fileName.lastIndexOf("/");
				fileName = fileName.substring(index + 1);
				input = this.getClass().getClassLoader().getResourceAsStream(fileName);
			}

			// load a properties file
			prop.load(input);
     //       logger.info(prop);
      //      logger.info(key);
			// get the property value
			value = prop.getProperty(key);

		} catch (IOException ioException) {
//			logger.error("Unable to update properties file = " + fileName + ", Details = " + ioException.getMessage(), ioException);
		} catch(NullPointerException npe){
//			logger.error("property file error " + npe);
		}
			finally {
		
			if (input != null) {
				try {
					input.close();
				} catch (IOException ioException) {
//					logger.error("Unable to update properties file = " + fileName + ", Details = " + ioException.getMessage(), ioException);
				}
			}
		}

		return value;
	}

	public static String getSystempropertiesfile() {
		return systemPropertiesFile;
	}


	public static String getCurrencyfile() {
		return currencyFile;
	}

	public static String getEmailpropertiesfile() {
		return emailPropertiesFile;
	}

	public static String getAmexpropertiesfile() {
		return amexPropertiesFile;
	}

	public static String getSubuserpermissionpropertiesfile() {
		return subUserPermissionPropertiesFile;
	}

	public static String getNetbankingpropertiesfile() {
		return netbankingPropertiesFile;
	}

	
	/**
	 * to get property value from props file relocated to out of project base directory
	 * @param key
	 * @param fileName
	 * @return
	 */
	protected String getPropertyFromFileSystem(String key, String fileName){
		String value = null;
		Properties properties = new Properties();
		try {
			FileInputStream input = new FileInputStream(fileName);
			properties.load(input);
			input.close();
			value = properties.getProperty(key, null);
		} catch (IOException ioException) {
//			logger.error("Unable to load properties file = " + fileName + ", Details = " +ioException.getMessage(), ioException);
		}
		return value;
	}
	
}
