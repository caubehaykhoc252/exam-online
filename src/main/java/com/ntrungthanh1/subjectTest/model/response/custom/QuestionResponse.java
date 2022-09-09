package com.ntrungthanh1.subjectTest.model.response.custom;

import com.ntrungthanh1.subjectTest.model.entity.Option;
import com.ntrungthanh1.subjectTest.model.entity.Question;
import com.ntrungthanh1.subjectTest.model.projection.QuestionProjection;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class QuestionResponse {
    String testCode;
    Long testId;
    Long timer;
    List<Long> choosenAnswers ;
    List<QuestionProjection> listQuestion;
}
