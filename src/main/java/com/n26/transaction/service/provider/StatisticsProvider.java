package com.n26.transaction.service.provider;

import java.util.DoubleSummaryStatistics;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.n26.transaction.service.bean.TransactionRequestVO;
import com.n26.transaction.service.bean.TransactionStatistic;

/**
 * Statistics Provider Service
 * 
 * @author Sanjib Pramanick
 *
 */
@Service
public class StatisticsProvider {

	public static final Map<String, CopyOnWriteArrayList<TransactionRequestVO>> TRANSACTION_MAP = new HashMap<>();

	public TransactionStatistic getStatistic(String dataSetName) {
		return calculate(TRANSACTION_MAP.get(dataSetName));
	}

	private TransactionStatistic calculate(CopyOnWriteArrayList<TransactionRequestVO> list) {
		if (list == null) {
			return new TransactionStatistic();
		}
		DoubleSummaryStatistics stats = list.stream()
				.collect(Collectors.summarizingDouble(TransactionRequestVO::getAmount));
		return buildStatistic(stats);
	}

	private TransactionStatistic buildStatistic(DoubleSummaryStatistics statistic) {
		return new TransactionStatistic(statistic.getSum(), statistic.getAverage(), statistic.getMax(),
				statistic.getMin(), statistic.getCount());
	}
}
