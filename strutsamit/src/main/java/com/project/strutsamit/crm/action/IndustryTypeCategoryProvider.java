package com.project.strutsamit.crm.action;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Map.Entry;
import org.apache.log4j.Logger;

import com.project.strutsamit.common.User;
import com.project.strutsamit.common.UserType;
import com.project.strutsamit.util.BusinessType;

public class IndustryTypeCategoryProvider {
	private Logger logger = Logger.getLogger(IndustryTypeCategoryProvider.class.getName());

	public Map<String, String> industryTypes(User user) {
		Map<String, String> tempMap;
		String industryKey = user.getIndustryCategory();

		Map<String, String> industryTypesMap = new LinkedHashMap<String, String>();
		if (user.getUserType().equals(UserType.MERCHANT)) {
			tempMap = BusinessType.getIndustryCategoryList();
			if (tempMap.containsKey(industryKey)) {
				industryTypesMap.put("Default", tempMap.get(industryKey));
			}
			for (Entry<String, String> entry : tempMap.entrySet()) {
				try {
					industryTypesMap.put(entry.getKey(), entry.getValue());
				} catch (ClassCastException classCastException) {
					logger.error("Exception", classCastException);
				}
			}
		}
		return industryTypesMap;
	}
}
