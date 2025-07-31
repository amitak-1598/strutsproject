package com.project.strutsamit.common;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Date;

//import org.apache.log4j.Logger;

public class DateCreater {
//	private static Logger logger = Logger.getLogger(DateCreater.class.getName());
	public static DateTimeFormatter dateTimeFormat = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

	public static String defaultFromDate() {
		SimpleDateFormat inputDateFormat = new SimpleDateFormat(CrmFieldConstants.INPUT_DATE_FORMAT.getValue());
		Date currentDate = new Date();
		try {
			return inputDateFormat.format(currentDate);
		} catch (Exception exception) {
	//		logger.error("Exception", exception);
			return null;
		}
	}

	public static String defaultToDate() {
		SimpleDateFormat inputDateFormat = new SimpleDateFormat(CrmFieldConstants.INPUT_DATE_FORMAT.getValue());
		Calendar cal = Calendar.getInstance();
		Date currentDate = new Date();
		try {
			cal.setTime(currentDate);
			cal.add(Calendar.DAY_OF_MONTH, -30);
			return inputDateFormat.format(cal.getTime());
		} catch (Exception exception) {
		//	logger.error("Exception", exception);
			return null;
		}
	}

	public static String formatFromDate(String dateFrom) {
		SimpleDateFormat inputDateFormat = new SimpleDateFormat(CrmFieldConstants.INPUT_DATE_FORMAT.getValue()); // get date format as is from front end
		//SimpleDateFormat outputDateFormat = new SimpleDateFormat(CrmFieldConstants.OUTPUT_DATE_FORMAT.getValue()); // convert date in this format

		try {
			Date fromDate = (Date)(inputDateFormat.parse(dateFrom));
			//dateFrom = outputDateFormat.format(fromDate);
			Calendar cal = Calendar.getInstance();
			cal.setTime(fromDate);
			dateFrom = cal.get(Calendar.YEAR) + "-" + (cal.get(Calendar.MONTH) + 1) + "-" +  cal.get(Calendar.DATE);

			return dateFrom;
		} catch (Exception exception) {
		//	logger.error("Exception", exception);
			return null;
		}
	}
	public static String formatDateforChargeback(String date) {
		
		try {
			String[] parts = date.split("-");
			date= parts[2] + "-" + parts[1] +"-" +  parts[0];
			return date;
		} catch (Exception exception) {
		//	logger.error("Exception", exception);
			return null;
		}
	}

	public static String formatToDate(String dateTo) {
		SimpleDateFormat inputDateFormat = new SimpleDateFormat(CrmFieldConstants.INPUT_DATE_FORMAT.getValue()); // get date format as is from front end
		//SimpleDateFormat outputDateFormat = new SimpleDateFormat(CrmFieldConstants.OUTPUT_DATE_FORMAT.getValue()); // convert date in this format
		Calendar cal = Calendar.getInstance();

		try {
			Date toDate = inputDateFormat.parse(dateTo);
			cal.setTime(toDate);
			cal.add(Calendar.DAY_OF_MONTH, 1);
			//dateTo = outputDateFormat.format(cal.getTime());
			dateTo = cal.get(Calendar.YEAR) + "-" + (cal.get(Calendar.MONTH) + 1) + "-" +  cal.get(Calendar.DATE);
			return dateTo;
		} catch (Exception exception) {
//			logger.error("Exception", exception);
			return null;
		}
	}
	
	public static Date formatStringToDate(String date) {		
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		try {
			Date dt = formatter.parse(date);
			return dt;
		} catch (Exception exception) {
//			logger.error("Exception", exception);
			return null;
		}		
	}
	
	public static long diffDate(String date1, String date2) {		
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		try {
			Date dt1 = formatter.parse(formatFromDate(date1));
		    Date dt2 = formatter.parse(formatFromDate(date2));
		    long diff = dt2.getTime() - dt1.getTime();
		    long diffDays = diff / (24 * 60 * 60 * 1000);
			return diffDays;
		} catch (Exception exception) {
//			logger.error("Exception", exception);
			return 0;
		}		
	}
	
	public static String formatDateForDb(Date date){
		SimpleDateFormat outputDateFormat = new SimpleDateFormat(CrmFieldConstants.OUTPUT_DATE_FORMAT_DB.getValue());		
		return outputDateFormat.format(date);
	}
	
	public static String defaultCurrentDateTime() {
		SimpleDateFormat inputDateFormat = new SimpleDateFormat(CrmFieldConstants.DATE_TIME_FORMAT.getValue());
		Date currentDate = new Date();
		try {
			return inputDateFormat.format(currentDate);
		} catch (Exception exception) {
	//		logger.error("Exception", exception);
			return null;
		}
	}
	
	@Deprecated
	public static Date currentDateTime() {		
		//DateFormat inputDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
		try {
			Calendar cal = Calendar.getInstance();
			Date dt = cal.getTime();
			return dt;
		} catch (Exception exception) {
	//		logger.error("Exception", exception);
			return null;
		}		
	}
	
	public static Date formatStringToDateTime(String date) {		
		DateFormat inputDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
		try {			 
			 Date dt = inputDateFormat.parse(date);
			return dt;
		} catch (Exception exception) {
	//		logger.error("Exception", exception);
			return null;
		}		
	}
	
	//TODO review return type -->because our format can't be parsed in LocalDateTime
	public static String subtractHours(LocalDateTime currentStamp, long hours){
		LocalDateTime finalStamp = currentStamp.minusHours(hours);
		return finalStamp.format(dateTimeFormat);	
	}
	
	public static String subtractDays(LocalDateTime currentStamp, long days){
		LocalDateTime finalStamp = currentStamp.minusDays(days);
		return finalStamp.format(dateTimeFormat);	
	}
	
	public static String subtractWeeks(LocalDateTime currentStamp, long weeks){
		LocalDateTime finalStamp = currentStamp.minusWeeks(weeks);
		return finalStamp.format(dateTimeFormat);	
	}
	
	public static String subtractMonths(LocalDateTime currentStamp, long months){
		LocalDateTime finalStamp = currentStamp.minusMonths(months);
		return finalStamp.format(dateTimeFormat);	
	}
	
	public static LocalDateTime now(){
		return LocalDateTime.now();
	}
}