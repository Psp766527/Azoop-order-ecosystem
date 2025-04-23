package com.rubicon.water.rubiconWater.validator;


import lombok.var;
import org.springframework.beans.factory.annotation.Autowired;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import javax.validation.ConstraintViolation;
import javax.validation.Validator;
import java.util.stream.Collectors;

public class RequestModelValidator implements ConstraintValidator<ValidRequestModel, Object> {
    @Autowired
    private Validator validator;

    @Override
    public void initialize(ValidRequestModel constraintAnnotation) {
    }

    @Override
    public boolean isValid(Object value, ConstraintValidatorContext context) {
        if (value == null) {
            context.disableDefaultConstraintViolation();
            context.buildConstraintViolationWithTemplate("Request model cannot be null")
                    .addConstraintViolation();
            return false;
        }
//ConstraintViolation
        var violations = validator.validate(value);
        if (!violations.isEmpty()) {
            context.disableDefaultConstraintViolation();
            String errorMessage = violations.stream()
                    .map(ConstraintViolation::getMessage)
                    .collect(Collectors.joining(", "));
            context.buildConstraintViolationWithTemplate(errorMessage)
                    .addConstraintViolation();
            return false;
        }

        return true;
    }
}