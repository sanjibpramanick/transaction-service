package com.n26.transaction.service.aspect;

import java.util.Arrays;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

/**
 * Aspect provided for logging
 *
 * @author Sanjib Pramanick
 */
@Aspect
@Component
public class LogHandler {

	@Around("@annotation(com.n26.transaction.service.annotation.Log)")
	public Object around(ProceedingJoinPoint point) {

		Object result = null;
		Logger logger = LoggerFactory.getLogger(point.getTarget().getClass());
		try {
			long start = System.currentTimeMillis();
			result = point.proceed();
			long end = System.currentTimeMillis();
			logger.info("\n Mthod Name : " + MethodSignature.class.cast(point.getSignature()).getMethod().getName() + "\n Input Parameters : " + Arrays.asList(point.getArgs()) + "\n Result : " + result
					+ "\n Time Taken : " + (end - start) + " milliseconds");
		} catch (Throwable e) {
			logger.error(e.getMessage(), e);
		}
		return result;
	}
}
