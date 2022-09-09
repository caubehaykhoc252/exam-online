package com.ntrungthanh1.subjectTest.model.request;

public class LoginRequest extends AbstractUserRequest {
    public LoginRequest(String username, String password) {
        super(username, password);
    }
}