package com.corkili.pa.validation.validator;

import java.lang.annotation.Annotation;

public abstract class AbstractValidator<E, A extends Annotation> implements Validator<E, A> {

    private boolean assertModel;

    public AbstractValidator() {
        this.assertModel = false;
    }

    public boolean isAssert() {
        return assertModel;
    }

    public void setAssertModel(boolean assertModel) {
        this.assertModel = assertModel;
    }
}
