package com.n26.transaction.service.task;

import java.util.Collections;
import java.util.concurrent.CopyOnWriteArrayList;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.n26.transaction.service.bean.TransactionRequestVO;
import com.n26.transaction.service.constant.TransactionServiceConstant;
import com.n26.transaction.service.provider.StatisticsProvider;
import com.n26.transaction.service.util.TransactionServiceUtil;

/**
 * Component for configuring tasks
 * 
 * @author Sanjib Pramanick
 *
 */
@Component
public class TransactionMonitor {

	private static Logger log = LoggerFactory.getLogger(TransactionMonitor.class.getName());

	/**
	 * task to eliminate the old transaction
	 */
	@Scheduled(fixedRate = TransactionServiceConstant._1_SECOND_IN_MILLI)
	public void trackTansactionBasedOnTimestamp() {
		log.info("Current Base: " + StatisticsProvider.TRANSACTION_MAP);
		CopyOnWriteArrayList<TransactionRequestVO> list = StatisticsProvider.TRANSACTION_MAP
				.get(TransactionServiceConstant.LAST_MINUTE_KEY);
		if (list == null) {
			return;
		}
		Collections.sort(list, (o1, o2) -> {
			return o1.getTimestamp().compareTo(o2.getTimestamp());
		});
		StatisticsProvider.TRANSACTION_MAP.put(TransactionServiceConstant.LAST_MINUTE_KEY, modify(list));
	}

	/**
	 * removes the old transactions
	 * 
	 * @param list
	 * @return
	 */
	private CopyOnWriteArrayList<TransactionRequestVO> modify(CopyOnWriteArrayList<TransactionRequestVO> list) {
		for (TransactionRequestVO transaction : list) {
			if (TransactionServiceUtil.isTansactionMadeBeforeTime(
					TransactionServiceUtil.convertEpochToTimestamp(transaction.getTimestamp()),
					TransactionServiceConstant._60_SECONDS)) {
				list.remove(transaction);
			} else {
				break;
			}
		}
		return list;
	}
}
