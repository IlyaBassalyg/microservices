package com.learn.ib.song_service.validation.annotation;

import com.learn.ib.song_service.validation.YearValidator;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Constraint(validatedBy = YearValidator.class)
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface YearValidation {
    String message() default "Year must be between 1900 and 2099";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
