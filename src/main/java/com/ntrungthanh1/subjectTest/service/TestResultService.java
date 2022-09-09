package com.ntrungthanh1.subjectTest.service;

import com.ntrungthanh1.subjectTest.model.entity.TestingResult;

import java.util.List;

public interface TestResultService {
    TestingResult saveTestingResult(Long testId  , Long userId);

    List<TestingResult> getTestingResultByTestIdAndUserId(Long test , Long user);

    Long getTime(Long testingResult);

}
