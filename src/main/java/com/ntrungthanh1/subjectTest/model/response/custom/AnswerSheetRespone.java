package com.ntrungthanh1.subjectTest.model.response.custom;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
public class AnswerSheetRespone {
    Long id;
    String choiceAnswer;
    Date choiceTime;
    double grade;
    Long optionId;
    Long questionId;
    Long testingResult;
}
