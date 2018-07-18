package com.n26.transaction.service.controller;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import com.n26.transaction.service.bean.TransactionRequestVO;
import com.n26.transaction.service.constant.TransactionServiceMappingPath;
import com.n26.transaction.service.provider.TransactionService;

/**
 * Controller to expose apis for the transactions
 * 
 * @author Sanjib Pramanick
 *
 */
@Controller
public class TransactionController {

	@Autowired
	private TransactionService service;

	/**
	 * post mapping to introduce a transaction into the system
	 * 
	 * @param transactionRequest
	 * @param response
	 */
	@PostMapping(value = TransactionServiceMappingPath.TRANSACTION_MAPPING_PATH)
	public @ResponseBody void introduceTransaction(@RequestBody TransactionRequestVO transactionRequest,
			HttpServletResponse response) {
		response.setStatus(service.createEntry(transactionRequest));
	}
}
