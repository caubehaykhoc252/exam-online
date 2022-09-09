package com.ntrungthanh1.subjectTest.model.projection;

import com.ntrungthanh1.subjectTest.model.entity.constant.ActiveStatus;

import java.util.List;

public interface QuestionProjection {
    Long getId();
    String getQuestion();

    double getMark();
    QuestionTypeId getQuestionType();
    List<OptionProjection> getOptions();
    ActiveStatus getStatus();

    interface QuestionTypeId{
        Long getId();
        String getTypeName();
    }
}
