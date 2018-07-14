package com.n26.transaction.service.provider;

import java.util.Date;

import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.n26.transaction.service.annotation.Log;
import com.n26.transaction.service.bean.TransactionRequestVO;
import com.n26.transaction.service.util.TransactionServiceUtil;

/**
 * Transaction Provider Service
 * 
 * @author pramanick_s
 *
 */
@Service
public class TransactionEventProvider {

	private static Logger log = LoggerFactory.getLogger(TransactionEventProvider.class.getName());

	/**
	 * returns 201 in case of transaction within 60 seconds else 204
	 * 
	 * @param transactionRequest
	 * @return
	 */
	@Log
	public int createEntry(TransactionRequestVO transactionRequest) {
		if (transactionRequest == null) {
			return HttpServletResponse.SC_BAD_REQUEST;
		}
		if (isTansactionMadeBeforeTime(transactionRequest.getTimestamp(), 60))
			return HttpServletResponse.SC_NO_CONTENT;
		return HttpServletResponse.SC_CREATED;
	}

	private boolean isTansactionMadeBeforeTime(Long tansactionTimestamp, Integer maxDiff) {
		Date transactionDate = new Date(tansactionTimestamp);
		log.info("Date of transaction: " + transactionDate);
		Date currentDate = new Date(System.currentTimeMillis());
		log.info("Current Date:" + currentDate);
		return TransactionServiceUtil.getDifferenceInSeconds(transactionDate, currentDate) > maxDiff;
	}
}
