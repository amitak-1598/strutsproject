package com.project.strutsamit.crm.action;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import com.project.strutsamit.common.CrmFieldConstants;
import com.project.strutsamit.common.Merchants;
import com.project.strutsamit.common.Statistics;
import com.project.strutsamit.common.User;
import com.project.strutsamit.common.UserDao;
import com.project.strutsamit.common.UserType;
import com.project.strutsamit.crm.common.action.ForwardAction;
import com.project.strutsamit.util.Constants;
import com.project.strutsamit.util.UserStatusType;

//import org.apache.log4j.Logger;

//import com.kbn.commons.user.Merchants;
//import com.kbn.commons.user.Statistics;
//import com.kbn.commons.user.User;
//import com.kbn.commons.user.UserDao;
//import com.kbn.commons.user.UserType;
//import com.kbn.commons.util.Constants;
//import com.kbn.commons.util.CrmFieldConstants;
//import com.kbn.commons.util.UserStatusType;
//import com.kbn.crm.actionBeans.CurrencyMapProvider;
//import com.kbn.crm.actionBeans.IndustryTypeCategoryProvider;
//import com.kbn.crm.commons.action.ForwardAction;


public class IndexAction extends ForwardAction {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4616437586910475430L;

//	private static Logger logger = Logger.getLogger(IndexAction.class.getName());
	private Statistics statistics = new Statistics();
	private List<Merchants> merchantList = new ArrayList<Merchants>();
	private Map<String, String> currencyMap = new LinkedHashMap<String, String>();
	private Map<String,String> industryTypes = new LinkedHashMap<String,String>();
	private User user = new User();
	private String permissionString = "";
	private String reselleId;

	@SuppressWarnings("unchecked")
	public String authoriseUser() {
		try {
			user = (User) sessionMap.get(Constants.USER.getValue());
			if (user.getUserType().equals(UserType.ADMIN)|| user.getUserType().equals(UserType.SUBADMIN) 
					|| user.getUserType().equals(UserType.SUPERADMIN)|| user.getUserType().equals(UserType.RESELLER)) {
				if (user.getUserType().equals(UserType.RESELLER)) {
					merchantList = new UserDao()
							.getActiveResellerMerchantList(user.getResellerId());
				} else {
					merchantList = new UserDao().getMerchantActiveList();
				}
				CurrencyMapProvider currencyMapProvider = new CurrencyMapProvider();
 				currencyMap = currencyMapProvider.currencyMap(user);
				
			} else if (user.getUserType().equals(UserType.MERCHANT)) {
				Merchants merchant = new Merchants();
				merchant.setMerchant(user);
				merchantList.add(merchant);
				IndustryTypeCategoryProvider IndustryTypeCategoryProvider = new IndustryTypeCategoryProvider();
				setIndustryTypes(IndustryTypeCategoryProvider.industryTypes(user));
				CurrencyMapProvider currencyMapProvider = new CurrencyMapProvider();
 				currencyMap = currencyMapProvider.currencyMap(user);
					}
				
			if (user.getUserType().equals(UserType.ADMIN) || user.getUserType().equals(UserType.RESELLER)) {
				CurrencyMapProvider currencyMapProvider = new CurrencyMapProvider();
				currencyMap = currencyMapProvider.currencyMap(user);
				return CrmFieldConstants.ADMIN.getValue();
			}else if(user.getUserType().equals(UserType.SUPERADMIN)){
				CurrencyMapProvider currencyMapProvider = new CurrencyMapProvider();
				currencyMap = currencyMapProvider.currencyMap(user);
				return CrmFieldConstants.SUPERADMIN.getValue();
			} else if (user.getUserType().equals(UserType.MERCHANT)|| user.getUserType().equals(UserType.POSMERCHANT)) {
				if (user.getUserStatus().equals(UserStatusType.SUSPENDED)) {
					return CrmFieldConstants.NEW_USER.getValue();
				} else {
					return CrmFieldConstants.MERCHANT.getValue();
				}
			} else if (user.getUserType().equals(UserType.SUBUSER)) {
				CurrencyMapProvider currencyMapProvider = new CurrencyMapProvider();
				currencyMap = currencyMapProvider.currencyMap(user);
					return CrmFieldConstants.SUBUSER.getValue();
			}else if (user.getUserType().equals(UserType.ACQUIRER)) {
				CurrencyMapProvider currencyMapProvider = new CurrencyMapProvider();
				currencyMap = currencyMapProvider.currencyMap(user);
					return CrmFieldConstants.ACQUIRER.getValue();
			}else if (user.getUserType().equals(UserType.SUBACQUIRER)) {
				CurrencyMapProvider currencyMapProvider = new CurrencyMapProvider();
				currencyMap = currencyMapProvider.currencyMap(user);
					return CrmFieldConstants.ACQUIRER_SUBUSER.getValue();		
			}
	        else if (user.getUserType().equals(UserType.SUBADMIN)) {
				CurrencyMapProvider currencyMapProvider = new CurrencyMapProvider();
				currencyMap = currencyMapProvider.currencyMap(user);
					return CrmFieldConstants.SUBADMIN.getValue();
			}

		} catch (Exception exception) {
//			logger.error("Exception", exception);
			return ERROR;
		}
		return LOGIN; // unmapped user
	}

	public Map<String, String> getCurrencyMap() {
		return currencyMap;
	}

	public void setCurrencyMap(Map<String, String> currencyMap) {
		this.currencyMap = currencyMap;
	}

	public Statistics getStatistics() {
		return statistics;
	}

	public void setStatistics(Statistics statistics) {
		this.statistics = statistics;
	}

	public List<Merchants> getMerchantList() {
		return merchantList;
	}

	public void setMerchantList(List<Merchants> merchantList) {
		this.merchantList = merchantList;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public String getPermissionString() {
		return permissionString;
	}

	public void setPermissionString(String permissionString) {
		this.permissionString = permissionString;
	}

	public String getReselleId() {
		return reselleId;
	}

	public void setReselleId(String reselleId) {
		this.reselleId = reselleId;
	}
	public Map<String, String> getIndustryTypes() {
		return industryTypes;
	}

	public void setIndustryTypes(Map<String, String> industryTypes) {
		this.industryTypes = industryTypes;
	}

}
