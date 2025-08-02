package com.project.strutsamit.crm.action;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.commons.lang3.StringUtils;

import com.project.strutsamit.common.CrmFieldConstants;
import com.project.strutsamit.common.User;
import com.project.strutsamit.common.UserDao;
import com.project.strutsamit.common.UserType;
import com.project.strutsamit.crm.core.Currency;




/**
 * @author Neeraj
 *
 */
public class CurrencyMapProvider{

//	private static Logger logger = Logger.getLogger(IndexAction.class.getName());

	public Map<String, String> currencyMap(User user) {
		
		Map<String, String> tempMap;
		String currencyKey = user.getDefaultCurrency();
		Map<String, String> currencyMap = new LinkedHashMap<String, String>();
		if (user.getUserType().equals(UserType.ADMIN)
				|| user.getUserType().equals(UserType.RESELLER) || user.getUserType().equals(UserType.ACQUIRER)
				|| user.getUserType().equals(UserType.SUPERADMIN)||user.getUserType().equals(UserType.SUBADMIN)) {
			tempMap = Currency.getAllCurrency();
			// set currencies
			String strKey = tempMap.get(CrmFieldConstants.INR.getValue());
			if (StringUtils.isBlank(currencyKey)) {
				if (!StringUtils.isBlank(strKey)) {
					tempMap.remove(CrmFieldConstants.INR.getValue());
					currencyMap.put(CrmFieldConstants.INR.getValue(), strKey);
					for (Entry<String, String> entry : tempMap.entrySet()) {
						try {
							currencyMap.put(entry.getKey(), entry.getValue());
						} catch (ClassCastException classCastException) {
//							logger.error("Exception", classCastException);
						}
					}
					return currencyMap;
				}
			}
		}else if(user.getUserType().equals(UserType.SUBUSER) || user.getUserType().equals(UserType.SUBACQUIRER)){
		//	User sessionUser = (User) sessionMap.get(Constants.USER.getValue());
			String parentappId = user.getParentappId();
			UserDao userDao = new UserDao();
			User parentUser = (User) userDao.findAppId(parentappId);
			tempMap = Currency.getSupportedCurreny(parentUser);
			if (StringUtils.isBlank(currencyKey)) {
				return tempMap;
			}
		} 
		else {
			tempMap = Currency.getSupportedCurreny(user);
			if (StringUtils.isBlank(currencyKey)) {
				return tempMap;
			}
		}
		tempMap.remove(currencyKey);
		currencyMap.put(currencyKey, Currency.getAlphabaticCode(currencyKey));
		for (Entry<String, String> entry : tempMap.entrySet()) {
			try {
				currencyMap.put(entry.getKey(), entry.getValue());
			} catch (ClassCastException classCastException) {
//				logger.error("Exception", classCastException);
			}
		}

		return currencyMap;
	}
}
