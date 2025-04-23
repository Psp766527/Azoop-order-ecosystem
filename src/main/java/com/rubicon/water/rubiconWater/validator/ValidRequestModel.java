package com.rubicon.water.rubiconWater.validator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Constraint(validatedBy = RequestModelValidator.class)
@Target({ElementType.PARAMETER, ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface ValidRequestModel {
    String message() default "Invalid request model";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}