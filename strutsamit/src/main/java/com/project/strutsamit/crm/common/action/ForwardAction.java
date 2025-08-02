package com.project.strutsamit.crm.common.action;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

import com.project.strutsamit.common.Account;
import com.project.strutsamit.common.Merchants;
import com.project.strutsamit.common.User;
import com.project.strutsamit.common.UserDao;
import com.project.strutsamit.common.UserType;
import com.project.strutsamit.common.exception.ErrorType;
import com.project.strutsamit.crm.action.CurrencyMapProvider;
import com.project.strutsamit.crm.core.Currency;
import com.project.strutsamit.util.BusinessType;
import com.project.strutsamit.util.Constants;
import com.project.strutsamit.util.CrmFieldType;

//import org.apache.log4j.Logger;



/**
 * @author Neeraj
 * 
 */

public class ForwardAction extends AbstractSecureAction {

//	private static Logger logger = Logger.getLogger(ForwardAction.class.getName());
	private static final long serialVersionUID = -6411665872667971425L;
	private List<Merchants> merchantList = new ArrayList<Merchants>();
	private Map<String, String> currencyMap = new LinkedHashMap<String, String>();
	private Map<String, String> industryTypes = new TreeMap<String, String>();
	private User sessionUser = null;
	private List<User> userActivelist = new ArrayList<User>();

	@SuppressWarnings("unchecked")
	public String execute() {
		CurrencyMapProvider currencyMapProvider = new CurrencyMapProvider();
		try {
			sessionUser = (User) sessionMap.get(Constants.USER.getValue());
			if (sessionUser.getUserType().equals(UserType.ADMIN)
					|| sessionUser.getUserType().equals(UserType.SUPERADMIN)
					|| sessionUser.getUserType().equals(UserType.SUBADMIN)
					|| sessionUser.getUserType().equals(UserType.RESELLER)
					|| sessionUser.getUserType().equals(UserType.ACQUIRER)) {
				if (sessionUser.getUserType().equals(UserType.RESELLER)) {
					merchantList = new UserDao().getActiveResellerMerchants(sessionUser.getResellerId());
				} else if (sessionUser.getUserType().equals(UserType.ACQUIRER)) {
					String merchantappId = sessionUser.getAppId();
					List<User> userlist = new ArrayList<User>();
					userlist = new UserDao().getUserActiveList();
					for (User user : userlist) {
						Merchants merchant = new Merchants();
						merchant.setEmailId(user.getEmailId());
						merchant.setAppId(user.getAppId());
						merchant.setBusinessName(user.getBusinessName());
						userActivelist.add(user);
						Set<Account> accountSet = user.getAccounts();
						Iterator<Account> accountDetails = accountSet.iterator();
						while (accountDetails.hasNext()) {
							Account account = accountDetails.next();
							if (merchantappId.equals(account.getAcquirerappId())) {
								merchantList.add(merchant);
							}

						}
					}
				} else {
					merchantList = new UserDao().getMerchantActiveList();
					setIndustryTypes(BusinessType.getIndustryCategoryList());
				}
				currencyMap = currencyMapProvider.currencyMap(sessionUser);

			} else if (sessionUser.getUserType().equals(UserType.MERCHANT)) {
				currencyMap = currencyMapProvider.currencyMap(sessionUser);
				if (currencyMap.isEmpty()) {
					addFieldError(CrmFieldType.DEFAULT_CURRENCY.getName(),
							ErrorType.UNMAPPED_CURRENCY_ERROR.getResponseMessage());
					addActionMessage("No currency mapped!!");
					return INPUT;
				}

			} else if (sessionUser.getUserType().equals(UserType.SUBUSER)) {
				Merchants merchant = new Merchants();
				String parentMerchantappId = sessionUser.getParentappId();
				UserDao userDao = new UserDao();
				User parentMerchant = userDao.findAppId(parentMerchantappId);
				merchant.setMerchant(parentMerchant);
				merchantList.add(merchant);
				currencyMap = currencyMapProvider.currencyMap(sessionUser);
				if (currencyMap.isEmpty()) {
					addFieldError(CrmFieldType.DEFAULT_CURRENCY.getName(),
							ErrorType.UNMAPPED_CURRENCY_ERROR.getResponseMessage());
					addActionMessage("No currency mapped!!");
					return INPUT;
				}

			}
			return INPUT;
		} catch (Exception exception) {
//			logger.error("Exception", exception);
			return ERROR;
		}
	}

	@SuppressWarnings("unchecked")
	public String allMerchants() {
		try {
			sessionUser = (User) sessionMap.get(Constants.USER.getValue());
			if (sessionUser.getUserType().equals(UserType.ADMIN) || sessionUser.getUserType().equals(UserType.SUBADMIN)
					|| sessionUser.getUserType().equals(UserType.SUPERADMIN)
					|| sessionUser.getUserType().equals(UserType.RESELLER)
					|| sessionUser.getUserType().equals(UserType.ACQUIRER)) {
				if (sessionUser.getUserType().equals(UserType.RESELLER)) {
					merchantList = new UserDao().getActiveResellerMerchants(sessionUser.getResellerId());
				} else {
					merchantList = new UserDao().getMerchantList();
				}
				// set currencies
				currencyMap = Currency.getAllCurrency();
			} else if (sessionUser.getUserType().equals(UserType.MERCHANT)) {
				Merchants merchant = new Merchants();
				merchant.setMerchant(sessionUser);
				merchantList.add(merchant);
				// set currencies
				currencyMap = Currency.getSupportedCurreny(sessionUser);
			} else if (sessionUser.getUserType().equals(UserType.SUBUSER)) {
				Merchants merchant = new Merchants();
				User user = new User();
				UserDao userDao = new UserDao();
				user = userDao.findAppId(sessionUser.getParentappId());
				merchant.setMerchant(user);
				merchantList.add(merchant);
				// set currencies
				currencyMap = Currency.getSupportedCurreny(sessionUser);
			}

			return INPUT;
		} catch (Exception exception) {
//			logger.error("Exception", exception);
			return ERROR;
		}
	}

	public List<Merchants> getMerchantList() {
		return merchantList;
	}

	public void setMerchantList(List<Merchants> merchantList) {
		this.merchantList = merchantList;
	}

	public Map<String, String> getCurrencyMap() {
		return currencyMap;
	}

	public void setCurrencyMap(Map<String, String> currencyMap) {
		this.currencyMap = currencyMap;
	}

	public Map<String, String> getIndustryTypes() {
		return industryTypes;
	}

	public void setIndustryTypes(Map<String, String> industryTypes) {
		this.industryTypes = industryTypes;
	}
}
