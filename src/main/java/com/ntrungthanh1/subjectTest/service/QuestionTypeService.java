package com.ntrungthanh1.subjectTest.service;

import com.ntrungthanh1.subjectTest.model.entity.QuestionType;
import org.springframework.transaction.annotation.Transactional;

public interface QuestionTypeService {

    @Transactional(readOnly = true)
    QuestionType getQuestionTypeById(Long id);
}
