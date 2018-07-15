package com.n26.transaction.service.annotation;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * 
 * @author Sanjib Pramanick
 *
 */
@Retention(RetentionPolicy.RUNTIME)
public @interface DefectInfo {

    String jirId() default "";

    String stepName() default "";
}
