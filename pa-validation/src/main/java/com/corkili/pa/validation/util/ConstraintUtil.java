package com.corkili.pa.validation.util;

import java.lang.annotation.Annotation;
import java.lang.reflect.AnnotatedElement;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import com.corkili.pa.validation.annotation.IntConstraint;
import com.corkili.pa.validation.annotation.LongConstraint;
import com.corkili.pa.validation.annotation.ShortConstraint;
import com.corkili.pa.validation.annotation.StringConstraint;

public abstract class ConstraintUtil {

    private static Set<Class<?>> constraints;

    static {
        constraints = new HashSet<>();
        // TODO(corkili): add Constraint class
        constraints.addAll(Arrays.asList(StringConstraint.class, ShortConstraint.class,
                IntConstraint.class, LongConstraint.class));
    }

    public static boolean isValidatedField(AnnotatedElement element) {
        Annotation[] annotations = element.getDeclaredAnnotations();
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
