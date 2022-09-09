package com.ntrungthanh1.subjectTest.controller;

import com.ntrungthanh1.subjectTest.dto.QuestionDTO;
import com.ntrungthanh1.subjectTest.exception.ServiceException;
import com.ntrungthanh1.subjectTest.exception.custom.NotFoundException;
import com.ntrungthanh1.subjectTest.model.projection.QuestionProjection;
import com.ntrungthanh1.subjectTest.model.response.AbstractResponse;
import com.ntrungthanh1.subjectTest.model.response.custom.QuestionResponse;
import com.ntrungthanh1.subjectTest.model.response.error.BaseErrorResponse;
import com.ntrungthanh1.subjectTest.model.response.success.BaseResultResponse;
import com.ntrungthanh1.subjectTest.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@PreAuthorize("hasRole('ADMIN') or hasRole('LECTURER') or hasRole('SUPER_ADMIN') or hasRole('STUDENT')")
public class QuestionController {
    @Autowired
    private QuestionService questionService;

    /**
     * API get list question
     *
     * @return
     */
    @GetMapping(value = "${api.url.question.getListQuestion}")
    public ResponseEntity<AbstractResponse> getListQuestion() {
        List<QuestionProjection> result = Optional.ofNullable(questionService.getListQuestion())
                .orElseThrow(() -> new NotFoundException("Not found question"));

        AbstractResponse response = BaseResultResponse.builder()
                .code(HttpStatus.OK.value())
                .message("Get list question successfully")
                .data(result)
                .build();

        return ResponseEntity.ok(response);
    }

    /**
     * API get 1 question by id question
     *
     * @param id
     * @return
     */
    @GetMapping(value = "${api.url.question.getQuestionById}")
    public ResponseEntity<AbstractResponse> getQuestionById(@RequestParam Long id) {
        QuestionProjection result = Optional.ofNullable(questionService.getQuestionById(id))
                .orElseThrow(() -> new NotFoundException(String.format("Question with id = %s not found", id)));;

        AbstractResponse response = BaseResultResponse.builder()
                .code(HttpStatus.OK.value())
                .message("Get question successfully")
                .data(result)
                .build();

        return ResponseEntity.ok(response);
    }

    /**
     * API create a question
     *
     * @param questionDTO
     * @return
     */
    @PostMapping(value = "${api.url.question.createQuestion}")
    public ResponseEntity<AbstractResponse> createQuestion(@RequestBody QuestionDTO questionDTO) {
        boolean check = Optional.ofNullable(questionService.createQuestion(questionDTO))
                .orElseThrow(() -> new ServiceException("Create question failed"));
        AbstractResponse response;
        if (check) {
            response = BaseResultResponse.builder()
                    .code(HttpStatus.CREATED.value())
                    .message("Create question successfully")
                    .data(questionDTO)
                    .build();

            return new ResponseEntity<>(response, HttpStatus.CREATED);
        } else {
            response = BaseErrorResponse.builder()
                    .code(HttpStatus.INTERNAL_SERVER_ERROR.value())
                    .message("Create question failed")
                    .build();

            return ResponseEntity.internalServerError().body(response);
        }

    }

    @GetMapping(value = "${api.url.question.getListQuestionByTest}")
    public ResponseEntity getListQuestionByTest(@RequestParam Long testId , @RequestParam Long userId , @RequestParam Long testingResult)
    {
        QuestionResponse result = questionService.getListQuestionByTest(testId , userId , testingResult);

        AbstractResponse response= BaseResultResponse.builder()
                .code(HttpStatus.OK.value())
                .message("Get list Test successfully")
                .data(result)
                .build();
        return ResponseEntity.ok(result);
    }

    @GetMapping(value = "${api.url.question.getTestDetail}")
    public ResponseEntity getQuestionByTest(@RequestParam Long testId , @RequestParam String testCode)
    {
        List<QuestionProjection> result = questionService.getListQuestionByTest(testId , testCode);

        AbstractResponse response= BaseResultResponse.builder()
                .code(HttpStatus.OK.value())
                .message("Get list Test successfully")
                .data(result)
                .build();
        return ResponseEntity.ok(result);
    }

}
