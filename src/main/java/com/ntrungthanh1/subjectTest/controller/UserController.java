package com.ntrungthanh1.subjectTest.controller;

import com.ntrungthanh1.subjectTest.exception.custom.NotFoundException;
import com.ntrungthanh1.subjectTest.model.projection.UserProjection;
import com.ntrungthanh1.subjectTest.model.response.AbstractResponse;
import com.ntrungthanh1.subjectTest.model.response.success.BaseResultResponse;
import com.ntrungthanh1.subjectTest.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@PreAuthorize("hasRole('ADMIN') or hasRole('LECTURER') or hasRole('SUPER_ADMIN')")
public class UserController {
    @Autowired
    private UserService userService;

    @GetMapping(value = "${api.url.user.getListUser}")
    public ResponseEntity<AbstractResponse> getListUser() {
        List<UserProjection> result = userService.getListUser();

        AbstractResponse response = BaseResultResponse.builder()
                .code(HttpStatus.OK.value())
                .message("Get list user successfully")
                .data(result)
                .build();

        return ResponseEntity.ok(response);
    }

    @PostMapping(value = "${api.url.user.getUserById}")
    public ResponseEntity<AbstractResponse> getUserById(@RequestParam Long id) {
        if (id == null) throw new NotFoundException("Get User fail - User Id is not null");

        UserProjection result = Optional.ofNullable(userService.getUserById(id))
                .orElseThrow(() -> new NotFoundException(String.format("User with id = %s not found", id)));

        AbstractResponse response = BaseResultResponse.builder()
                .code(HttpStatus.OK.value())
                .message("Get list user successfully")
                .data(result)
                .build();

        return ResponseEntity.ok(response);
    }
}
