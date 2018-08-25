package com.corkili.pa.validation.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Documented
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface FloatRange {

    FloatValue min() default @FloatValue(value = Float.MIN_VALUE);

    boolean minInclude() default true;

    FloatValue max() default @FloatValue(value = Float.MAX_VALUE);

    boolean maxInclude() default true;

}
