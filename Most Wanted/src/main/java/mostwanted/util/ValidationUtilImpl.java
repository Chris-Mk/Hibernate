package mostwanted.util;

import javax.validation.Validation;
import javax.validation.Validator;

public class ValidationUtilImpl implements ValidationUtil {

    @Override
    public <E> boolean isValid(E entity) {
        return Validation.buildDefaultValidatorFactory()
                .getValidator()
                .validate(entity)
                .isEmpty();
    }
}
