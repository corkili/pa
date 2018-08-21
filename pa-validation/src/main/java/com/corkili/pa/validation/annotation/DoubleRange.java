package com.corkili.pa.validation.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Documented
@Target({ElementType.FIELD, ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
public @interface DoubleRange {

    DoubleValue min() default @DoubleValue(value = Double.MIN_VALUE);

    boolean minInclude() default true;

    DoubleValue max() default @DoubleValue(value = Double.MAX_VALUE);

    boolean maxInclude() default true;

}
