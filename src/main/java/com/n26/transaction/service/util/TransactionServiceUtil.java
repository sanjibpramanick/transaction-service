package com.n26.transaction.service.util;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.n26.transaction.service.annotation.Log;

/**
 * Util for the application
 * 
 * @author Sanjib Pramanick
 *
 */
public class TransactionServiceUtil {

	private static Logger LOG = LoggerFactory.getLogger(TransactionServiceUtil.class.getName());

	/**
	 * converts epoch time to java timestamp in milliseconds
	 * 
	 * @param epochTime
	 * @return
	 */
	@Log
	public static Long convertEpochToTimestamp(Long epochTime) {
		return epochTime * 1000;
	}

	public static boolean isTansactionMadeBeforeTime(Long tansactionTimestamp, Integer maxDiff) {
		Date transactionDate = new Date(tansactionTimestamp);
		LOG.info("Date of transaction: " + transactionDate);
		Date currentDate = new Date(System.currentTimeMillis());
		LOG.info("Current Date:" + currentDate);
		return getDifferenceInSeconds(transactionDate, currentDate) >= maxDiff;
	}

	/**
	 * Returns the time difference in seconds
	 * 
	 * @param startDate
	 * @param endDate
	 * @return the time difference in seconds
	 */
	public static Long getDifferenceInSeconds(Date startDate, Date endDate) {
		LOG.info("Start Date: " + startDate);
		LOG.info("End Date: " + endDate);
		Duration diff = Duration.between(LocalDateTime.ofInstant(startDate.toInstant(), ZoneId.systemDefault()),
				LocalDateTime.ofInstant(endDate.toInstant(), ZoneId.systemDefault()));
		LOG.info("Difference: " + diff.getSeconds());
		return diff.getSeconds();
	}
}
