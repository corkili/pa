package com.corkili.pa.validation.util;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import com.corkili.pa.validation.annotation.IntConstraint;
import com.corkili.pa.validation.annotation.ShortConstraint;
import com.corkili.pa.validation.annotation.StringConstraint;

public abstract class ConstraintUtil {

    private static Set<Class<?>> constraints;

    static {
        constraints = new HashSet<>();
        constraints.addAll(Arrays.asList(StringConstraint.class, ShortConstraint.class,
                IntConstraint.class));
    }

    public static boolean isValidatedField(Field field) {
        Annotation[] annotations = field.getDeclaredAnnotations();
        boolean success = false;
        for (Annotation annotation : annotations) {
            if (constraints.contains(annotation.annotationType())) {
                success = true;
                break;
            }
        }
        return success;
    }

}
