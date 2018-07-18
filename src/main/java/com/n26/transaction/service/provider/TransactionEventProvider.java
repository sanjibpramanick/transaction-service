package com.n26.transaction.service.provider;

import java.util.concurrent.CopyOnWriteArrayList;

import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.n26.transaction.service.annotation.Log;
import com.n26.transaction.service.bean.TransactionRequestVO;
import com.n26.transaction.service.constant.TransactionServiceConstant;
import com.n26.transaction.service.util.TransactionServiceUtil;

/**
 * Transaction Provider Service
 * 
 * @author Sanjib Pramanick
 *
 */
@Service
public class TransactionEventProvider implements TransactionService{

	private static Logger LOG = LoggerFactory.getLogger(TransactionEventProvider.class.getName());

	/**
	 * returns 201 in case of transaction within 60 seconds else 204
	 * 
	 * @param transactionRequest
	 * @return
	 */
	@Log
	@Override
	public int createEntry(TransactionRequestVO transactionRequest) {
		LOG.info("Transaction Received");
		if (transactionRequest == null) {
			return HttpServletResponse.SC_BAD_REQUEST;
		}
		if (!TransactionServiceUtil.isTansactionMadeBeforeTime(
				TransactionServiceUtil.convertEpochToTimestamp(transactionRequest.getTimestamp()),
				TransactionServiceConstant._60_SECONDS)) {
			CopyOnWriteArrayList<TransactionRequestVO> list = StatisticsProvider.TRANSACTION_MAP
					.get(TransactionServiceConstant.LAST_MINUTE_KEY);
			if (list == null) {
				list = new CopyOnWriteArrayList<>();
			}
			list.add(transactionRequest);
			StatisticsProvider.TRANSACTION_MAP.put(TransactionServiceConstant.LAST_MINUTE_KEY, list);
			return HttpServletResponse.SC_NO_CONTENT;
		}
		return HttpServletResponse.SC_CREATED;
	}

}
