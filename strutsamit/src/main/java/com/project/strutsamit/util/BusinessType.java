package com.project.strutsamit.util;

import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class BusinessType {

	private static final String categoryPropertyFile = "industry_category.properties";

	public static List<String> getIndustrySubcategory(String industryCategory){
		List<String> subCategories = new LinkedList<String>();
		String industrySubCategoryString =	new PropertiesManager().getIndustrySubcategories(industryCategory);
		String[] industrySubcategoryArray = industrySubCategoryString.split(Constants.COMMA.getValue());

		for(String subCategoey: industrySubcategoryArray){
			subCategories.add(subCategoey);
		}
		return subCategories;
	}

	public static Map<String,String> getIndustryCategoryList(){
		Map<String,String> categories = new LinkedHashMap<String, String>();
		categories =	new PropertiesManager().getAllProperties(categoryPropertyFile);
		return categories;
	}
}
