package com.ntrungthanh1.subjectTest.controller;


import com.ntrungthanh1.subjectTest.exception.ServiceException;
import com.ntrungthanh1.subjectTest.exception.custom.NotFoundException;
import com.ntrungthanh1.subjectTest.model.entity.Group;
import com.ntrungthanh1.subjectTest.model.response.AbstractResponse;
import com.ntrungthanh1.subjectTest.model.response.success.BaseResultResponse;
import com.ntrungthanh1.subjectTest.service.GroupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
@PreAuthorize("hasRole('STUDENT')")
public class GroupController {

    @Autowired
    GroupService  groupService;

    @GetMapping(value = "${api.url.group.getListGroup}")
    public ResponseEntity<AbstractResponse> getListGroupByUser(@RequestParam Long studentId) {
        AbstractResponse response = BaseResultResponse.builder()
                .code(HttpStatus.OK.value())
                .message("Get group user successfully")
                .data(groupService.getListGroupByUser(studentId))
                .build();
        return new ResponseEntity<>(response , HttpStatus.OK);
    }
}
