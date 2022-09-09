package com.ntrungthanh1.subjectTest.controller;

import com.ntrungthanh1.subjectTest.model.entity.TestingResult;
import com.ntrungthanh1.subjectTest.model.response.AbstractResponse;
import com.ntrungthanh1.subjectTest.model.response.success.BaseResultResponse;
import com.ntrungthanh1.subjectTest.service.TestResultService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@PreAuthorize("hasRole('ADMIN') or hasRole('LECTURER') or hasRole('SUPER_ADMIN') or hasRole('STUDENT')")
public class TestingResultController {
    @Autowired
    private TestResultService testResultService;

    @GetMapping(value = "${api.url.exams.getTestingResult}")
    public ResponseEntity<AbstractResponse> saveTestingResult(@RequestParam Long testId , @RequestParam Long studentId) {
        TestingResult result = testResultService.saveTestingResult(testId , studentId);
        AbstractResponse  response = BaseResultResponse.builder()
                .code(HttpStatus.OK.value())
                .message("Get list Test successfully")
                .data(result)
                .build();
        return ResponseEntity.ok(response);
    }

    @GetMapping(value = "${api.url.exams.getTimeByTestingResult}")
    public ResponseEntity getTimeByTestingResult(@RequestParam Long testingResultId) {
        Long result = testResultService.getTime(testingResultId );
        AbstractResponse  response = BaseResultResponse.builder()
                .code(HttpStatus.OK.value())
                .message("Get list Test successfully")
                .data(result)
                .build();
        return ResponseEntity.ok(result);
    }

}
