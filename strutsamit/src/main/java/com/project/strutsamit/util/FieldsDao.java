package com.project.strutsamit.util;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collection;
import java.util.Set;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.lang3.StringUtils;

import com.project.strutsamit.common.TransactionType;
import com.project.strutsamit.common.dao.DataAccessObject;
import com.project.strutsamit.common.exception.ErrorType;
import com.project.strutsamit.common.exception.SystemException;
//import org.apache.log4j.Logger;
//import org.apache.log4j.MDC;
import com.project.strutsamit.crm.core.Amount;



public class FieldsDao {
	// All static fields
//	private static Logger logger = Logger.getLogger(FieldsDao.class.getName());
	private static final Collection<String> allDBRequestFields = SystemProperties.getAllDBRequestFields();
	private static final String allFieldsQuery = createSQLQueryForAllSelect();
	private static final String allFieldsInsertQuery = insertQuery();
	public static final String duplicateDetectionQuery = "SELECT TXN_ID FROM TRANSACTION WHERE ORDER_ID = ? AND APP_ID = ? AND ( STATUS = 'Captured' OR STATUS = 'Approved' ) "
			+ " AND AMOUNT = ? AND (TXNTYPE = 'NEWORDER' OR TXNTYPE = 'SALE' OR TXNTYPE = 'AUTHORISE')";
	public static final String AUTHORIZATION_UPDATE_QUERY = "UPDATE TRANSACTION SET "
			+ "ACQ_ID = ?, "
			+ "RRN = ?, "
			+ "PG_REF_NUM = ?, "
			+ "AUTH_CODE = ?, "
			+ "PG_RESP_CODE = ?, "
			+ "PG_TXN_MESSAGE = ?, "
			+ "PG_TXN_STATUS = ?, "
			+ "PG_DATE_TIME = ?, "
			+ "PG_GATEWAY = ?, "
			+ "STATUS = ?, "
			+ "RESPONSE_CODE = ?, "
			+ "RESPONSE_MESSAGE = ?, "
			+ "TXNTYPE = ? " + "WHERE " + "TXN_ID = ?";

	public static final String UPDATE_STATUS = "UPDATE TRANSACTION SET "
			+ "STATUS = ? ," + "RESPONSE_CODE = ? ," + "RESPONSE_MESSAGE = ? "
			+ " WHERE TXN_ID = ?";
	public static final String UPDATE_NEW_ORDER = "UPDATE TRANSACTION SET "
			+ "STATUS = ? ," + "RESPONSE_CODE = ? ," + "RESPONSE_MESSAGE = ? ,"
			+ "CARD_MASK =? ," + "PAYMENT_TYPE =? ," + "MOP_TYPE =? ,"
			+ "ACQUIRER_TYPE=? ," + "PG_TXN_MESSAGE=? ," + "AMOUNT=? ,"+ "SURCHARGE_FLAG=? ,"+ "SURCHARGE_AMOUNT=? ,"+"INTERNAL_TXN_CHANNEL =? "+ " WHERE TXN_ID = ?";

	public FieldsDao() {
	}

	public void updateNewOrderDetails(Fields fields) throws SystemException {

		try (Connection connection = getConnection()) {
			try (PreparedStatement updateStatement = connection
					.prepareStatement(UPDATE_NEW_ORDER)) {
				String amountString = fields.get(FieldType.AMOUNT.getName());
				String surchargeAmountString = fields.get(FieldType.SURCHARGE_AMOUNT.getName());
				String currencyString = fields.get(FieldType.CURRENCY_CODE
						.getName());

				String amount = "0";
				if (!StringUtils.isEmpty(amountString)
						&& !StringUtils.isEmpty(currencyString)) {
					amount = Amount.toDecimal(amountString, currencyString);
				}
				String surchargeAmount = "0";
				if (!StringUtils.isEmpty(surchargeAmountString)
						&& !StringUtils.isEmpty(currencyString)) {
					surchargeAmount = Amount.toDecimal(surchargeAmountString, currencyString);
				}
				// set the updateStatement parameters
				updateStatement.setString(1,
						fields.get(FieldType.STATUS.getName()));
				updateStatement.setString(2,
						fields.get(FieldType.RESPONSE_CODE.getName()));
				updateStatement.setString(3,
						fields.get(FieldType.RESPONSE_MESSAGE.getName()));
				updateStatement.setString(4,
						fields.get(FieldType.CARD_MASK.getName()));
				updateStatement.setString(5,
						fields.get(FieldType.PAYMENT_TYPE.getName()));
				updateStatement.setString(6,
						fields.get(FieldType.MOP_TYPE.getName()));
				updateStatement.setString(7,
						fields.get(FieldType.ACQUIRER_TYPE.getName()));
				updateStatement.setString(8,
						fields.get(FieldType.PG_TXN_MESSAGE.getName()));
				updateStatement.setString(9, amount);
				updateStatement.setString(10,
						fields.get(FieldType.SURCHARGE_FLAG.getName()));
				updateStatement.setString(11, surchargeAmount);
				updateStatement.setString(12,
						fields.get(FieldType.INTERNAL_TXN_CHANNEL.getName()));
				updateStatement.setLong(13, Long.parseLong(fields
						.get(FieldType.INTERNAL_ORIG_TXN_ID.getName())));

				int updateCount = updateStatement.executeUpdate();
				if (1 != updateCount) {
//					MDC.put(FieldType.INTERNAL_CUSTOM_MDC.getName(),fields.getCustomMDC());
//					logger.error("Unable to update original transaction status!");
					throw new SystemException(ErrorType.DATABASE_ERROR,
							"Unable to update orginal transaction status, updateCount = "
									+ updateCount);
				}
			}

		} catch (SQLException sqlException) {
			throw new SystemException(ErrorType.DATABASE_ERROR, sqlException,
					"Unable to update orginal transaction while updating status!");
		}
	}

	public void updateStatus(Fields fields) throws SystemException {

		try (Connection connection = getConnection()) {
			try (PreparedStatement updateStatement = connection
					.prepareStatement(UPDATE_STATUS)) {

				// set the updateStatement parameters
				updateStatement.setString(1,
						fields.get(FieldType.STATUS.getName()));
				updateStatement.setString(2,
						fields.get(FieldType.RESPONSE_CODE.getName()));
				updateStatement.setString(3,
						fields.get(FieldType.RESPONSE_MESSAGE.getName()));
				updateStatement.setLong(4, Long.parseLong(fields
						.get(FieldType.INTERNAL_ORIG_TXN_ID.getName())));

				int updateCount = updateStatement.executeUpdate();
				if (1 != updateCount) {
//					MDC.put(FieldType.INTERNAL_CUSTOM_MDC.getName(),fields.getCustomMDC());
//					logger.error("Unable to update original transaction status!");
					throw new SystemException(ErrorType.DATABASE_ERROR,
							"Unable to update orginal transaction status, updateCount = "
									+ updateCount);
				}
			}

		} catch (SQLException sqlException) {
			throw new SystemException(ErrorType.DATABASE_ERROR, sqlException,
					"Unable to update orginal transaction while updating status!");
		}
	}

	public void updateCurrentTransaction(Fields fields) throws SystemException {

		try (Connection connection = getConnection()) {
			try (PreparedStatement updateStatement = connection
					.prepareStatement(UPDATE_STATUS)) {

				// set the updateStatement parameters
				updateStatement.setString(1,
						fields.get(FieldType.STATUS.getName()));
				updateStatement.setString(2,
						fields.get(FieldType.RESPONSE_CODE.getName()));
				updateStatement.setString(3,
						fields.get(FieldType.RESPONSE_MESSAGE.getName()));
				updateStatement.setLong(4,
						Long.parseLong(fields.get(FieldType.TXN_ID.getName())));

				int updateCount = updateStatement.executeUpdate();
				if (1 != updateCount) {
//					MDC.put(FieldType.INTERNAL_CUSTOM_MDC.getName(),fields.getCustomMDC());
//					logger.error("Unable to update original transaction status!");
					throw new SystemException(ErrorType.DATABASE_ERROR,
							"Unable to update orginal transaction status, updateCount = "
									+ updateCount);
				}
			}

		} catch (SQLException sqlException) {
			throw new SystemException(ErrorType.DATABASE_ERROR, sqlException,
					"Unable to update orginal transaction while updating status!");
		}
	}

	public void updateForAuthorization(Fields fields) throws SystemException {

		try (Connection connection = getConnection()) {
			try (PreparedStatement updateStatement = connection
					.prepareStatement(AUTHORIZATION_UPDATE_QUERY)) {

				// set the updateStatement parameters
				updateStatement.setString(1,
						fields.get(FieldType.ACQ_ID.getName()));
				updateStatement.setString(2,
						fields.get(FieldType.RRN.getName()));
				updateStatement.setString(3,
						fields.get(FieldType.PG_REF_NUM.getName()));
				updateStatement.setString(4,
						fields.get(FieldType.AUTH_CODE.getName()));
				updateStatement.setString(5,
						fields.get(FieldType.PG_RESP_CODE.getName()));
				updateStatement.setString(6,
						fields.get(FieldType.PG_TXN_MESSAGE.getName()));
				updateStatement.setString(7,
						fields.get(FieldType.PG_TXN_STATUS.getName()));
				updateStatement.setString(8,
						fields.get(FieldType.PG_DATE_TIME.getName()));
				updateStatement.setString(9,
						fields.get(FieldType.PG_GATEWAY.getName()));
				updateStatement.setString(10,
						fields.get(FieldType.STATUS.getName()));
				updateStatement.setString(11,
						fields.get(FieldType.RESPONSE_CODE.getName()));
				updateStatement.setString(12,
						fields.get(FieldType.RESPONSE_MESSAGE.getName()));
				updateStatement.setString(13,
						fields.get(FieldType.INTERNAL_ORIG_TXN_TYPE.getName()));
				updateStatement.setLong(14,
						Long.parseLong(fields.get(FieldType.TXN_ID.getName())));

				int updateCount = updateStatement.executeUpdate();
				if (1 != updateCount) {
//					MDC.put(FieldType.INTERNAL_CUSTOM_MDC.getName(),fields.getCustomMDC());
					String message = "Unable to update orginal transaction, updateCount = "+ updateCount;
//					logger.error(message);
					throw new SystemException(ErrorType.DATABASE_ERROR, message);
				}
			}
		} catch (SQLException sqlException) {
			throw new SystemException(ErrorType.DATABASE_ERROR, sqlException,
					"Unable to update orginal transaction!");
		}

	}// updateForAuthorization()

	private static String createSQLQueryForAllSelect() {
		StringBuilder sqlQueryBuilder = new StringBuilder();
		sqlQueryBuilder.append("SELECT ");
		sqlQueryBuilder.append(ConfigurationConstants.DB_ALLREQUESTSTRINGFIELDS
				.getValue());
		sqlQueryBuilder.append(" FROM TRANSACTION WHERE ");
		sqlQueryBuilder.append(FieldType.TXN_ID.getName());
		sqlQueryBuilder.append(" = ? AND ");
		sqlQueryBuilder.append(FieldType.APP_ID.getName());
		sqlQueryBuilder.append(" = ?");

		return sqlQueryBuilder.toString();
	}

	private static String insertQuery() {
		StringBuilder query = new StringBuilder();

		query.append("INSERT INTO TRANSACTION ( ");
		query.append(FieldType.CARD_MASK.getName());
		query.append(SystemConstants.COMMA);
		query.append(FieldType.TXN_ID.getName());
		query.append(SystemConstants.COMMA);
		query.append(FieldType.TXNTYPE.getName());
		query.append(SystemConstants.COMMA);
		query.append(FieldType.CUST_NAME.getName());
		query.append(SystemConstants.COMMA);
		query.append(FieldType.AMOUNT.getName());
		query.append(SystemConstants.COMMA);
		query.append(FieldType.ORDER_ID.getName());
		query.append(SystemConstants.COMMA);
		query.append(FieldType.APP_ID.getName());
		query.append(SystemConstants.COMMA);
		query.append(FieldType.MOP_TYPE.getName());
		query.append(SystemConstants.COMMA);
		query.append(FieldType.CURRENCY_CODE.getName());
		query.append(SystemConstants.COMMA);
		query.append(FieldType.STATUS.getName());
		query.append(SystemConstants.COMMA);
		query.append(FieldType.RESPONSE_CODE.getName());
		query.append(SystemConstants.COMMA);
		query.append(FieldType.RESPONSE_MESSAGE.getName());
		query.append(SystemConstants.COMMA);
		query.append(FieldType.ACQ_ID.getName());
		query.append(SystemConstants.COMMA);
		query.append(FieldType.INTERNAL_CUST_IP.getName());
		query.append(SystemConstants.COMMA);
		query.append(FieldType.INTERNAL_CUST_COUNTRY_NAME.getName());
		query.append(SystemConstants.COMMA);
		query.append(FieldType.CUST_EMAIL.getName());
		query.append(SystemConstants.COMMA);
		query.append(FieldType.ORIG_TXN_ID.getName());
		query.append(SystemConstants.COMMA);
		query.append(FieldType.ACCT_ID.getName());
		query.append(SystemConstants.COMMA);
		query.append(FieldType.PAYMENT_TYPE.getName());
		query.append(SystemConstants.COMMA);
		query.append(FieldType.RRN.getName());
		query.append(SystemConstants.COMMA);
		query.append(FieldType.ACQUIRER_TYPE.getName());
		query.append(SystemConstants.COMMA);
		query.append(FieldType.PRODUCT_DESC.getName());
		query.append(SystemConstants.COMMA);
		query.append(FieldType.AUTH_CODE.getName());
		query.append(SystemConstants.COMMA);
		query.append(FieldType.PG_DATE_TIME.getName());
		query.append(SystemConstants.COMMA);
		query.append(FieldType.INTERNAL_REQUEST_FIELDS.getName());
		query.append(SystemConstants.COMMA);
		query.append(FieldType.PG_RESP_CODE.getName());
		query.append(SystemConstants.COMMA);
		query.append(FieldType.PG_TXN_MESSAGE.getName());
		query.append(SystemConstants.COMMA);
		query.append(FieldType.OID.getName());
		query.append(SystemConstants.COMMA);
		query.append(FieldType.PG_REF_NUM.getName());
		query.append(SystemConstants.COMMA);
		query.append(FieldType.INTERNAL_TXN_AUTHENTICATION.getName());
		query.append(SystemConstants.COMMA);
		query.append(FieldType.INTERNAL_CARD_ISSUER_BANK.getName());
		query.append(SystemConstants.COMMA);
		query.append(FieldType.INTERNAL_CARD_ISSUER_COUNTRY.getName());
		query.append(SystemConstants.COMMA);
		query.append(FieldType.INTERNAL_USER_EMAIL.getName());
		query.append(SystemConstants.COMMA);
		query.append(FieldType.IS_RECURRING.getName());
		query.append(SystemConstants.COMMA);
		query.append(FieldType.RECURRING_TRANSACTION_COUNT.getName());
		query.append(SystemConstants.COMMA);
		query.append(FieldType.RECURRING_TRANSACTION_INTERVAL.getName());
		query.append(SystemConstants.COMMA);
		query.append(FieldType.SURCHARGE_FLAG.getName());
		query.append(SystemConstants.COMMA);
		query.append(FieldType.SURCHARGE_AMOUNT.getName());
		query.append(SystemConstants.COMMA);
		query.append(FieldType.INTERNAL_TXN_CHANNEL.getName());
		query.append(SystemConstants.COMMA);
		query.append(FieldType.CREATE_DATE.getName());
		query.append(SystemConstants.COMMA);
		query.append(FieldType.UPDATE_DATE.getName());
		query.append(SystemConstants.COMMA);
		
		query.append(FieldType.PG_GATEWAY.getName());
		query.append(SystemConstants.COMMA);
		
		query.append(FieldType.UPI.getName());
		query.append(SystemConstants.COMMA);
		query.append(FieldType.INTERNAL_CURRENCY_CHANGE_RATE.getName());
		query.append(" )");

		query.append(" VALUES ( ");

		query.append("?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?,?,?,?");
		query.append(" )");

		return query.toString();
	}

	public Connection getConnection() throws SQLException {
		return DataAccessObject.getBasicConnection();
	}

	public void getDuplicate(Fields fields) throws SystemException {

		String orderId = fields.get(FieldType.ORDER_ID.getName());
		String appId = fields.get(FieldType.APP_ID.getName());

		try (Connection connection = getConnection()) {
			try (PreparedStatement preparedStatement = connection
					.prepareStatement(duplicateDetectionQuery)) {
				preparedStatement.setString(1, orderId);
				preparedStatement.setString(2, appId);

				String currencyString = fields.get(FieldType.CURRENCY_CODE
						.getName());

				String amount = fields.get(FieldType.AMOUNT.getName());

				if (!StringUtils.isEmpty(amount)
						&& !StringUtils.isEmpty(currencyString)) {
					amount = Amount.toDecimal(amount, currencyString);
				}

				preparedStatement.setBigDecimal(3, new BigDecimal(amount));

				try (ResultSet resultSet = preparedStatement.executeQuery()) {
					if (null != resultSet && resultSet.next()) {
						fields.put(FieldType.ORIG_TXN_ID.getName(),
								resultSet.getString(FieldType.TXN_ID.getName()));
						fields.put(FieldType.DUPLICATE_YN.getName(), "Y");
					} else {
						fields.put(FieldType.DUPLICATE_YN.getName(), "N");
					}
				}
			}
		} catch (SQLException sqlException) {
//			MDC.put(FieldType.INTERNAL_CUSTOM_MDC.getName(),fields.getCustomMDC());
			String message = "Error checking duplicate transaction!";
//			logger.error(message, sqlException);
			throw new SystemException(ErrorType.DATABASE_ERROR, sqlException,
					message);
		}
	}

	public Fields getFields(String txnId, String appId) throws SystemException {

		try (Connection connection = getConnection()) {
			try (PreparedStatement selectStatement = connection
					.prepareStatement(allFieldsQuery)) {
				selectStatement.setString(1, txnId);
				selectStatement.setString(2, appId);
				try (ResultSet resultSet = selectStatement.executeQuery()) {
					return getFields(resultSet);
				}
			}
		} catch (SQLException sqlException) {
			String message = "Error in reading previous transaction!";
//			logger.error(message);
			throw new SystemException(ErrorType.DATABASE_ERROR, sqlException,
					message);
		}
	}

	public void insertTransaction(Fields fields) throws SystemException {

		try (Connection connection = getConnection()) {
			try (PreparedStatement statement = connection
					.prepareStatement(allFieldsInsertQuery)) {
				statement.setString(1,
						fields.get(FieldType.CARD_MASK.getName()));
				statement.setLong(2,
						Long.parseLong(fields.get(FieldType.TXN_ID.getName())));
				statement.setString(3, fields.get(FieldType.TXNTYPE.getName()));
				statement.setString(4,
						fields.get(FieldType.CUST_NAME.getName()));

				String amountString = fields.get(FieldType.AMOUNT.getName());
				String surchargeAmountString = fields.get(FieldType.SURCHARGE_AMOUNT.getName());
				String dccamountString = fields.get(FieldType.INTERNAL_CURRENCY_CHANGE_RATE.getName());
				String currencyString = fields.get(FieldType.CURRENCY_CODE
						.getName());

				String amount = "0";
				if (!StringUtils.isEmpty(amountString)
						&& !StringUtils.isEmpty(currencyString)) {
					amount = Amount.toDecimal(amountString, currencyString);
				}
				
				String surchargeAmount = "0";
				if (!StringUtils.isEmpty(surchargeAmountString)
						&& !StringUtils.isEmpty(currencyString)) {
					surchargeAmount = Amount.toDecimal(surchargeAmountString, currencyString);
				}
				
				String dccamount = "0";
				if (!StringUtils.isEmpty(dccamountString) && !StringUtils.isEmpty(currencyString)) {
					dccamount = Amount.toDecimal(dccamountString, currencyString);
				}
				statement.setBigDecimal(5, new BigDecimal(amount));

				statement
						.setString(6, fields.get(FieldType.ORDER_ID.getName()));
				statement.setString(7, fields.get(FieldType.APP_ID.getName()));
				statement
						.setString(8, fields.get(FieldType.MOP_TYPE.getName()));
				statement.setString(9,
						fields.get(FieldType.CURRENCY_CODE.getName()));
				statement.setString(10, fields.get(FieldType.STATUS.getName()));
				statement.setString(11,
						fields.get(FieldType.RESPONSE_CODE.getName()));
				statement.setString(12,
						fields.get(FieldType.RESPONSE_MESSAGE.getName()));
				statement.setString(13, fields.get(FieldType.ACQ_ID.getName()));
				statement.setString(14,
						fields.get(FieldType.INTERNAL_CUST_IP.getName()));
				statement.setString(15, fields
						.get(FieldType.INTERNAL_CUST_COUNTRY_NAME.getName()));
				statement.setString(16,
						fields.get(FieldType.CUST_EMAIL.getName()));

				long origTxnId = 0;
				String origTxnStr = fields.get(FieldType.ORIG_TXN_ID.getName());
				if (StringUtils.isEmpty(origTxnStr)) {
					origTxnStr = fields.get(FieldType.INTERNAL_ORIG_TXN_ID
							.getName());
				}
				if (!StringUtils.isEmpty(origTxnStr)) {
					origTxnId = Long.parseLong(origTxnStr);
				}
				statement.setLong(17, origTxnId);

				long acctId = 0;
				String acctIdStr = fields.get(FieldType.ACCT_ID.getName());
				if (acctIdStr != null && acctIdStr.length() > 0) {
					acctId = Long.parseLong(acctIdStr);
				}
				statement.setLong(18, acctId);

				statement.setString(19,
						fields.get(FieldType.PAYMENT_TYPE.getName()));
				statement.setString(20, fields.get(FieldType.RRN.getName()));
				statement.setString(21,
						fields.get(FieldType.ACQUIRER_TYPE.getName()));
				statement.setString(22,
						fields.get(FieldType.PRODUCT_DESC.getName()));
				statement.setString(23,
						fields.get(FieldType.AUTH_CODE.getName()));
				statement.setString(24,
						fields.get(FieldType.PG_DATE_TIME.getName()));
				statement.setBytes(25, getInternalRequestFields(fields));
				statement.setString(26,
						fields.get(FieldType.PG_RESP_CODE.getName()));
				statement.setString(27,
						fields.get(FieldType.PG_TXN_MESSAGE.getName()));
				String oid = fields.get(FieldType.OID.getName());
				long longOid = 0;
				if (!StringUtils.isEmpty(oid)) {
					longOid = Long.parseLong(oid);
				}
				statement.setLong(28, longOid);
				statement.setString(29,
						fields.get(FieldType.PG_REF_NUM.getName()));
				statement.setString(30, fields
						.get(FieldType.INTERNAL_TXN_AUTHENTICATION.getName()));
				statement.setString(31, fields
						.get(FieldType.INTERNAL_CARD_ISSUER_BANK.getName()));
				statement.setString(32, fields
						.get(FieldType.INTERNAL_CARD_ISSUER_COUNTRY.getName()));
				statement.setString(33,
						fields.get(FieldType.INTERNAL_USER_EMAIL.getName()));
				statement.setString(34,
						fields.get(FieldType.IS_RECURRING.getName()));
				statement.setString(35,
						fields.get(FieldType.RECURRING_TRANSACTION_COUNT.getName()));
				statement.setString(36,
						fields.get(FieldType.RECURRING_TRANSACTION_INTERVAL.getName()));
				statement.setString(37,
						fields.get(FieldType.SURCHARGE_FLAG.getName()));
				statement.setString(38, surchargeAmount);
				statement.setString(39,
						fields.get(FieldType.INTERNAL_TXN_CHANNEL.getName()));
				statement.setString(40,
						fields.get(FieldType.CREATE_DATE.getName()));
				statement.setString(41,
						fields.get(FieldType.UPDATE_DATE.getName()));
				statement.setString(42, fields.get(FieldType.PG_GATEWAY.getName()));

				statement.setString(43, fields.get(FieldType.UPI.getName()));
				statement.setBigDecimal(44, new BigDecimal(dccamount));
				
				
				int count = statement.executeUpdate();
				if (count < 1) {
					throw new SystemException(ErrorType.DATABASE_ERROR,
							"No record inserted in table 'transaction'");
				}
			}
		} catch (SQLException sqlException) {
//			MDC.put(FieldType.INTERNAL_CUSTOM_MDC.getName(),fields.getCustomMDC());
			String message = "Error while inserting transaction in database";
//			logger.error(message, sqlException);
			throw new SystemException(ErrorType.DATABASE_ERROR, sqlException,
					message);
		}
	}

	private byte[] getInternalRequestFields(Fields fields) {
		String internalReqFields = fields.get(FieldType.INTERNAL_REQUEST_FIELDS
				.getName());

		if (null != internalReqFields) {
			byte[] allFields = Base64.encodeBase64(internalReqFields.getBytes());
			return allFields;
		} else {
			return null;
		}
	}

	public void insertCustomerInfo(Fields fields) throws SystemException {

		// Return for invalid transaction
		String responseCode = fields.get(FieldType.RESPONSE_CODE.getName());
		if (responseCode != null
				&& responseCode.equals(ErrorType.VALIDATION_FAILED.getCode())) {
			return;
		}

		// Return for invalid transaction (Hash invalid)
		String invalidHash = fields.get(FieldType.INTERNAL_VALIDATE_HASH_YN
				.getName());
		if (null != invalidHash && invalidHash.equals("Y")) {
			return;
		}

		boolean custInfoPresent = false;// if not a new order then return
		// TODO.......
		// if fields do not contain any shipping/billing information then return
		if (!fields.get(FieldType.TXNTYPE.getName()).equals(
				TransactionType.NEWORDER.getName())) {
			return;
		}

		Set<String> fieldsKeySet = fields.keySet();
		for (String fieldName : fieldsKeySet) {
			if (fieldName.startsWith("CUST_SHIP")
					|| fieldName.startsWith("CUST_S")
					|| fieldName.equals("CUST_ID")) {
				custInfoPresent = true;
			}
		}
		if (custInfoPresent) {
			// execute query
			insertCustomerInfoQuery(fields);
		}

	}

	@SuppressWarnings("incomplete-switch")
	public void updateNewOrder(Fields fields) throws SystemException {
		String internalOrigTxnId = fields.get(FieldType.INTERNAL_ORIG_TXN_ID
				.getName());
		String status = fields.get(FieldType.STATUS.getName());
		if (StringUtils.isEmpty(internalOrigTxnId)
				|| StringUtils.isEmpty(status)) {
			return;
		}

		TransactionType transactionType = TransactionType.getInstance(fields
				.get(FieldType.TXNTYPE.getName()));
		switch (transactionType) {
		case SALE:
			updateNewOrderDetails(fields);
			break;
		case AUTHORISE:
			updateNewOrderDetails(fields);
			break;
		case ENROLL:
			updateNewOrderDetails(fields);
			break;
		}
	}

	public void insert(Fields fields) throws SystemException {
		insertTransaction(fields);

		updateNewOrder(fields);

		insertCustomerInfo(fields);
	}

	private Fields getFields(ResultSet resultSet) throws SystemException {
		Fields fields = new Fields();

		try {
			if (null != resultSet && resultSet.next()) {

				for (String columnName : allDBRequestFields) {
					fields.put(columnName, resultSet.getString(columnName));
				}
			}

			fields.logAllFields("Previous fields");

		} catch (SQLException sqlException) {

			String message = "Database Error while reading previous transaction";
//			logger.error(message, sqlException);
			throw new SystemException(ErrorType.DATABASE_ERROR, sqlException,
					message);
		}

		return fields;
	}

	public void insertCustomerInfoQuery(Fields fields) throws SystemException {
		String query = "INSERT INTO BILLING_DETAILS "
				+ "( TXN_ID,CUST_ID,CUST_NAME,CUST_PHONE,CUST_EMAIL,CUST_STREET_ADDRESS1,CUST_STREET_ADDRESS2,"
				+ "CUST_CITY,CUST_STATE,CUST_COUNTRY,CUST_ZIP,CUST_FIRST_NAME, CUST_LAST_NAME, "
				+ "CUST_SHIP_NAME,CUST_SHIP_PHONE,CUST_SHIP_EMAIL,CUST_SHIP_STREET_ADDRESS1, CUST_SHIP_STREET_ADDRESS2,"
				+ "CUST_SHIP_CITY,CUST_SHIP_STATE,CUST_SHIP_COUNTRY,CUST_SHIP_ZIP,CUST_SHIP_FIRST_NAME,CUST_SHIP_LAST_NAME,ORDER_ID ) "
				+ "VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";

		try (Connection connection = getConnection()) {
			try (PreparedStatement statement = connection
					.prepareStatement(query)) {

				statement.setString(1, fields.get(FieldType.TXN_ID.getName()));
				statement.setString(2, fields.get(FieldType.CUST_ID.getName()));
				statement.setString(3,
						fields.get(FieldType.CUST_NAME.getName()));
				statement.setString(4,
						fields.get(FieldType.CUST_PHONE.getName()));
				statement.setString(5,
						fields.get(FieldType.CUST_EMAIL.getName()));
				statement.setString(6,
						fields.get(FieldType.CUST_STREET_ADDRESS1.getName()));
				statement.setString(7,
						fields.get(FieldType.CUST_STREET_ADDRESS2.getName()));
				statement.setString(8,
						fields.get(FieldType.CUST_CITY.getName()));
				statement.setString(9,
						fields.get(FieldType.CUST_STATE.getName()));
				statement.setString(10,
						fields.get(FieldType.CUST_COUNTRY.getName()));
				statement.setString(11,
						fields.get(FieldType.CUST_ZIP.getName()));
				statement.setString(12,
						fields.get(FieldType.CUST_FIRST_NAME.getName()));
				statement.setString(13,
						fields.get(FieldType.CUST_LAST_NAME.getName()));

				statement.setString(14,
						fields.get(FieldType.CUST_SHIP_NAME.getName()));
				statement.setString(15,
						fields.get(FieldType.CUST_SHIP_PHONE.getName()));
				statement.setString(16,
						fields.get(FieldType.CUST_SHIP_EMAIL.getName()));
				statement.setString(17, fields
						.get(FieldType.CUST_SHIP_STREET_ADDRESS1.getName()));
				statement.setString(18, fields
						.get(FieldType.CUST_SHIP_STREET_ADDRESS2.getName()));
				statement.setString(19,
						fields.get(FieldType.CUST_SHIP_CITY.getName()));
				statement.setString(20,
						fields.get(FieldType.CUST_SHIP_STATE.getName()));
				statement.setString(21,
						fields.get(FieldType.CUST_SHIP_COUNTRY.getName()));
				statement.setString(22,
						fields.get(FieldType.CUST_SHIP_ZIP.getName()));
				statement.setString(23,
						fields.get(FieldType.CUST_SHIP_FIRST_NAME.getName()));
				statement.setString(24,
						fields.get(FieldType.CUST_SHIP_LAST_NAME.getName()));
				statement.setString(24,
						fields.get(FieldType.CUST_SHIP_LAST_NAME.getName()));
				statement.setString(25,
						fields.get(FieldType.ORDER_ID.getName()));

				statement.execute();
			}
		} catch (SQLException sqlException) {
//			MDC.put(FieldType.INTERNAL_CUSTOM_MDC.getName(),fields.getCustomMDC());
			String message = "Error while inserting customer information in database";
//			logger.error(message, sqlException);
			throw new SystemException(ErrorType.DATABASE_ERROR, sqlException,
					message);
		}
	}

	public void insertNewOrder(Fields fields) throws SystemException {
		try (Connection connection = getConnection()) {
			try (PreparedStatement statement = connection
					.prepareStatement(allFieldsInsertQuery)) {

				statement.setString(1,
						fields.get(FieldType.CARD_MASK.getName()));
				statement.setLong(2,
						Long.parseLong(fields.get(FieldType.TXN_ID.getName())));
				statement.setString(3, TransactionType.NEWORDER.getName());
				statement.setString(4,
						fields.get(FieldType.CUST_NAME.getName()));

				String amountString = fields.get(FieldType.AMOUNT.getName());
				String currencyString = fields.get(FieldType.CURRENCY_CODE
						.getName());
				String surchargeAmountString = fields.get(FieldType.SURCHARGE_AMOUNT.getName());
				String dccamountString = fields.get(FieldType.INTERNAL_CURRENCY_CHANGE_RATE.getName());
				String amount = "0";
				if (!StringUtils.isEmpty(amountString)
						&& !StringUtils.isEmpty(currencyString)) {
					amount = Amount.toDecimal(amountString, currencyString);
				}
				String surchargeAmount = "0";
				if (!StringUtils.isEmpty(surchargeAmountString)
						&& !StringUtils.isEmpty(currencyString)) {
					surchargeAmount = Amount.toDecimal(surchargeAmountString, currencyString);
				}
				String dccamount = "0";
				if (!StringUtils.isEmpty(dccamountString) && !StringUtils.isEmpty(currencyString)) {
					dccamount = Amount.toDecimal(dccamountString, currencyString);
				}
				statement.setBigDecimal(5, new BigDecimal(amount));
				statement
						.setString(6, fields.get(FieldType.ORDER_ID.getName()));
				statement.setString(7, fields.get(FieldType.APP_ID.getName()));
				statement.setString(8, null);
				statement.setString(9,
						fields.get(FieldType.CURRENCY_CODE.getName()));

				statement.setString(10, fields.get(FieldType.STATUS.getName()));
				statement.setString(11, ErrorType.SUCCESS.getCode());
				statement.setString(12, ErrorType.SUCCESS.getResponseMessage());

				statement.setString(13, fields.get(FieldType.ACQ_ID.getName()));
				statement.setString(14,
						fields.get(FieldType.INTERNAL_CUST_IP.getName()));
				statement.setString(15, fields
						.get(FieldType.INTERNAL_CUST_COUNTRY_NAME.getName()));
				statement.setString(16,
						fields.get(FieldType.CUST_EMAIL.getName()));
				long origTxnId = 0;
				String origTxnStr = fields.get(FieldType.ORIG_TXN_ID.getName());
				if (StringUtils.isEmpty(origTxnStr)) {
					origTxnStr = fields.get(FieldType.INTERNAL_ORIG_TXN_ID
							.getName());
				}
				if (!StringUtils.isEmpty(origTxnStr)) {
					origTxnId = Long.parseLong(origTxnStr);
				}
				statement.setLong(17, origTxnId);
				long acctId = 0;
				String acctIdStr = fields.get(FieldType.ACCT_ID.getName());
				if (acctIdStr != null && acctIdStr.length() > 0) {
					acctId = Long.parseLong(acctIdStr);
				}
				statement.setLong(18, acctId);
				statement.setString(19, null);
				statement.setString(20, fields.get(FieldType.RRN.getName()));
				statement.setString(21,
						fields.get(FieldType.ACQUIRER_TYPE.getName()));
				statement.setString(22,
						fields.get(FieldType.PRODUCT_DESC.getName()));
				statement.setString(23,
						fields.get(FieldType.AUTH_CODE.getName()));
				statement.setString(24,
						fields.get(FieldType.PG_DATE_TIME.getName()));
				statement.setBytes(25, getInternalRequestFields(fields));
				statement.setString(26,
						fields.get(FieldType.PG_RESP_CODE.getName()));
				statement.setString(27,
						fields.get(FieldType.PG_TXN_MESSAGE.getName()));
				String oid = fields.get(FieldType.OID.getName());
				long longOid = 0;
				if (!StringUtils.isEmpty(oid)) {
					longOid = Long.parseLong(oid);
				}
				statement.setLong(28, longOid);
				statement.setString(29,
						fields.get(FieldType.PG_REF_NUM.getName()));
				statement.setString(30, fields
						.get(FieldType.INTERNAL_TXN_AUTHENTICATION.getName()));
				statement.setString(31, fields
						.get(FieldType.INTERNAL_CARD_ISSUER_BANK.getName()));
				statement.setString(32, fields
						.get(FieldType.INTERNAL_CARD_ISSUER_COUNTRY.getName()));
				statement.setString(33,
						fields.get(FieldType.INTERNAL_USER_EMAIL.getName()));
				statement.setString(34,
						fields.get(FieldType.IS_RECURRING.getName()));
				statement.setString(35,
						fields.get(FieldType.RECURRING_TRANSACTION_COUNT.getName()));
				statement.setString(36,
						fields.get(FieldType.RECURRING_TRANSACTION_INTERVAL.getName()));
				statement.setString(37,
						fields.get(FieldType.SURCHARGE_FLAG.getName()));
				statement.setString(38, surchargeAmount);
				statement.setString(39,
						fields.get(FieldType.INTERNAL_TXN_CHANNEL.getName()));
				statement.setString(40,
						fields.get(FieldType.CREATE_DATE.getName()));
				statement.setString(41,
						fields.get(FieldType.UPDATE_DATE.getName()));
				statement.setString(42, fields.get(FieldType.PG_GATEWAY.getName()));

				statement.setString(43, fields.get(FieldType.UPI.getName()));
				statement.setBigDecimal(44, new BigDecimal(dccamount));
				int count = statement.executeUpdate();
				if (count < 1) {
					throw new SystemException(ErrorType.DATABASE_ERROR,
							"No record inserted in table 'transaction'");
				}
			}
		} catch (SQLException sqlException) {
//			MDC.put(FieldType.INTERNAL_CUSTOM_MDC.getName(),fields.getCustomMDC());
			String message = "Error while inserting transaction in database";
//			logger.error(message, sqlException);
			throw new SystemException(ErrorType.DATABASE_ERROR, sqlException,
					message);
		}
	}

}