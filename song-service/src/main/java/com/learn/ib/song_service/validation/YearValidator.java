package com.learn.ib.song_service.validation;

import com.learn.ib.song_service.validation.annotation.YearValidation;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class YearValidator implements ConstraintValidator<YearValidation, String> {

    @Override
    public void initialize(YearValidation constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        if (value == null || value.isEmpty()) {
            return false;
        }
        try {
            short year = Short.parseShort(value);
            return value.length() == 4 && year >= 1900 && year <= 2099;
        } catch (NumberFormatException e) {
            return false;
        }
    }
}
