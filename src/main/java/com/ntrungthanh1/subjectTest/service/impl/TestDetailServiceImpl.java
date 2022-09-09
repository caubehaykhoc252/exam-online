package com.ntrungthanh1.subjectTest.service.impl;

import com.ntrungthanh1.subjectTest.exception.ServiceException;
import com.ntrungthanh1.subjectTest.model.entity.Question;
import com.ntrungthanh1.subjectTest.model.entity.Test;
import com.ntrungthanh1.subjectTest.model.entity.TestingDetail;
import com.ntrungthanh1.subjectTest.model.projection.QuestionProjection;
import com.ntrungthanh1.subjectTest.repository.QuestionRepository;
import com.ntrungthanh1.subjectTest.repository.TestRepository;
import com.ntrungthanh1.subjectTest.repository.TestingDetailRepository;
import com.ntrungthanh1.subjectTest.service.TestDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@Service
public class TestDetailServiceImpl implements TestDetailService {
    @Autowired
    TestingDetailRepository testingDetailRepository;

    @Autowired
    TestRepository testRepository;

    @Autowired
    QuestionRepository questionRepository;

    @Override
    @Transactional
    public Boolean createTestDetail(Long testId, Long NumberCode) {
        if (testId == null || NumberCode == null)
            throw new NullPointerException("Save test detail is fail - TestId or NumberCode diff null");

        Test test = testRepository.findTestById(testId);

        if (test == null) {
            return false;            //no found test id
        }

        List<Question> listQuestion = questionRepository.findListBySubjectId(test.getSubject().getId());
        List<Question> listClone = listQuestion;

        if ( listQuestion.size() > NumberCode) {
            return false;
        } else {
            for (int i = 1; i <= NumberCode; i++) {
                //set random list Question
                Collections.shuffle(listClone);

                //check equal random list with old list
                if (listQuestion.equals(listClone)) {
                    for (int j = 0; j < listQuestion.size(); j++) {
                        TestingDetail testingDetail = new TestingDetail();
                        testingDetail.setTestCode("code_" + i);
                        testingDetail.setTest(test);
                        testingDetail.setTestOrder(j + 1);
                        testingDetail.setQuestion(listQuestion.get(j));

                        //create test detail
                        testingDetailRepository.save(testingDetail);
                    }
                }
            }
            return true;
        }
    }

    @Override
    @Transactional
    public List<QuestionProjection> getListQuestionByTestId(Long testId){
        Test test = Optional.ofNullable(testRepository.findTestById(testId))
                .orElseThrow(() -> new ServiceException("Not found Test by Id"));

        List<QuestionProjection> list =  testingDetailRepository.getListQuestionByTest(test.getId());
        return list;
    }

}