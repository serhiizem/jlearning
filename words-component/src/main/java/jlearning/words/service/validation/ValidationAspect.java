package jlearning.words.service.validation;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import jlearning.words.service.validation.validators.Validator;

import java.lang.reflect.AnnotatedType;
import java.lang.reflect.Method;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import static java.util.Arrays.stream;

@Aspect
@Component
public class ValidationAspect {

    private final Map<Class<?>, Validator<?>> validatorsMap = new ConcurrentHashMap<>();

    @Autowired
    private void registerValidators(List<Validator> validators) {
        validators.forEach(validator -> {
            Class<?> targetType = validator.getClass();
            validatorsMap.putIfAbsent(targetType, validator);
        });
    }

    @Before("@annotation(jlearning.words.service.validation.Validate)")
    public void validate(JoinPoint joinPoint) {
        Class<?> targetClass = joinPoint.getTarget().getClass();
        // should consider caching for better performance
        stream(targetClass.getDeclaredMethods())
                .filter(method -> method.getAnnotation(Validate.class) != null)
                .forEach(method -> validateMethodArgs(method, joinPoint.getArgs()));
    }

    private void validateMethodArgs(Method method, Object[] args) {
        AnnotatedType[] annotatedParameterTypes = method.getAnnotatedParameterTypes();
        for (int argIndex = 0; argIndex < annotatedParameterTypes.length; argIndex++) {
            AnnotatedType annotatedParameter = annotatedParameterTypes[argIndex];
            ValidatorTypes validatorTypes = annotatedParameter.getAnnotation(ValidatorTypes.class);
            if (validatorTypes != null) {
                Class<?>[] validators = validatorTypes.values();
                Object argument = args[argIndex];

                validateArgument(argument, validators);
            }
        }
    }

    private void validateArgument(Object argument, Class<?>[] validatorClasses) {
        for (Class<?> validatorClass : validatorClasses) {
            Validator<?> validator = validatorsMap.get(validatorClass);
            if (validator != null) {
                validator.validate(argument);
            }
        }
    }
}
