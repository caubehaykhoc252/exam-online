package com.ntrungthanh1.subjectTest.service;

import com.ntrungthanh1.subjectTest.model.entity.Test;
import com.ntrungthanh1.subjectTest.model.request.TestModelRequest;

import java.util.List;

public interface TestService {

    List<Test> getListTest(Long groupId , Long userId);

//    Test createTest(TestModelRequest testModelRequest);
}
