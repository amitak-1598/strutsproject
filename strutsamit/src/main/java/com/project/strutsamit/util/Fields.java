package com.project.strutsamit.util;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import org.apache.commons.lang3.StringUtils;

import com.project.strutsamit.common.exception.SystemException;
//import org.apache.log4j.Logger;
//import org.apache.log4j.MDC;



public class Fields {

	private Map<String, Object> sessionMap;
	private Map<String, String> fields = new HashMap<String, String>();
	private FieldsDao fieldsDao = new FieldsDao();
	private Fields previous = null;
	private boolean valid = true;
//	private static Logger logger = Logger.getLogger(Fields.class.getName());

	public Fields(Map<String, String> fields) {
		this.fields.putAll(fields);
	}

	public Fields() {
	}

	public void updateNewOrderDetails() throws SystemException{
		fieldsDao.updateNewOrderDetails(this);
	}

	public void updateStatus() throws SystemException{
		fieldsDao.updateStatus(this);
	}

	public void checkDuplicate() throws SystemException{
		fieldsDao.getDuplicate(this);
	}

	public void insert() throws SystemException{
		fieldsDao.insert(this);
	}
	public void insertNewOrder() throws SystemException{
		fieldsDao.insertNewOrder(this);
	}
	public void updateCurrentTransaction() throws SystemException{
		fieldsDao.updateCurrentTransaction(this);
	}

	public Fields refreshPrevious() throws SystemException{
		String txnId = fields.get(FieldType.ORIG_TXN_ID.getName());
		String appId = fields.get(FieldType.APP_ID.getName());
		previous = fieldsDao.getFields(txnId, appId);
		return previous;
	}
	
	public void put(Fields fields){
		this.fields.putAll(fields.getFields());
	}

	public Fields(Fields fields) {
		this.fields.putAll(fields.getFields());
	}

	public void putAll(Map<String, String> fields_) {		
		this.fields.putAll(fields_);
	}

	public Map<String, String> getFields() {
		return new HashMap<String, String>(fields);		
	}

	public void setFields(Map<String, String> fields) {
		this.fields = fields;
	}

	public void put(String key, String value) {
		fields.put(key, value);
	}

	public String get(String key) {
		return fields.get(key);
	}

	public Set<String> keySet() {
		return fields.keySet();
	}

	public String remove(String key) {
		return fields.remove(key);
	}

	//Remove keys when value/key is null/spaces
	public void clean(){
		Map<String, String> validFields = new HashMap<String, String>();
		for (String key : fields.keySet()) {
			
			String value = fields.get(key);
			if(null == value){
				continue;
			}
			key.trim();
			value.trim();

			if (!key.isEmpty() && !value.isEmpty()) {
				validFields.put(key, value);
			}
		}

		fields.clear();
		fields.putAll(validFields);
	}
	
	public void clear() {
		fields.clear();
	}

	public void removeExtraFields() {

		// All the fields which are not configured to be allowed are ignored
		Collection<String> allowedRequestFields = SystemProperties.getRequestfields();
		Map<String, String> validFields = new HashMap<String, String>();
		for (String key : fields.keySet()) {
			if (allowedRequestFields.contains(key)) {
				validFields.put(key, fields.get(key));
			}
		}

		fields.clear();
		fields.putAll(validFields);
	}// removeExtraFields()

	public void trimAndToUpper() {
		Map<String, String> validFields = new HashMap<String, String>();
		for (String key : fields.keySet()) {
			String value = fields.get(key);
			key.trim();
			value.trim();

			if (!key.isEmpty() && !value.isEmpty()) {
				validFields.put(key.toUpperCase(), value.toUpperCase());
			}
		}

		fields.clear();
		fields.putAll(validFields);
	}// trimAndToUpper()
	
	public void removeSecureFieldsSubmitted(){
		for(String secureField: SystemProperties.getSecureRequestFields()){
			fields.remove(secureField);
		}
	}

	public FieldsDao getFieldsDao() {
		return fieldsDao;
	}

	public void setFieldsDao(FieldsDao fieldsDao) {
		this.fieldsDao = fieldsDao;
	}

	public Fields getPrevious() {
		return previous;
	}

	public void setPrevious(Fields previous) {
		this.previous = previous;
	}

	public boolean isValid() {
		return valid;
	}

	public void setValid(boolean valid) {
		this.valid = valid;
	}
	
	public int size(){
		return fields.size();
	}
	
	public Map<String, String> removeSecureFields(){
		
		Map<String, String> secureFields = new HashMap<String, String>();
		
		String cardNumber = remove(FieldType.CARD_NUMBER.getName());
		if(null != cardNumber){
			secureFields.put(FieldType.CARD_NUMBER.getName(), cardNumber);
		}
		
		String cardExpiryDate = remove(FieldType.CARD_EXP_DT.getName());
		if(null != cardExpiryDate){
			secureFields.put(FieldType.CARD_EXP_DT.getName(), cardExpiryDate);
		}
		
		String cvv = remove(FieldType.CVV.getName());
		if(null != cvv){
			secureFields.put(FieldType.CVV.getName(), cvv);
		}
		
		String password = remove(FieldType.PASSWORD.getName());
		if(null != password){
			secureFields.put(FieldType.PASSWORD.getName(), password);
		}
		
		return secureFields;
	}
	
	public String getFieldsAsString() {

		StringBuilder allFieldsSum = new StringBuilder();
		allFieldsSum.append("\n");
		for (String key : fields.keySet()) {
			allFieldsSum.append(key);
			allFieldsSum.append("=");
			allFieldsSum.append(fields.get(key));
			allFieldsSum.append("~");
		}

		return allFieldsSum.toString();
	}
	
	public String getFieldsAsBlobString() {

		StringBuilder allFieldsSum = new StringBuilder();
		allFieldsSum.append("\n");
		for (String key : fields.keySet()) {
			if(key.equals(FieldType.CARD_NUMBER.getName())) {
				continue;
			}
			else if(key.equals(FieldType.CARD_EXP_DT.getName())) {
				continue;
			}
			else if(key.equals(FieldType.CVV.getName())) {
				continue;
			}
			allFieldsSum.append(key);
			allFieldsSum.append("=");
			allFieldsSum.append(fields.get(key));
			allFieldsSum.append("~");
			
		}

		return allFieldsSum.toString();
	}
	
	public void logAllFields(String message){
				
		//Do not log card details, as this is a security issue
		Map<String, String> secureFields = removeSecureFields();
		
		StringBuilder allFieldsSum = new StringBuilder();
		allFieldsSum.append(message);
		allFieldsSum.append("\n");
		for(String key: fields.keySet()){
			allFieldsSum.append(key);
			allFieldsSum.append(" = ");
			allFieldsSum.append(fields.get(key));
			allFieldsSum.append("~");
		}
		
		//Put secure details back in the collection
		putAll(secureFields);		
		
//		MDC.put(FieldType.INTERNAL_CUSTOM_MDC.getName(), this.getCustomMDC());
//		logger.info(allFieldsSum.toString());
	}
	
	public void addDefaultFields() throws SystemException{
		fields.put(FieldType.STATUS.getName(), StatusType.PENDING.getName());
		
		String transactionId = fields.get(FieldType.TXN_ID.getName());
		if(StringUtils.isEmpty(transactionId)){
			fields.put(FieldType.TXN_ID.getName(), TransactionManager.getNewTransactionId());	
		}

		fields.put(FieldType.KEY_ID.getName(), SystemConstants.DEFAULT_KEY_ID);
		//Changes by @Neeraj removed acquirer addtion code as it is done later in addAcquirerFields method
	}
	
	public Fields removeInternalFields(){
		Fields internalFields = new Fields();
		
		for(String key : fields.keySet()){
			if(key.startsWith(SystemConstants.FIELDS_PREFIX)){
				internalFields.put(key, fields.get(key)); 
			}
		}
		
		for(String key: internalFields.keySet()){
			fields.remove(key);
		}
		
		
		return internalFields;
	}//removeInternalFields()
		
	public Fields removeVpcFields(){
		Fields internalFields = new Fields();
		
		for(String key : fields.keySet()){
			if(key.startsWith(SystemConstants.VPC_PREFIX)){
				internalFields.put(key, fields.get(key)); 
			}
		}
		
		for(String key: internalFields.keySet()){
			fields.remove(key);
		}
				
		return internalFields;
	}//removeInternalVPCFields()
	
	public void updateForAuthorization() throws SystemException{
		fieldsDao.insertTransaction(this);
		fieldsDao.updateNewOrder(this);
	}
	
	public void updateTransactionDetails() throws SystemException{
		fieldsDao.updateForAuthorization(this);		
	}

	public String getCustomMDC(){
		String customMdc = fields.get(FieldType.INTERNAL_CUSTOM_MDC.getName());
		if(null == customMdc){
			StringBuilder mdcBuilder = new StringBuilder();
			mdcBuilder.append(Constants.PG_LOG_PREFIX.getValue());
			mdcBuilder.append(FieldType.ORDER_ID.getName());
			mdcBuilder.append(Constants.EQUATOR.getValue());
			mdcBuilder.append(fields.get(FieldType.ORDER_ID.getName()));
			mdcBuilder.append(Constants.SEPARATOR.getValue());
			mdcBuilder.append(FieldType.APP_ID.getName());
			mdcBuilder.append(Constants.EQUATOR.getValue());
			mdcBuilder.append(fields.get(FieldType.APP_ID.getName()));
			mdcBuilder.append(Constants.SEPARATOR.getValue());
			mdcBuilder.append(FieldType.TXN_ID.getName());
			mdcBuilder.append(Constants.EQUATOR.getValue());
			mdcBuilder.append(fields.get(FieldType.TXN_ID.getName()));

			customMdc = mdcBuilder.toString();
			fields.put(FieldType.INTERNAL_CUSTOM_MDC.getName(), customMdc);
		}
		return customMdc;
	}
	
	//to check a specific key-value exists or not
	public boolean contains(String fieldName){
		return (fields.containsKey(fieldName) ? true :false);
	}

	public Map<String, Object> getSessionMap() {
		return sessionMap;
	}

	public void setSessionMap(Map<String, Object> sessionMap) {
		this.sessionMap = sessionMap;
	}
}
