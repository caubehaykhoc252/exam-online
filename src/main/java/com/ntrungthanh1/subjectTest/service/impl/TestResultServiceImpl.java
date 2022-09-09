package com.ntrungthanh1.subjectTest.service.impl;

import com.ntrungthanh1.subjectTest.exception.ServiceException;
import com.ntrungthanh1.subjectTest.exception.custom.NotFoundException;
import com.ntrungthanh1.subjectTest.model.entity.Test;
import com.ntrungthanh1.subjectTest.model.entity.TestingResult;
import com.ntrungthanh1.subjectTest.model.entity.User;
import com.ntrungthanh1.subjectTest.repository.*;
import com.ntrungthanh1.subjectTest.service.TestResultService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.Random;

@Service
public class TestResultServiceImpl implements TestResultService {
    @Autowired
    TestingResultRepository testingResultRepository;

    @Autowired
    TestingDetailRepository testingDetailRepository;

    @Autowired
    QuestionRepository questionRepository;

    @Autowired
    TestRepository testRepository;
    @Autowired
    UserRepository userRepository;

    @Override
    @Transactional
    public TestingResult saveTestingResult(Long testId  , Long userId ){
        User user = Optional.ofNullable(userRepository.getUserById(userId))
                .orElseThrow(() -> new ServiceException("Not found User by Id"));
        Test test = Optional.ofNullable(testRepository.findTestById(testId)).
                orElseThrow(() -> new ServiceException("Not found Test By Id"));
        //get number test of user in db
        List<TestingResult> list = Optional.ofNullable(testingResultRepository.findByTestIdAndUserId(testId,userId))
                .orElseThrow(() -> new ServiceException("Not found"));

        TestingResult result;
        if(checkEmptyTest(test.getNumberTest() , list)){
            int duration = Optional.ofNullable(test.getDoingDuration())
                    .orElseThrow(() -> new NotFoundException("Not found duration time of test"));
            Long time = new Long(duration * 60);
            String code = getRandomTestCode(testId);

            TestingResult testingResult = new TestingResult();
            testingResult.setTimer(time);
            testingResult.setUser(user);
            testingResult.setTest(test);
            testingResult.setGrade(0);
            testingResult.setStatus(1);
            testingResult.setTestCode(code);
            testingResult.setStartTime(new Date());
            result =  Optional.ofNullable(testingResultRepository.save(testingResult))
                    .orElseThrow(() -> new ServiceException("Not create testing result") );
            return result;
        }
        else {
            result = list.get(0);
            if(result.getFinishTime() == null){
                return result;
            }
        }
        throw  new ServiceException("You have done too many times!");
    }


    public boolean checkEmptyTest(int number , List<TestingResult> list){
        if(list.isEmpty())
        {
            return true;
        }
        return false;
    }

    public String getRandomTestCode(Long testId){

        List<String> listCode = Optional.ofNullable(testingDetailRepository.findTestingDetailById(testId))
                .orElseThrow(() -> new NotFoundException("Not found test"));

        Random rand = new Random();
        String randomElement = listCode.get(rand.nextInt(listCode.size()));
        return  randomElement;
    }

    @Override
    @Cacheable
    public List<TestingResult> getTestingResultByTestIdAndUserId(Long testId , Long userId){
        return  testingResultRepository.findByTestIdAndUserId(testId , userId);
    }

    @Override
    public Long getTime(Long testingResult) {
        TestingResult obj = Optional.ofNullable(testingResultRepository.getTestResultById(testingResult))
                .orElseThrow(() -> new NotFoundException("Not found Testing Result By Id"));
        return obj.getTimer();
    }
}
