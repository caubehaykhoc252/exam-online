package com.ntrungthanh1.subjectTest.model.response.success;


import com.ntrungthanh1.subjectTest.model.request.AbstractUserRequest;

public class UserResponse extends AbstractUserRequest {
    public UserResponse(String username, String password, String email, String fullName, String phone, String address, int status) {
        super(username, password, email, fullName, phone, address);
        this.status = status;
    }
}
