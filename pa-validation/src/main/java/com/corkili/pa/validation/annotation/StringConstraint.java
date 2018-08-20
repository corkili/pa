package com.corkili.pa.validation.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Documented
@Target({ElementType.FIELD, ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
public @interface StringConstraint {

    boolean notNull() default true;

    boolean notEmpty() default true;

    int length() default -1;

    IntRange[] lengthRanges() default {};

    String[] valueRange() default {};

    String[] contains() default {};

    String[] notContains() default {};

    String[] startWith() default {};

    String[] endWith() default {};

    boolean ignoreCase() default true;

    boolean lower() default true;

    boolean upper() default false;

    String regex() default "";

}
