package com.n26.transaction.service.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.n26.transaction.service.bean.TransactionStatistic;
import com.n26.transaction.service.constant.TransactionServiceConstant;
import com.n26.transaction.service.constant.TransactionServiceMappingPath;
import com.n26.transaction.service.provider.StatisticsService;

/**
 * Controller to expose apis for the statistics
 * 
 * @author Sanjib Pramanick
 *
 */
@Controller
public class StatisticsController {

	@Autowired
	private StatisticsService service;

	/**
	 * returns statistic of last minute
	 * 
	 * @return
	 */
	@GetMapping(value = TransactionServiceMappingPath.STATISTICS_MAPPING_PATH)
	public @ResponseBody TransactionStatistic getStatisticOfLastMinute() {
		return service.getStatistic(TransactionServiceConstant.LAST_MINUTE_KEY);
	}

}
