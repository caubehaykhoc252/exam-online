package com.ntrungthanh1.subjectTest.model.projection;

import com.ntrungthanh1.subjectTest.model.entity.Option;
import com.ntrungthanh1.subjectTest.model.entity.Question;
import com.ntrungthanh1.subjectTest.model.entity.QuestionType;
import com.ntrungthanh1.subjectTest.model.entity.TestingResult;

import java.util.Date;
import java.util.List;

public interface AnswerProjection {
    Long getId();
    String getChosenAnswer();
    Date getChosenTime();
    double getGrade();
    testingResultId getTestingResult();
    questionId getQuestion();
    optionId getOption();

    interface testingResultId{
        Long getId();
    }
    interface  questionId{
        Long getId();
    }

    interface  optionId{
        Long getId();
    }
}
