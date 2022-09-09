package com.ntrungthanh1.subjectTest.controller;

import com.ntrungthanh1.subjectTest.model.entity.Test;
import com.ntrungthanh1.subjectTest.model.response.AbstractResponse;
import com.ntrungthanh1.subjectTest.model.response.success.BaseResultResponse;
import com.ntrungthanh1.subjectTest.service.TestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
//@PreAuthorize("hasRole('ADMIN') or hasRole('LECTURER') or hasRole('SUPER_ADMIN')")
public class TestController {
    @Autowired
    private TestService testService;

    @GetMapping(value = "${api.url.exams.getListTest}")
    public ResponseEntity<AbstractResponse> getListTest(@RequestParam Long groupId , @RequestParam Long studentId) {
        List<Test> result = testService.getListTest(groupId, studentId);
        AbstractResponse  response = BaseResultResponse.builder()
                .code(HttpStatus.OK.value())
                .message("Get list Test successfully")
                .data(result)
                .build();
        return ResponseEntity.ok(response);
    }


}
