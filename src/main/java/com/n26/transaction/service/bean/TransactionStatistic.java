package com.n26.transaction.service.bean;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 * 
 * @author Sanjib Pramanick
 *
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@EqualsAndHashCode
public class TransactionStatistic {

	private Double sum = 0.0;
	private Double avg = 0.0;
	private Double max = 0.0;
	private Double min = 0.0;
	private Long count = 0L;
}
