package com.corkili.pa.validation.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Documented
@Target({ElementType.FIELD, ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
public @interface ShortRange {

    short min() default Short.MIN_VALUE;

    boolean minInclude() default true;

    short max() default Short.MAX_VALUE;

    boolean maxInclude() default true;

}
