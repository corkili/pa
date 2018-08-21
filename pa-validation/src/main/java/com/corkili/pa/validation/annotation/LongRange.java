package com.corkili.pa.validation.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Documented
@Target({ElementType.FIELD, ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
public @interface LongRange {

    long min() default Long.MIN_VALUE;

    boolean minInclude() default true;

    long max() default Long.MAX_VALUE;

    boolean maxInclude() default false;

}
