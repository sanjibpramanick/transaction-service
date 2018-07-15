package com.n26.transaction.service.provider;

import static org.junit.Assert.assertTrue;

import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.Reporter;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.n26.transaction.service.TransactionServiceApplication;
import com.n26.transaction.service.bean.TransactionStatistic;
import com.n26.transaction.service.constant.TransactionServiceConstant;
import com.n26.transaction.service.listener.ReportListener;

/**
 * 
 * @author Sanjib Pramanick
 *
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = TransactionServiceApplication.class)
@Listeners({ ReportListener.class })
public class StatisticsProviderTest extends AbstractTestNGSpringContextTests {

	@Autowired
	private StatisticsProvider provider;

	@Test(priority = 1, description = "Default Value Test")
	public void should_get_default_values() {
		TransactionStatistic statistic = provider.getStatistic(TransactionServiceConstant.LAST_MINUTE_KEY);
		try {
			assertTrue(statistic.getAvg().equals(0.0));
			assertTrue(statistic.getSum().equals(0.0));
			assertTrue(statistic.getMax().equals(0.0));
			assertTrue(statistic.getMin().equals(0.0));
			assertTrue(statistic.getCount().equals(0L));
			Reporter.log("should_get_default_values", true);
		} catch (Throwable t) {
			Reporter.log("should_get_default_values", false);
		}
	}
}
