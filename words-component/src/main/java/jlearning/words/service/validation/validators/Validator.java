package jlearning.words.service.validation.validators;

import jlearning.words.service.exception.ValidationException;

import static java.lang.String.format;

public interface Validator<T> {
    void validate(Object target);

    /**
     * Defines type which validator is applicable to
     */
    Class<T> getTargetType();

    default T cast(Object target) {
        Class<T> targetType = getTargetType();
        Class<?> targetClass = target.getClass();
        if (targetType.equals(targetClass)) {
            return targetType.cast(target);
        } else {
            String errorMessage = format("Types do not match. Expected: %s, but got: %s",
                    targetType.getSimpleName(),
                    targetClass.getSimpleName());

            throw new ValidationException(errorMessage);
        }
    }

    default void doBasicValidation(Object target) {
        if (target == null) {
            throw new ValidationException("Argument should not be null");
        }
    }
}
