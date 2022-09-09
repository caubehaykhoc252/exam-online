package com.ntrungthanh1.subjectTest.exception;

import lombok.Getter;

@Getter
public class CustomResponseStatusException extends Exception {
    private int code;

    public CustomResponseStatusException(int code, String message) {
        super(message);
        this.code = code;
    }

    @Override
    public String getMessage() {
        return super.getMessage();
    }
}
