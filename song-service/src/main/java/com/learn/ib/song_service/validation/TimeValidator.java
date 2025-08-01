package com.learn.ib.song_service.validation;

import com.learn.ib.song_service.validation.annotation.TimeValidation;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.util.regex.Pattern;

public class TimeValidator implements ConstraintValidator<TimeValidation, String> {

    private static final Pattern DURATION_PATTERN = Pattern.compile("^[0-5]\\d:[0-5]\\d$");

    @Override

    public boolean isValid(String duration, ConstraintValidatorContext context) {
        if (duration == null || duration.isBlank()) {
            return false;
        }

        return DURATION_PATTERN.matcher(duration).matches();
    }

}
