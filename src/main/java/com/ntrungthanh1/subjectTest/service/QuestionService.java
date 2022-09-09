package com.ntrungthanh1.subjectTest.service;

import com.ntrungthanh1.subjectTest.dto.QuestionDTO;
import com.ntrungthanh1.subjectTest.model.entity.Question;
import com.ntrungthanh1.subjectTest.model.projection.QuestionProjection;
import com.ntrungthanh1.subjectTest.model.response.custom.QuestionResponse;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface QuestionService {
    @Transactional(readOnly = true)
    List<QuestionProjection> getListQuestion();

    @Transactional
    boolean createQuestion(QuestionDTO questionDTO);

    @Transactional(readOnly = true)
    Question getQuestionByQuestion(String question);

    @Transactional(readOnly = true)
    QuestionProjection getQuestionById(Long id);

    @Transactional(readOnly = true)
    QuestionResponse getListQuestionByTest(Long testId , Long userId , Long testingResult);
    @Transactional(readOnly = true)
    List<QuestionProjection> getListQuestionByTest(Long testId , String testCode);


}
