package com.n26.transaction.service.provider;

import com.n26.transaction.service.bean.TransactionRequestVO;

/**
 * 
 * @author sanjib.pramanick
 *
 */
public interface TransactionService {

	public int createEntry(TransactionRequestVO transactionRequest);
}
