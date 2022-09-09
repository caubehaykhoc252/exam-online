package com.ntrungthanh1.subjectTest.service.impl;

import com.ntrungthanh1.subjectTest.model.entity.QuestionType;
import com.ntrungthanh1.subjectTest.repository.QuestionTypeRepository;
import com.ntrungthanh1.subjectTest.service.QuestionTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class QuestionTypeServiceImpl implements QuestionTypeService {
    @Autowired
    QuestionTypeRepository questionTypeRepository;

    @Override
    public QuestionType getQuestionTypeById(Long id) {
        return questionTypeRepository.findQuestionTypeById(id);
    }
}
