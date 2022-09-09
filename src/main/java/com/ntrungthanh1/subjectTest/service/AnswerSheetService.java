package com.ntrungthanh1.subjectTest.service;

import com.ntrungthanh1.subjectTest.model.entity.AnswerSheet;
import com.ntrungthanh1.subjectTest.model.projection.AnswerProjection;
import com.ntrungthanh1.subjectTest.model.request.AnswerSheetRequest;

import java.util.List;

public interface AnswerSheetService {
    List<AnswerSheet> createAnswerSheet(AnswerSheetRequest answerSheetRequest);

    List<AnswerProjection> getListAnswer(Long testingResultId);
}
