package com.n26.transaction.service.provider;

import com.n26.transaction.service.bean.TransactionStatistic;

/**
 * 
 * @author sanjib.pramanick
 *
 */
public interface StatisticsService {
	
	public TransactionStatistic getStatistic(String dataSetName);

}
