package app.ccb.util;

import javax.validation.Validation;

public class ValidationUtilImpl implements ValidationUtil {

    @Override
    public <E> boolean isValid(E entity) {
        return Validation.buildDefaultValidatorFactory()
                .getValidator()
                .validate(entity)
                .isEmpty();
    }
}
