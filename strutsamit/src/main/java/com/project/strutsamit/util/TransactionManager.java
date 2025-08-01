package com.project.strutsamit.util;

import java.text.SimpleDateFormat;
import java.util.Date;

public class TransactionManager {

	public static final String DATE_FORMAT = "yyMMddHHmmss";
	public static final int MIN_TRANSACTION_ID = 1000;
	public static final int MAX_TRANSACTION_ID = 9999;

	public static final TransactionIdGenerator transactionIdGenerator = new TransactionIdGenerator(MIN_TRANSACTION_ID,MAX_TRANSACTION_ID);

	public TransactionManager() {
	}

	public static String getNewTransactionId() {
		final Date date = new Date();
		final SimpleDateFormat simpleDateFormat = new SimpleDateFormat(DATE_FORMAT);
		final StringBuilder transactionId = new StringBuilder();
		transactionId.append(transactionIdGenerator.next());
		transactionId.append(simpleDateFormat.format(date));
		return transactionId.toString();
	}
}
