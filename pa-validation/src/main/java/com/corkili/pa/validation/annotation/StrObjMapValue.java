package com.corkili.pa.validation.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Documented
@Target({ElementType.FIELD, ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
public @interface StrObjMapValue {

    String key();

    Class<?> valueType();

    boolean required() default true;

    boolean validateString() default false;

    StringConstraint stringConstraint() default @StringConstraint;

    boolean validateShort() default false;

    ShortConstraint shortConstraint() default @ShortConstraint;

    boolean validateInt() default false;

    IntConstraint intConstraint() default @IntConstraint;

    boolean validateLong() default false;

    LongConstraint longConstraint() default @LongConstraint;

    boolean validateFloat() default false;

    FloatConstraint floatConstraint() default @FloatConstraint;

    boolean validateDouble() default false;

    DoubleConstraint doubleConstraint() default @DoubleConstraint;

}
