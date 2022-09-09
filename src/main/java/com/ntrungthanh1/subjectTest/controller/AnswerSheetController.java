package com.ntrungthanh1.subjectTest.controller;

import com.ntrungthanh1.subjectTest.model.entity.AnswerSheet;
import com.ntrungthanh1.subjectTest.model.projection.AnswerProjection;
import com.ntrungthanh1.subjectTest.model.request.AnswerSheetRequest;
import com.ntrungthanh1.subjectTest.model.response.AbstractResponse;
import com.ntrungthanh1.subjectTest.model.response.success.BaseResultResponse;
import com.ntrungthanh1.subjectTest.service.AnswerSheetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
//@PreAuthorize("hasRole('STUDENT')")
public class AnswerSheetController {
    @Autowired
    AnswerSheetService answerSheetService;

    @PostMapping(value = "${api.url.answerSheet.createAnswerSheet}")
    public ResponseEntity<AbstractResponse> createAnswerSheet(@RequestBody AnswerSheetRequest answerSheetRequest) {
        answerSheetService.createAnswerSheet(answerSheetRequest);

        AbstractResponse response = BaseResultResponse.builder()
                .code(HttpStatus.CREATED.value())
                .message("Create Answer successfully")
                .build();

        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @GetMapping(value = "${api.url.answerSheet.getListAnswer}")
    public ResponseEntity<AbstractResponse> getListAnswer(@RequestParam Long testResultId) {
        List<AnswerProjection> data =  answerSheetService.getListAnswer(testResultId);

        AbstractResponse response = BaseResultResponse.builder()
                .code(HttpStatus.OK.value())
                .message("Get List Answer successfully")
                .data(data)
                .build();

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

}
