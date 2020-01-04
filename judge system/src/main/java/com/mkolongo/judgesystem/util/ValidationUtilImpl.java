package com.mkolongo.judgesystem.util;

import com.mkolongo.judgesystem.util.base.ValidationUtil;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import java.util.Set;

public class ValidationUtilImpl implements ValidationUtil {

    private Validator validator;

    public ValidationUtilImpl() {
        this.validator = Validation.buildDefaultValidatorFactory().getValidator();
    }

    @Override
    public <T> boolean isValid(T object) {
        return validator.validate(object).isEmpty();
    }

    @Override
    public <T> Set<ConstraintViolation<T>> getViolations(T object) {
        return validator.validate(object);
    }
}
