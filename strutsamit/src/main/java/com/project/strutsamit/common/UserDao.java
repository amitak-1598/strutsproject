package com.project.strutsamit.common;

// comment out transaction and payout reports 

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.persistence.NoResultException;

//import org.apache.log4j.Logger;
import org.hibernate.HibernateException;
import org.hibernate.ObjectNotFoundException;

import com.project.strutsamit.common.dao.DataAccessObject;
import com.project.strutsamit.common.dao.HibernateAbstractDao;
import com.project.strutsamit.common.exception.DataAccessLayerException;
import com.project.strutsamit.common.exception.ErrorType;
import com.project.strutsamit.common.exception.SystemException;
import com.project.strutsamit.util.CrmFieldType;
import com.project.strutsamit.util.UserStatusType;



public class UserDao extends HibernateAbstractDao {

//	private static Logger logger = Logger.getLogger(UserDao.class.getName());

	public UserDao() {
		super();
	}

	private static final String getCompleteUserWithappIdQuery = "from User U where U.appId = :appId";
	private static final String getUserTableWithappId = "select new map (emailId as emailId, password as password, appId as appId, accHolderName as accHolderName, "
			+ "accountNo as accountNo, firstName as firstName, lastName as lastName, accountValidationKey as accountValidationKey, activationDate as activationDate,"
			+ " address as address, amountOfTransactions as amountOfTransactions, bankName as bankName, branchName as branchName, businessModel as businessModel, businessName as businessName, settlementDay as settlementDay, cin as cin, comments as comments, companyName as companyName, "
			+ "contactPerson as contactPerson, merchantType as merchantType, resellerId as resellerId, productDetail as productDetail, registrationDate as registrationDate,mobile as mobile, transactionSmsFlag as transactionSmsFlag, telephoneNo as telephoneNo, fax as fax, address as address,"
			+ " city as city, state as state, country as country, postalCode as postalCode, modeType as modeType, whiteListIpAddress as whiteListIpAddress, ifscCode as ifscCode, currency as currency, panCard as panCard, "
			+ "uploadePhoto as uploadePhoto, uploadedPanCard as uploadedPanCard, uploadedPhotoIdProof as uploadedPhotoIdProof, uploadedContractDocument as uploadedContractDocument, emailValidationFlag as emailValidationFlag, organisationType as organisationType, website as website,"
			+ " multiCurrency as multiCurrency, businessModel as businessModel, operationAddress as operationAddress, operationState as operationState, operationCity as operationCity, operationPostalCode as operationPostalCode, dateOfEstablishment as dateOfEstablishment, pan as pan, panName as panName,"
			+ " noOfTransactions as noOfTransactions, attemptTrasacation as attemptTrasacation, transactionEmailId as transactionEmailId, transactionEmailerFlag as transactionEmailerFlag, expressPayFlag as expressPayFlag, merchantHostedFlag as merchantHostedFlag, "
			+ "iframePaymentFlag as iframePaymentFlag, transactionAuthenticationEmailFlag as transactionAuthenticationEmailFlag, transactionCustomerEmailFlag as transactionCustomerEmailFlag, refundTransactionCustomerEmailFlag as refundTransactionCustomerEmailFlag, refundTransactionMerchantEmailFlag as refundTransactionMerchantEmailFlag,"
			+ "retryTransactionCustomeFlag as retryTransactionCustomeFlag, surchargeFlag as surchargeFlag, parentappId as parentappId, userStatus as userStatus, userType as userType, industryCategory as industryCategory, industrySubCategory as industrySubCategory,"
			+ "extraRefundLimit as extraRefundLimit, defaultCurrency as defaultCurrency, amexSellerId as amexSellerId, mCC as mCC,webhookurl as webhookurl) "
			+ "from User U where U.appId = :appId1";;

	private final static String queryAdminList = "select appId, businessName, emailId, userStatus,Mobile,registrationDate,userType from User where (userType='ADMIN') order by appId ";
	private final static String querymerchantList = "Select emailId from User U where ((U.userType = '"
			+ UserType.MERCHANT + "') or (U.userType = '" + UserType.RESELLER + "') ) and U.userStatus='"
			+ UserStatusType.ACTIVE + "' order by emailId";
	private static final String getNotificationEmailerUserDetail = "from NotificationEmailer N where N.appId = :appId";

	private static final String getStatus = "select STATUS,AMOUNT,ORIG_TXN_ID,RESPONSE_CODE From TRANSACTION where APP_ID = ? and TXNTYPE IN('SALE','AUTHORISE','ENROLL') and ORDER_ID = ?";
	private static final String getStatusAPI = "select RESPONSE_CODE,PG_REF_NUM,TXN_ID,MOP_TYPE,CARD_MASK,ACQ_ID,TXNTYPE,CURRENCY_CODE,PAYMENT_TYPE,STATUS,APP_ID,ORDER_ID,AMOUNT,ORIG_TXN_ID,RESPONSE_CODE,CUST_EMAIL,CUST_EMAIL,CREATE_DATE From TRANSACTION where APP_ID = ? and TXNTYPE IN('SALE','AUTHORISE','ENROLL') and ORDER_ID = ?";
	private static final String getPayoutAmount = "select appId,lastRechargedAmount,lastConsumedAmount,availableBalance,lastRechargedDate,lastConsumedDate From payout_balance_sheet where appId = ?";
	private static final String getPayoutTransactionsList = "select beneId,transferId,createdDate,referenceId,transferMode,status,transferAmount,serviceCharge,gstAmount,netAmount From payout_transaction where appId = ?";
	private static final String getCompleteWhitelabelUserWithappIdQuery = "from WhitelableBranding W where W.appId = :appId";
	private static final String getCompleteWhitelabelUserWithBrandURLQuery = "from WhitelableBranding W where W.brandURL = :brandURL";
	private static final String getResellerappId = "select appId from User U where U.resellerId = ? and U.userType = ?";

	private Connection getConnection() throws SQLException {
		return DataAccessObject.getBasicConnection();
	}

	public void create(User user) throws DataAccessLayerException {
		super.save(user);
	}

	public void createEmailerFalg(NotificationEmailer userFE) throws DataAccessLayerException {
		super.save(userFE);
	}

	// whitelabel partner
//	public void create(WhitelableBranding whitelabelUser) throws DataAccessLayerException {
//		super.save(whitelabelUser);
//	}
//
//	public void update(WhitelableBranding branding) throws DataAccessLayerException {
//		super.saveOrUpdate(branding);
//	}

	public void delete(User User) throws DataAccessLayerException {
		super.delete(User);
	}

	public User find(Long id) throws DataAccessLayerException {
		return (User) super.find(User.class, id);
	}

	public User find(String name) throws DataAccessLayerException {
		return (User) super.find(User.class, name);
	}

	@SuppressWarnings("rawtypes")
	public List findAll() throws DataAccessLayerException {
		return super.findAll(User.class);
	}

	public void update(User user) throws DataAccessLayerException {
		super.saveOrUpdate(user);
	}

	public void updateNotificationEamiler(NotificationEmailer user) throws DataAccessLayerException {
		super.saveOrUpdate(user);
	}

	public void updateEmailValidation(String accountValidationKey, UserStatusType userStatus,
			boolean emailValidationFlag) {
		try {
			startOperation();
			getSession()
					.createQuery(
							"update User U set U.userStatus = :userStatus, U.emailValidationFlag = :emailValidationFlag"
									+ " where U.accountValidationKey = :accountValidationKey")
					.setParameter("userStatus", userStatus).setParameter("emailValidationFlag", emailValidationFlag)
					.setParameter("accountValidationKey", accountValidationKey).executeUpdate();
			getTx().commit();

		} catch (ObjectNotFoundException objectNotFound) {
			handleException(objectNotFound);
		} catch (HibernateException hibernateException) {
			handleException(hibernateException);
		} finally {
			autoClose();
		}
	}

	public void updateAccountValidationKey(String accountValidationKey, String appId) {
		try {
			startOperation();
			getSession()
					.createQuery("update User U set U.accountValidationKey = :accountValidationKey"
							+ ",U.emailValidationFlag=0 where U.appId = :appId")
					.setParameter("accountValidationKey", accountValidationKey).setParameter("appId", appId)
					.executeUpdate();
			getTx().commit();
		} catch (ObjectNotFoundException objectNotFound) {
			handleException(objectNotFound);
		} catch (HibernateException hibernateException) {
			handleException(hibernateException);
		} finally {
			autoClose();
		}
	}

	public User findAppId(String appId1) {
		return (User) findByappId(appId1);

	}

	public NotificationEmailer findByEmailerByappId(String appId) {

		NotificationEmailer responseUser = null;
		try {
			startOperation();
			responseUser = (NotificationEmailer) getSession().createQuery(getNotificationEmailerUserDetail)
					.setParameter("appId", appId).setCacheable(true).getSingleResult();
			getTx().commit();
			return responseUser;
		} catch (NoResultException noResultException) {
			return null;
		} catch (ObjectNotFoundException objectNotFound) {
			handleException(objectNotFound);
		} catch (HibernateException hibernateException) {
//			logger.error(hibernateException);
			handleException(hibernateException);
		} finally {
			autoClose();
		}
		return responseUser;
	}

	protected User findByappId(String appId1) {

		User responseUser = null;
		try {
			startOperation();
			responseUser = (User) getSession().createQuery(getCompleteUserWithappIdQuery).setParameter("appId", appId1)
					.setCacheable(true).getSingleResult();
			getTx().commit();
			return responseUser;
		} catch (NoResultException noResultException) {
			return null;
		} catch (ObjectNotFoundException objectNotFound) {
			handleException(objectNotFound);
		} catch (HibernateException hibernateException) {
//			logger.error(hibernateException);
			handleException(hibernateException);
		} finally {
			autoClose();
		}
		return responseUser;
	}

	// For Merchant API
	public User findBySafexpayUser(String appId1) {

		User responseUser = null;
		try {
			startOperation();
			responseUser = (User) getSession().createQuery(getCompleteUserWithappIdQuery).setParameter("appId", appId1)
					.setCacheable(true).getSingleResult();
			getTx().commit();
			return responseUser;
		} catch (NoResultException noResultException) {
			return null;
		} catch (ObjectNotFoundException objectNotFound) {
			handleException(objectNotFound);
		} catch (HibernateException hibernateException) {
//			logger.error(hibernateException);
			handleException(hibernateException);
		} finally {
			autoClose();
		}
		return responseUser;
	}

	protected Object getUserObj(String appId) {
		Object userObject = null;
		try {
			startOperation();
			userObject = getSession().createQuery(getUserTableWithappId).setParameter("appId1", appId)
					.setCacheable(true).getSingleResult();

			getTx().commit();
		} catch (NoResultException noResultException) {
			return null;
		} catch (ObjectNotFoundException objectNotFound) {
			handleException(objectNotFound);
		} catch (HibernateException hibernateException) {
			handleException(hibernateException);
		} finally {
			autoClose();
		}
		return userObject;
	}

	@SuppressWarnings("unchecked")
	public Map<String, Object> getUserObjMap(String appId) {

		Map<String, Object> userDetailsMap = null;
		Object userObject = getUserObj(appId);

		if (null != userObject) {
			userDetailsMap = (Map<String, Object>) userObject;
		}
		return userDetailsMap;
	}

	public User getUserClass(String appId) {
		User responseUser = new User();
		Map<String, Object> userDetailsMap = getUserObjMap(appId);
		if (null == userDetailsMap) {
			return null;
		} else {
			responseUser.setEmailId((String) userDetailsMap.get(CrmFieldType.EMAILID.getName()));
			responseUser.setModeType((ModeType) userDetailsMap.get(CrmFieldConstants.MODE_TYPE.getValue()));
			responseUser.setAccHolderName((String) userDetailsMap.get(CrmFieldType.ACC_HOLDER_NAME.getName()));
			responseUser.setAccountNo((String) userDetailsMap.get(CrmFieldType.ACCOUNT_NO.getName()));
			responseUser.setAccountValidationKey(
					(String) userDetailsMap.get(CrmFieldType.ACCOUNT_VALIDATION_KEY.getName()));
			responseUser.setActivationDate((Date) userDetailsMap.get(CrmFieldType.ACTIVATION_DATE.getName()));
			responseUser.setAddress((String) userDetailsMap.get(CrmFieldType.ADDRESS.getName()));
			responseUser.setAmountOfTransactions(
					(String) userDetailsMap.get(CrmFieldType.AMOUNT_OF_TRANSACTIONS.getName()));
			responseUser.setAttemptTrasacation((String) userDetailsMap.get(CrmFieldType.ATTEMPT_TRASACATION.getName()));
			responseUser.setBankName((String) userDetailsMap.get(CrmFieldType.BANK_NAME.getName()));
			responseUser.setBranchName((String) userDetailsMap.get(CrmFieldType.BRANCH_NAME.getName()));
			responseUser.setBusinessModel((String) userDetailsMap.get(CrmFieldType.BUSINESSMODEL.getName()));
			responseUser.setBusinessName((String) userDetailsMap.get(CrmFieldType.BUSINESS_NAME.getName()));
			// responseUser.setBusinessType((String)
			// userDetailsMap.get(CrmFieldType.BUSINESS_TYPE.getName()));
			responseUser.setCin((String) userDetailsMap.get(CrmFieldType.CIN.getName()));
			responseUser.setCity((String) userDetailsMap.get(CrmFieldType.CITY.getName()));
			responseUser.setComments((String) userDetailsMap.get(CrmFieldType.COMMENTS.getName()));
			responseUser.setCompanyName((String) userDetailsMap.get(CrmFieldType.COMPANY_NAME.getName()));
			responseUser.setContactPerson((String) userDetailsMap.get(CrmFieldType.CONTACT_PERSON.getName()));
			responseUser.setCountry((String) userDetailsMap.get(CrmFieldType.COUNTRY.getName()));
			responseUser.setCurrency((String) userDetailsMap.get(CrmFieldType.CURRENCY.getName()));
			responseUser
					.setDateOfEstablishment((String) userDetailsMap.get(CrmFieldType.DATE_OF_ESTABLISHMENT.getName()));
			responseUser.setEmailValidationFlag(
					(boolean) userDetailsMap.get(CrmFieldConstants.EMAIL_VALIDATION_FLAG.getValue()));
			responseUser.setExpressPayFlag((boolean) userDetailsMap.get(CrmFieldConstants.EXPRESS_PAY_FLAG.getValue()));
			responseUser.setFax((String) userDetailsMap.get(CrmFieldType.FAX.getName()));
			responseUser.setFirstName((String) userDetailsMap.get(CrmFieldType.FIRSTNAME.getName()));
			responseUser.setIframePaymentFlag(
					(boolean) userDetailsMap.get(CrmFieldConstants.IFRAME_PAYMENT_FLAG.getValue()));
			responseUser.setIfscCode((String) userDetailsMap.get(CrmFieldType.IFSC_CODE.getName()));
			responseUser.setLastName((String) userDetailsMap.get(CrmFieldType.LASTNAME.getName()));
			responseUser.setMerchantHostedFlag(
					(boolean) userDetailsMap.get(CrmFieldConstants.MERCHANT_HOSTED_FALAG.getValue()));
			responseUser.setMerchantType((String) userDetailsMap.get(CrmFieldType.MERCHANT_TYPE.getName()));
			responseUser.setMobile((String) userDetailsMap.get(CrmFieldType.MOBILE.getName()));
			responseUser.setMultiCurrency((String) userDetailsMap.get(CrmFieldType.MULTICURRENCY.getName()));
			responseUser.setNoOfTransactions((String) userDetailsMap.get(CrmFieldType.NO_OF_TRANSACTIONS.getName()));
			responseUser.setOperationAddress((String) userDetailsMap.get(CrmFieldType.OPERATIONADDRESS.getName()));
			responseUser.setOperationCity((String) userDetailsMap.get(CrmFieldType.OPERATION_CITY.getName()));
			responseUser
					.setOperationPostalCode((String) userDetailsMap.get(CrmFieldType.OPERATION_POSTAL_CODE.getName()));
			responseUser.setOperationState((String) userDetailsMap.get(CrmFieldType.PPERATION_STATE.getName()));
			responseUser.setOrganisationType((String) userDetailsMap.get(CrmFieldType.ORGANIZATIONTYPE.getName()));
			responseUser.setPan((String) userDetailsMap.get(CrmFieldType.PAN.getName()));
			responseUser.setPanCard((String) userDetailsMap.get(CrmFieldType.PANCARD.getName()));
			responseUser.setPanName((String) userDetailsMap.get(CrmFieldType.PANNAME.getName()));
			responseUser.setParentappId((String) userDetailsMap.get(CrmFieldType.PARENT_APP_ID.getName()));
			responseUser.setPassword((String) userDetailsMap.get(CrmFieldType.PASSWORD.getName()));
			responseUser.setAppId((String) userDetailsMap.get(CrmFieldType.APP_ID.getName()));
			responseUser.setPostalCode((String) userDetailsMap.get(CrmFieldType.POSTALCODE.getName()));
			responseUser.setProductDetail((String) userDetailsMap.get(CrmFieldType.PRODUCT_DETAIL.getName()));
			responseUser.setRegistrationDate((Date) userDetailsMap.get(CrmFieldType.REGISTRATION_DATE.getName()));
			responseUser.setResellerId((String) userDetailsMap.get(CrmFieldType.RESELLER_ID.getName()));
			responseUser.setState((String) userDetailsMap.get(CrmFieldType.STATE.getName()));
			responseUser.setRetryTransactionCustomeFlag(
					(boolean) userDetailsMap.get(CrmFieldConstants.RETRY_TRANSACTION_FLAG.getValue()));
			responseUser.setTelephoneNo((String) userDetailsMap.get(CrmFieldType.TELEPHONE_NO.getName()));
			responseUser.setTransactionAuthenticationEmailFlag(
					(boolean) userDetailsMap.get(CrmFieldConstants.TRANSACTION_AUTHENTICATION_EMAIL_FLAG.getValue()));
			responseUser.setTransactionCustomerEmailFlag(
					(boolean) userDetailsMap.get(CrmFieldConstants.TRANSACTION_CUSTOMER_EMAIL_FLAG.getValue()));
			responseUser.setRefundTransactionCustomerEmailFlag(
					(boolean) userDetailsMap.get(CrmFieldConstants.REFUND_TXN_CUSTOMER_EMAIL_FLAG.getValue()));
			responseUser.setRefundTransactionMerchantEmailFlag(
					(boolean) userDetailsMap.get(CrmFieldConstants.REFUND_TXN_MERCHANT_EMAIL_FLAG.getValue()));
			responseUser.setTransactionEmailerFlag(
					(boolean) userDetailsMap.get(CrmFieldConstants.TRANSACTION_EMAILER_FLAG.getValue()));
			responseUser
					.setTransactionEmailId((String) userDetailsMap.get(CrmFieldType.TRANSACTION_EMAIL_ID.getName()));
			responseUser.setTransactionSmsFlag(
					(boolean) userDetailsMap.get(CrmFieldConstants.TRANSACTION_SMS_FLAG.getValue()));
			responseUser.setUploadedContractDocument(
					(String) userDetailsMap.get(CrmFieldType.UPLOADE_CONTRACT_DOCUMENT.getName()));
			responseUser.setUploadedPanCard((String) userDetailsMap.get(CrmFieldType.UPLOADE_PAN_CARD.getName()));
			responseUser
					.setUploadedPhotoIdProof((String) userDetailsMap.get(CrmFieldType.UPLOADE_PHOTOID_PROOF.getName()));
			responseUser.setUploadePhoto((String) userDetailsMap.get(CrmFieldType.UPLOADE_PHOTO.getName()));
			responseUser.setUserStatus((UserStatusType) userDetailsMap.get(CrmFieldType.USERSTATUS.getName()));
			responseUser.setUserType((UserType) userDetailsMap.get(CrmFieldConstants.USER_TYPE.getValue()));
			responseUser.setWebsite((String) userDetailsMap.get(CrmFieldType.WEBSITE.getName()));
			responseUser.setWhiteListIpAddress((String) userDetailsMap.get(CrmFieldType.WHITE_LIST_IPADDRES.getName()));
			responseUser.setExtraRefundLimit((float) userDetailsMap.get(CrmFieldType.EXTRA_REFUND_LIMIT.getName()));
			responseUser.setDefaultCurrency((String) userDetailsMap.get(CrmFieldType.DEFAULT_CURRENCY.getName()));
			responseUser.setAmexSellerId((String) userDetailsMap.get(CrmFieldType.AMEX_SELLER_ID.getName()));
			responseUser.setMCC((String) userDetailsMap.get(CrmFieldType.MCC.getName()));
			responseUser.setSettlementDay((String) userDetailsMap.get(CrmFieldType.MCC.getName()));
			responseUser.setWebhookurl((String) userDetailsMap.get(CrmFieldType.WEBHOOK_URL.getName()));

			// responseUser.setTerminalId((String)
			// userDetailsMap.get(CrmFieldType.TERMINAL_ID.getName()));
			// responseUser.setEncryptionKey((String)
			// userDetailsMap.get(CrmFieldType.ENCRYPTION_KEY.getName()));
			// responseUser.setSecureKey((String)
			// userDetailsMap.get(CrmFieldType.SECURE_KEY.getName()));
			// responseUser.setMccCode((String)
			// userDetailsMap.get(CrmFieldType.MCC_CODE.getName()));
			// UPI
			// responseUser.setMerchantVpa((String)
			// userDetailsMap.get(CrmFieldType.MERCHANT_VPA.getName()));
			responseUser.setSurchargeFlag((boolean) userDetailsMap.get(CrmFieldConstants.SURCHARGE_FLAG.getValue()));
		}
		return responseUser;
	}

	// get status API - 1
//	public TransactionStatusReport getfindStatusAPI(String appId, String orderId) throws SystemException {
//		TransactionStatusReport transaction = new TransactionStatusReport();
//
//		try (Connection connection = getConnection()) {
//			try (PreparedStatement prepStmt = connection.prepareStatement(getStatusAPI)) {
//
//				prepStmt.setString(1, appId);
//				prepStmt.setString(2, orderId);
//				try (ResultSet rs = prepStmt.executeQuery()) {
//					while (rs.next()) {
//
//						transaction.setResponseCode(rs.getString(FieldType.RESPONSE_CODE.getName()));
//						transaction.setTransactionId(rs.getString(FieldType.TXN_ID.getName()));
//						transaction.setMoptype(rs.getString(FieldType.MOP_TYPE.getName()));
//						transaction.setCardmask(rs.getString(FieldType.CARD_MASK.getName()));
//
//						transaction.setAcqId(rs.getString(FieldType.ACQ_ID.getName()));
//						transaction.setTxnType(rs.getString(FieldType.TXNTYPE.getName()));
//						transaction.setCurrency(rs.getString(FieldType.CURRENCY_CODE.getName()));
//						transaction.setPaymentType(rs.getString(FieldType.PAYMENT_TYPE.getName()));
//						transaction.setPgRefNum(rs.getString(FieldType.PG_REF_NUM.getName()));
//
//						transaction.setStatus(rs.getString(FieldType.STATUS.getName()));
//						transaction.setAppId(rs.getString(FieldType.APP_ID.getName()));
//						transaction.setOrderId(rs.getString(FieldType.ORDER_ID.getName()));
//						transaction.setApprovedAmount(rs.getString(FieldType.AMOUNT.getName()));
//						transaction.setOrginTxnId(rs.getString(FieldType.ORIG_TXN_ID.getName()));
//						transaction.setResponseCode(rs.getString(FieldType.RESPONSE_CODE.getName()));
//						transaction.setCustEmail(rs.getString(FieldType.CUST_EMAIL.getName()));
//						transaction.setDateTime(rs.getString(FieldType.CREATE_DATE.getName()));
//
//					}
//				}
//			}
//
//		} catch (SQLException exception) {
//			throw new SystemException(ErrorType.DATABASE_ERROR, ErrorType.DATABASE_ERROR.getResponseMessage());
//		} finally {
//			autoClose();
//		}
//		return transaction;
//	}

	// get status API -2
//	public TransactionStatusReport getfindStatus(String appId, String orderId) throws SystemException {
//		TransactionStatusReport transaction = new TransactionStatusReport();
//
//		try (Connection connection = getConnection()) {
//			try (PreparedStatement prepStmt = connection.prepareStatement(getStatus)) {
//
//				prepStmt.setString(1, appId);
//				prepStmt.setString(2, orderId);
//				try (ResultSet rs = prepStmt.executeQuery()) {
//					while (rs.next()) {
//						transaction.setStatus(rs.getString(FieldType.STATUS.getName()));
//						transaction.setApprovedAmount(rs.getString(FieldType.AMOUNT.getName()));
//						transaction.setTransactionId(rs.getString(FieldType.ORIG_TXN_ID.getName()));
//						transaction.setResponseCode(rs.getString(FieldType.RESPONSE_CODE.getName()));
//
//					}
//				}
//			}
//
//		} catch (SQLException exception) {
//			throw new SystemException(ErrorType.DATABASE_ERROR, ErrorType.DATABASE_ERROR.getResponseMessage());
//		} finally {
//			autoClose();
//		}
//		return transaction;
//	}

	@SuppressWarnings("unchecked")
	public User findByAccountValidationKey(String accountValidationKey) {

		User responseUser = null;
		try {
			startOperation();
			List<User> users = getSession()
					.createQuery("from User U where U.accountValidationKey = :accountValidationKey")
					.setParameter("accountValidationKey", accountValidationKey).getResultList();
			for (User user : users) {
				responseUser = user;
				break;
			}
			getTx().commit();

			return responseUser;
		} catch (ObjectNotFoundException objectNotFound) {
			handleException(objectNotFound);
		} catch (HibernateException hibernateException) {
			handleException(hibernateException);
		} finally {
			autoClose();
		}

		return responseUser;
	}

	@SuppressWarnings("rawtypes")
	public List getMerchantActiveList() throws DataAccessLayerException {
		return getMerchantActive();
	}

	@SuppressWarnings("unchecked")
	protected List<Merchants> getMerchantActive() {
		List<Merchants> merchantsList = new ArrayList<Merchants>();
		try {
			startOperation();
			List<Object[]> merchantListRaw = getSession()
					.createQuery(
							"Select emailId, appId, businessName from User U where U.userType = '" + UserType.MERCHANT
									+ "' and U.userStatus='" + UserStatusType.ACTIVE + "' order by businessName")
					.getResultList();

			for (Object[] objects : merchantListRaw) {
				Merchants merchant = new Merchants();
				merchant.setEmailId((String) objects[0]);
				merchant.setAppId((String) objects[1]);
				merchant.setBusinessName((String) objects[2]);
				merchantsList.add(merchant);
			}
			getTx().commit();

			return merchantsList;
		} catch (ObjectNotFoundException objectNotFound) {
			handleException(objectNotFound);
		} catch (HibernateException hibernateException) {
			handleException(hibernateException);
		} finally {
			autoClose();
		}
		return merchantsList;
	}

	@SuppressWarnings("rawtypes")
	public List getMerchantList() throws DataAccessLayerException {
		return getMerchants();
	}

	@SuppressWarnings("unchecked")
	protected List<Merchants> getMerchants() {
		List<Merchants> merchantsList = new ArrayList<Merchants>();
		try {
			startOperation();
			List<Object[]> merchantListRaw = getSession()
					.createQuery("Select emailId, appId, businessName from User U where U.userType = '"
							+ UserType.MERCHANT + "' order by businessName")
					.getResultList();

			for (Object[] objects : merchantListRaw) {
				Merchants merchant = new Merchants();
				merchant.setEmailId((String) objects[0]);
				merchant.setAppId((String) objects[1]);
				merchant.setBusinessName((String) objects[2]);
				merchantsList.add(merchant);
			}
			getTx().commit();

			return merchantsList;
		} catch (ObjectNotFoundException objectNotFound) {
			handleException(objectNotFound);
		} catch (HibernateException hibernateException) {
			handleException(hibernateException);
		} finally {
			autoClose();
		}
		return merchantsList;
	}

	@SuppressWarnings("rawtypes")
	public List getActiveMerchantList() throws DataAccessLayerException {
		return getActiveMerchants();
	}

	@SuppressWarnings("unchecked")
	protected List<Merchants> getActiveMerchants() {
		List<Merchants> merchantsList = new ArrayList<Merchants>();
		try {
			startOperation();
			List<Object[]> merchantListRaw = getSession()
					.createQuery("Select emailId, appId, businessName from User U where ((U.userType = '"
							+ UserType.MERCHANT + "') or (U.userType = '" + UserType.RESELLER
							+ "') ) and U.userStatus='" + UserStatusType.ACTIVE + "' order by businessName")
					.getResultList();

			for (Object[] objects : merchantListRaw) {
				Merchants merchant = new Merchants();
				merchant.setEmailId((String) objects[0]);
				merchant.setAppId((String) objects[1]);
				merchant.setBusinessName((String) objects[2]);
				merchantsList.add(merchant);
			}
			getTx().commit();

			return merchantsList;
		} catch (ObjectNotFoundException objectNotFound) {
			handleException(objectNotFound);
		} catch (HibernateException hibernateException) {
			handleException(hibernateException);
		} finally {
			autoClose();
		}
		return merchantsList;
	}

	@SuppressWarnings("unchecked")
	public List<Merchants> getSubUserList(String parentappId) {
		List<Merchants> merchantsList = new ArrayList<Merchants>();
		try {
			startOperation();
			List<Object[]> merchantListRaw = getSession()
					.createQuery("Select emailId, appId, businessName from User U where U.userType = '"
							+ UserType.SUBUSER + "' and U.parentappId = '" + parentappId + "'")
					.getResultList();

			for (Object[] objects : merchantListRaw) {
				Merchants merchant = new Merchants();
				merchant.setEmailId((String) objects[0]);
				merchant.setAppId((String) objects[1]);
				merchant.setBusinessName((String) objects[2]);
				merchantsList.add(merchant);
			}
			getTx().commit();
			return merchantsList;
		} catch (ObjectNotFoundException objectNotFound) {
			handleException(objectNotFound);
		} catch (HibernateException hibernateException) {
			handleException(hibernateException);
		} finally {
			autoClose();
		}
		return merchantsList;
	}

	@SuppressWarnings("unchecked")
	public List<Merchants> getSubUsers(String parentappId) {
		List<Merchants> merchantsList = new ArrayList<Merchants>();
		try {
			startOperation();
			List<Object[]> merchantListRaw = getSession().createQuery(
					"Select appId, emailId, firstName, lastName, mobile, userStatus from User U where U.userType = '"
							+ UserType.SUBUSER + "' and parentappId='" + parentappId + "'")
					.getResultList();

			for (Object[] objects : merchantListRaw) {
				Merchants merchant = new Merchants();
				merchant.setAppId((String) objects[0]);
				merchant.setEmailId((String) objects[1]);
				merchant.setFirstName((String) objects[2]);
				merchant.setLastName((String) objects[3]);
				merchant.setMobile((String) objects[4]);
				if (((UserStatusType) objects[5]).equals(UserStatusType.ACTIVE)) {
					merchant.setIsActive(true);
				} else if (((UserStatusType) objects[5]).equals(UserStatusType.PENDING)) {
					merchant.setIsActive(false);
				}
				merchantsList.add(merchant);
			}
			getTx().commit();
			return merchantsList;
		} catch (ObjectNotFoundException objectNotFound) {
			handleException(objectNotFound);
		} catch (HibernateException hibernateException) {
			handleException(hibernateException);
		} finally {
			autoClose();
		}
		return merchantsList;
	}

	// get Agents
	@SuppressWarnings("unchecked")
	public List<SubAdmin> getAgents(String parentappId) {
		List<SubAdmin> agentsList = new ArrayList<SubAdmin>();
		try {
			startOperation();
			List<Object[]> agentListRaw = getSession().createQuery(
					"Select appId, emailId, firstName, lastName, mobile, userStatus from User U where U.userType = '"
							+ UserType.SUBADMIN + "' and parentappId='" + parentappId + "'")
					.getResultList();

			for (Object[] objects : agentListRaw) {
				SubAdmin subAdmin = new SubAdmin();
				subAdmin.setAppId((String) objects[0]);
				subAdmin.setAgentEmailId((String) objects[1]);
				subAdmin.setAgentFirstName((String) objects[2]);
				subAdmin.setAgentLastName((String) objects[3]);
				subAdmin.setAgentMobile((String) objects[4]);
				if (((UserStatusType) objects[5]).equals(UserStatusType.ACTIVE)) {
					subAdmin.setAgentIsActive(true);
				} else if (((UserStatusType) objects[5]).equals(UserStatusType.PENDING)) {
					subAdmin.setAgentIsActive(false);
				}
				agentsList.add(subAdmin);
			}
			getTx().commit();
			return agentsList;
		} catch (ObjectNotFoundException objectNotFound) {
			handleException(objectNotFound);
		} catch (HibernateException hibernateException) {
			handleException(hibernateException);
		} finally {
			autoClose();
		}
		return agentsList;
	}

	public User findAcquirerByCode(String acquirerCode) {
		User user = getAcquirer(acquirerCode);
		return user;
	}

	protected User getAcquirer(String acqCode) {
		User responseUser = null;
		try {
			startOperation();
			responseUser = (User) getSession()
					.createQuery("from User U where U.userType='ACQUIRER' and U.firstName = :acqCode")
					.setParameter("acqCode", acqCode).setCacheable(true).getSingleResult();
			getTx().commit();
		} catch (ObjectNotFoundException objectNotFound) {
			handleException(objectNotFound);
		} catch (HibernateException hibernateException) {
			handleException(hibernateException);
		} finally {
			autoClose();
		}
		return responseUser;
	}

	public User findappIdByEmail(String emailId) {
		User user = getappId(emailId);
		return user;
	}

	protected User getappId(String emailId) {
		User responseUser = null;
		try {
			startOperation();
			responseUser = (User) getSession().createQuery("from User U where U.emailId = :emailId")
					.setParameter("emailId", emailId).getSingleResult();
			getTx().commit();
		} catch (ObjectNotFoundException objectNotFound) {
			handleException(objectNotFound);
		} catch (HibernateException hibernateException) {
			handleException(hibernateException);
		} finally {
			autoClose();
		}
		return responseUser;
	}

	@SuppressWarnings("rawtypes")
	public List getResellerList() throws DataAccessLayerException {
		return getResellers();
	}

	@SuppressWarnings("unchecked")
	private List<Merchants> getResellers() {
		List<Merchants> resellerList = new ArrayList<Merchants>();
		try {
			startOperation();
			List<Object[]> merchantListRaw = getSession()
					.createQuery("Select emailId, appId, businessName from User U where U.userType = '"
							+ UserType.RESELLER + "' order by businessName")
					.getResultList();

			for (Object[] objects : merchantListRaw) {
				Merchants merchant = new Merchants();
				merchant.setEmailId((String) objects[0]);
				merchant.setAppId((String) objects[1]);
				merchant.setBusinessName((String) objects[2]);
				resellerList.add(merchant);
			}
			getTx().commit();

			return resellerList;
		} catch (ObjectNotFoundException objectNotFound) {
			handleException(objectNotFound);
		} catch (HibernateException hibernateException) {
			handleException(hibernateException);
		} finally {
			autoClose();
		}
		return resellerList;
	}

	public List<Merchants> getActiveResellerMerchantList(String reselleId) throws DataAccessLayerException {
		return getActiveResellerMerchants(reselleId);
	}

	@SuppressWarnings("unchecked")
	public List<Merchants> getActiveResellerMerchants(String resellerId) {
		List<Merchants> merchantsList = new ArrayList<Merchants>();
		try {
			startOperation();
			List<Object[]> merchantListRaw = getSession()
					.createQuery("Select emailId, appId, businessName from User U where U.userType = '"
							+ UserType.MERCHANT + "'and U.userStatus='" + UserStatusType.ACTIVE + "' and resellerId = '"
							+ resellerId + "' order by businessName")
					.getResultList();
			for (Object[] objects : merchantListRaw) {
				Merchants merchant = new Merchants();
				merchant.setEmailId((String) objects[0]);
				merchant.setAppId((String) objects[1]);
				merchant.setBusinessName((String) objects[2]);
				merchantsList.add(merchant);
			}
			getTx().commit();
			return merchantsList;
		} catch (ObjectNotFoundException objectNotFound) {
			handleException(objectNotFound);
		} catch (HibernateException hibernateException) {
			handleException(hibernateException);
		} finally {
			autoClose();
		}
		return merchantsList;
	}

	@SuppressWarnings("rawtypes")
	public List getResellerMerchantList(String resellerId) throws DataAccessLayerException {

		return getResellerMerchant(resellerId);
	}

	@SuppressWarnings("unchecked")
	public List<Merchants> getResellerMerchant(String resellerId) {
		List<Merchants> merchantsList = new ArrayList<Merchants>();
		try {
			startOperation();
			List<Object[]> merchantListRaw = getSession().createQuery(
					"Select emailId, appId, businessName from User U where U.userType = '" + UserType.MERCHANT
							+ "'and U.userStatus='" + UserStatusType.ACTIVE + "' and resellerId = '" + resellerId + "'")
					.getResultList();

			for (Object[] objects : merchantListRaw) {
				Merchants merchant = new Merchants();
				merchant.setEmailId((String) objects[0]);
				merchant.setAppId((String) objects[1]);
				merchant.setBusinessName((String) objects[2]);
				merchantsList.add(merchant);
			}
			getTx().commit();

			return merchantsList;
		} catch (ObjectNotFoundException objectNotFound) {
			handleException(objectNotFound);
		} catch (HibernateException hibernateException) {
			handleException(hibernateException);
		} finally {
			autoClose();
		}
		return merchantsList;
	}

	@SuppressWarnings("unchecked")
	public List<Merchants> getAcquirerSubUsers(String parentappId) {
		List<Merchants> merchantsList = new ArrayList<Merchants>();
		try {
			startOperation();
			List<Object[]> merchantListRaw = getSession().createQuery(
					"Select appId, emailId, firstName, lastName, mobile, userStatus from User U where U.userType = '"
							+ UserType.SUBACQUIRER + "' and parentappId='" + parentappId + "'")
					.getResultList();

			for (Object[] objects : merchantListRaw) {
				Merchants merchant = new Merchants();
				merchant.setAppId((String) objects[0]);
				merchant.setEmailId((String) objects[1]);
				merchant.setFirstName((String) objects[2]);
				merchant.setLastName((String) objects[3]);
				merchant.setMobile((String) objects[4]);
				if (((UserStatusType) objects[5]).equals(UserStatusType.ACTIVE)) {
					merchant.setIsActive(true);
				} else if (((UserStatusType) objects[5]).equals(UserStatusType.PENDING)) {
					merchant.setIsActive(false);
				}
				merchantsList.add(merchant);
			}
			getTx().commit();
			return merchantsList;
		} catch (ObjectNotFoundException objectNotFound) {
			handleException(objectNotFound);
		} catch (HibernateException hibernateException) {
			handleException(hibernateException);
		} finally {
			autoClose();
		}
		return merchantsList;
	}

	public List<User> getUserActiveList() throws DataAccessLayerException {
		return getUserActive();
	}

	@SuppressWarnings("unchecked")
	private List<User> getUserActive() {
		List<User> userList = new ArrayList<User>();
		try {
			startOperation();
			userList = getSession().createQuery(" from User U where U.userStatus='" + UserStatusType.ACTIVE + "'")
					.getResultList();
			getTx().commit();

			return userList;
		} catch (ObjectNotFoundException objectNotFound) {
			handleException(objectNotFound);
		} catch (HibernateException hibernateException) {
			handleException(hibernateException);
		} finally {
			autoClose();
		}
		return userList;
	}

	@SuppressWarnings({ "unchecked" })
	public List<String> getMerchantEmailIdListByBusinessType(String businessType) {
		List<String> merchantEmailList = new ArrayList<String>();
		try {
			startOperation();
			merchantEmailList = getSession()
					.createQuery(" Select U.emailId from User U where U.userStatus='" + UserStatusType.ACTIVE + "'"
							+ " and U.userType = 'MERCHANT' and U.industryCategory = :businessType")
					.setParameter("businessType", businessType).getResultList();
			getTx().commit();

			return merchantEmailList;
		} catch (ObjectNotFoundException objectNotFound) {
			handleException(objectNotFound);
		} catch (HibernateException hibernateException) {
			handleException(hibernateException);
		} finally {
			autoClose();
		}
		return merchantEmailList;
	}

	public String getMerchantNameByappId(String appId) {
		String name = null;
		String firstName = null;
		String lastName = null;
		try {
			startOperation();
			firstName = (String) getSession().createQuery("Select firstName from User U where U.appId = :appId")
					.setParameter("appId", appId).getSingleResult();
			lastName = (String) getSession().createQuery("Select lastName from User U where U.appId = :appId")
					.setParameter("appId", appId).getSingleResult();
			name = firstName + " " + lastName;
			getTx().commit();
		} catch (ObjectNotFoundException objectNotFound) {
			handleException(objectNotFound);
		} catch (HibernateException hibernateException) {
			handleException(hibernateException);
		} finally {
			autoClose();
		}
		return name;
	}

	public List<MerchantDetails> getAllAdminList() throws SystemException {
		List<MerchantDetails> merchants = new ArrayList<MerchantDetails>();
		try (Connection connection = getConnection()) {
			try (PreparedStatement prepStmt = connection.prepareStatement(queryAdminList)) {
				try (ResultSet rs = prepStmt.executeQuery()) {
					while (rs.next()) {

						MerchantDetails merchant = new MerchantDetails();
						merchant.setAppId(rs.getString("appId"));
						merchant.setEmailId(rs.getString("emailId"));
						merchant.setBusinessName(rs.getString("businessName"));
						merchant.setMobile(rs.getString("Mobile"));
						merchant.setRegistrationDate(rs.getString("registrationDate"));
						merchant.setUserType(rs.getString("userType"));
						String status = rs.getString("userStatus");

						if (status != null) {
							UserStatusType userStatus = UserStatusType.valueOf(status);
							merchant.setStatus(userStatus);
						}
						merchants.add(merchant);
					}
				}
			}
		} catch (SQLException exception) {
//			logger.error("Database error", exception);
			throw new SystemException(ErrorType.DATABASE_ERROR, ErrorType.DATABASE_ERROR.getResponseMessage());
		}
		return merchants;
	}

	public List<Merchants> featchAllmerchant() throws SystemException {
		List<Merchants> merchants = new ArrayList<Merchants>();
		try (Connection connection = getConnection()) {
			try (PreparedStatement prepStmt = connection.prepareStatement(querymerchantList)) {
				try (ResultSet rs = prepStmt.executeQuery()) {
					while (rs.next()) {
						Merchants merchant = new Merchants();
						merchant.setEmailId(rs.getString("emailId"));
						merchants.add(merchant);
					}
				}
			}
		} catch (SQLException exception) {
//			logger.error("Database error", exception);
			throw new SystemException(ErrorType.DATABASE_ERROR, ErrorType.DATABASE_ERROR.getResponseMessage());
		}
		return merchants;

	}

	public String getBusinessNameByEmailId(String emailId) {
		String businessName = null;
		try {
			startOperation();
			businessName = (String) getSession()
					.createQuery("Select businessName from User U where U.emailId = :emailId")
					.setParameter("emailId", emailId).getSingleResult();
			getTx().commit();
		} catch (ObjectNotFoundException objectNotFound) {
			handleException(objectNotFound);
		} catch (HibernateException hibernateException) {
			handleException(hibernateException);
		} finally {
			autoClose();
		}
		return businessName;
	}

	public String getBusinessNameByappId(String appId) {
		String businessName = null;
		try {
			startOperation();
			businessName = (String) getSession().createQuery("Select businessName from User U where U.appId = :appId")
					.setParameter("appId", appId).getSingleResult();
			getTx().commit();
		} catch (ObjectNotFoundException objectNotFound) {
			handleException(objectNotFound);
		} catch (HibernateException hibernateException) {
			handleException(hibernateException);
		} finally {
			autoClose();
		}
		return businessName;
	}

	public String getEmailIdByBusinessName(String businessName) {
		String emailId = null;
		try {
			startOperation();
			emailId = (String) getSession()
					.createQuery("Select emailId from User U where U.businessName = :businessName")
					.setParameter("businessName", businessName).getSingleResult();
			getTx().commit();
		} catch (ObjectNotFoundException objectNotFound) {
			handleException(objectNotFound);
		} catch (HibernateException hibernateException) {
			handleException(hibernateException);
		} finally {
			autoClose();
		}
		return emailId;
	}

	public String getAppIdByEmailId(String emailId) {
		String appId = null;
		try {
			startOperation();
			appId = (String) getSession().createQuery("Select appId from User U where U.emailId = :emailId")
					.setParameter("emailId", emailId).getSingleResult();
			getTx().commit();
		} catch (ObjectNotFoundException objectNotFound) {
			handleException(objectNotFound);
		} catch (HibernateException hibernateException) {
			handleException(hibernateException);
		} finally {
			autoClose();
		}
		return appId;
	}

	public String getEmailIdByAppId(String appId) {
		String emailId = null;
		try {
			startOperation();
			emailId = (String) getSession().createQuery("Select emailId from User U where U.appId = :appId")
					.setParameter("appId", appId).getSingleResult();
			getTx().commit();
		} catch (ObjectNotFoundException objectNotFound) {
			handleException(objectNotFound);
		} catch (HibernateException hibernateException) {
			handleException(hibernateException);
		} finally {
			autoClose();
		}
		return emailId;
	}

//	public WhitelableBranding findBrandingAppId(String appId1) {
//		return (WhitelableBranding) findByWhitelabelAppId(appId1);
//
//	}
//
//	protected WhitelableBranding findByWhitelabelAppId(String appId1) {
//
//		WhitelableBranding responseBrandingUser = null;
//		try {
//			startOperation();
//			responseBrandingUser = (WhitelableBranding) getSession()
//					.createQuery(getCompleteWhitelabelUserWithappIdQuery).setParameter("appId", appId1)
//					.setCacheable(true).getSingleResult();
//			getTx().commit();
//			return responseBrandingUser;
//		} catch (NoResultException noResultException) {
//			return null;
//		} catch (ObjectNotFoundException objectNotFound) {
//			handleException(objectNotFound);
//		} catch (HibernateException hibernateException) {
//			logger.error(hibernateException);
//			handleException(hibernateException);
//		} finally {
//			autoClose();
//		}
//		return responseBrandingUser;
//	}
//
//	public WhitelableBranding findByWhitelabelBrandURL(String brandURLTmp) {
//
//		WhitelableBranding responseBrandingUser = null;
//		try {
//			startOperation();
//			responseBrandingUser = (WhitelableBranding) getSession()
//					.createQuery(getCompleteWhitelabelUserWithBrandURLQuery).setParameter("brandURL", brandURLTmp)
//					.setCacheable(true).getSingleResult();
//			getTx().commit();
//			return responseBrandingUser;
//		} catch (NoResultException noResultException) {
//			return null;
//		} catch (ObjectNotFoundException objectNotFound) {
//			handleException(objectNotFound);
//		} catch (HibernateException hibernateException) {
//			logger.error(hibernateException);
//			handleException(hibernateException);
//		} finally {
//			autoClose();
//		}
//		return responseBrandingUser;
//	}

	// get reseller appId for Branding
	public String getResellerAppId(String resellerId) throws SystemException {
		String resellerappId = "";
		String userType = "RESELLER";
		try (Connection connection = getConnection()) {
			try (PreparedStatement prepStmt = connection.prepareStatement(getResellerappId)) {

				prepStmt.setString(1, resellerId);
				prepStmt.setString(2, userType);
				try (ResultSet rs = prepStmt.executeQuery()) {
					while (rs.next()) {
						resellerappId = rs.getString("appId");

					}
				}
			}

		} catch (SQLException exception) {
			throw new SystemException(ErrorType.DATABASE_ERROR, ErrorType.DATABASE_ERROR.getResponseMessage());
		} finally {
			autoClose();
		}
		return resellerappId;
	}
      // payout -3 
//	public PayoutAmount getPayoutDetailsAPI(String appId) throws SystemException {
//		PayoutAmount transaction = new PayoutAmount();
//
//		try (Connection connection = getConnection()) {
//			try (PreparedStatement prepStmt = connection.prepareStatement(getPayoutAmount)) {
//
//				prepStmt.setString(1, appId);
//				try (ResultSet rs = prepStmt.executeQuery()) {
//					while (rs.next()) {
//
//						transaction.setLastRechargedAmount(rs.getString("lastRechargedAmount"));
//						transaction.setLastConsumedAmount(rs.getString("lastConsumedAmount"));
//						transaction.setAvailableBalance(rs.getString("availableBalance"));
//						transaction.setLastConsumedDate(rs.getString("lastConsumedDate"));
//						transaction.setLastRechargedDate(rs.getString("lastRechargedDate"));
//
//					}
//				}
//			}
//
//		} catch (SQLException exception) {
//			throw new SystemException(ErrorType.DATABASE_ERROR, ErrorType.DATABASE_ERROR.getResponseMessage());
//		} finally {
//			autoClose();
//		}
//		return transaction;
//	}
       // PayoutSummaryReport - 4 
//	public PayoutSummaryReport getPayoutTransactionsList(String appId) throws SystemException {
//
//		PayoutSummaryReport payout = new PayoutSummaryReport();
//
//		try (Connection connection = getConnection()) {
//			try (PreparedStatement prepStmt = connection.prepareStatement(getPayoutTransactionsList)) {
//
//				prepStmt.setString(1, appId);
//				try (ResultSet rs = prepStmt.executeQuery()) {
//					while (rs.next()) {
//						payout.setTransactionId(rs.getString("transferId"));
//						payout.setTxnDate(rs.getString("createdDate"));
//						payout.setTransferMode(rs.getString("transferMode"));
//						payout.setStatus(rs.getString("status"));
//						payout.setGstAmount(rs.getString("gstAmount"));
//						payout.setNetAmount(rs.getString("netAmount"));
//						payout.setServiceCharge(rs.getString("serviceCharge"));
//						payout.setTransferAmount(rs.getString("transferAmount"));
//
//					}
//				}
//			}
//
//		} catch (SQLException exception) {
//			throw new SystemException(ErrorType.DATABASE_ERROR, ErrorType.DATABASE_ERROR.getResponseMessage());
//		} finally {
//			autoClose();
//		}
//		return payout;
//
//	}

}
