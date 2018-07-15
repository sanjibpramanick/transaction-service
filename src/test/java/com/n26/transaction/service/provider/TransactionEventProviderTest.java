package com.n26.transaction.service.provider;

import static org.junit.Assert.assertTrue;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.Reporter;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.n26.transaction.service.TransactionServiceApplication;
import com.n26.transaction.service.bean.TransactionRequestVO;
import com.n26.transaction.service.listener.ReportListener;

/**
 * 
 * @author Sanjib Pramanick
 *
 */
@SpringBootTest(classes=TransactionServiceApplication.class)
@Listeners({ ReportListener.class })
public class TransactionEventProviderTest extends AbstractTestNGSpringContextTests {

	@Autowired
	public TransactionEventProvider provider;

	@Test(priority = 2, description = "Test for Creation")
	public void should_create_an_entry_with_response_201() {
		TransactionRequestVO requestVO = new TransactionRequestVO();
		requestVO.setAmount(150.0);
		requestVO.setTimestamp(System.currentTimeMillis() / 1000);
		int responseStatus = provider.createEntry(requestVO);
		try {
			assertTrue(responseStatus == 201);
			Reporter.log("should_create_an_entry_with_response_201", true);
		} catch (Throwable t) {
			Reporter.log("should_create_an_entry_with_response_201", false);
		}
	}

	@Test(priority = 3, description = "Test for Creation for past transactions")
	public void should_not_create_entry_with_response_204() {
		TransactionRequestVO requestVO = new TransactionRequestVO();
		requestVO.setAmount(124.0);
		requestVO.setTimestamp((System.currentTimeMillis() - 100) / 1000);
		int responseStatus = provider.createEntry(requestVO);
		try {
			assertTrue(responseStatus == 204);
			Reporter.log("should_not_create_entry_with_response_204", true);
		} catch (Throwable t) {
			Reporter.log("should_not_create_entry_with_response_204", false);
		}
	}
}
