package com.ntrungthanh1.subjectTest.model.response.custom;

import com.ntrungthanh1.subjectTest.model.projection.QuestionProjection;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class TestingResultRespone {
    Long id;
    String testCode;
    double grade;
    Date finishTime;
    Date startTime;
    Long testId;

    List<QuestionProjection> listQuestions;

}
