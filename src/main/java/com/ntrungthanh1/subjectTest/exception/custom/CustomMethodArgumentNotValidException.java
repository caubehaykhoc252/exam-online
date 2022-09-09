package com.ntrungthanh1.subjectTest.exception.custom;

import lombok.AllArgsConstructor;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Errors;

@AllArgsConstructor
public class CustomMethodArgumentNotValidException extends RuntimeException {
    BindingResult bindingResult;

    public Errors getBindingResult() {
        return bindingResult;
    }
}
