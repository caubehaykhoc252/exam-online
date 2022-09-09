package com.ntrungthanh1.subjectTest.service;

import com.ntrungthanh1.subjectTest.model.projection.QuestionProjection;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface TestDetailService {
    @Transactional
    Boolean createTestDetail(Long testId, Long NumberCode);

    @Transactional
    List<QuestionProjection> getListQuestionByTestId(Long testId);

}
