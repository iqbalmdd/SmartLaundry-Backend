package com.smartlaundry.utils;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;
import jakarta.validation.Validator;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Set;

@Component
@RequiredArgsConstructor
public class Validation {
    private final Validator validator;

    public void validate(Object o) {
        Set<ConstraintViolation<Object>> validate = validator.validate(o); //Collect All Validation
        if(!validate.isEmpty()){
            throw new ConstraintViolationException(validate);
        }
    }
}
