package com.ntrungthanh1.subjectTest.controller;

import com.ntrungthanh1.subjectTest.model.entity.TestingResult;
import com.ntrungthanh1.subjectTest.model.projection.QuestionProjection;
import com.ntrungthanh1.subjectTest.model.response.AbstractResponse;
import com.ntrungthanh1.subjectTest.model.response.success.BaseResultResponse;
import com.ntrungthanh1.subjectTest.service.TestDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@PreAuthorize("hasRole('ADMIN') or hasRole('LECTURER') or hasRole('SUPER_ADMIN') or hasRole('STUDENT')")
public class TestDetailsController {
    @Autowired
    TestDetailService testDetailService;

    @PostMapping(value = "${api.url.testingDetail.createTestingDetail}")
    public ResponseEntity<AbstractResponse> createTestingDetail(@RequestParam Long testId,
                                                                @RequestParam Long NumberCode) {
        testDetailService.createTestDetail(testId, NumberCode);

        AbstractResponse response = BaseResultResponse.builder()
                .code(HttpStatus.CREATED.value())
                .message("Create Testing Detail successfully")
                .build();

        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @PostMapping(value = "${api.url.testingDetail.getQuestionByTestId}")
    public ResponseEntity<AbstractResponse> getQuestionByTestId(@RequestParam Long testId) {
        List<QuestionProjection> result = testDetailService.getListQuestionByTestId(testId);

        AbstractResponse response = BaseResultResponse.builder()
                .code(HttpStatus.OK.value())
                .message("Get list Question successfully")
                .data(result)
                .build();

        return ResponseEntity.ok(response);
    }

}
