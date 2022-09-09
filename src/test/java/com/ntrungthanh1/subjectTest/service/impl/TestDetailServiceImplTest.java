package com.ntrungthanh1.subjectTest.service.impl;

import com.ntrungthanh1.subjectTest.model.entity.*;
import com.ntrungthanh1.subjectTest.repository.*;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.context.MessageSource;

import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class TestDetailServiceImplTest {
    @InjectMocks
    private TestDetailServiceImpl testDetailService;

    @Mock
    private QuestionRepository questionRepository;

    @Mock
    private TestRepository testRepository;

    @Mock
    private TestingDetailRepository testingDetailRepository;

    @Mock
    private MessageSource message;

    Question question1 , question2;

    com.ntrungthanh1.subjectTest.model.entity.Test test;

    Subject subject;

    // Before each test we get a fresh instance for TestDetailService
    @Before
    public void setUp() {
        question1 = new Question();
        question1.setId(1l);
        question1.setQuestion("OOP?");

        question2 = new Question();
        question2.setId(2l);
        question2.setQuestion("How many is OOP?");

        subject = new Subject();
        subject.setId(1l);

        test = new com.ntrungthanh1.subjectTest.model.entity.Test();
        test.setSubject(subject);

    }

    /**
     * For getAllUser()
     * Scenario: Get all user
     */
    @Test(expected = NullPointerException.class)
    public void testCreateTestDetail_WithTestIdAndNumberCodeNull_ReturnFail() throws NullPointerException{
        testDetailService.createTestDetail(null , null);
    }

    @Test
    public void testCreateTestDetail_WithTestNotExists_ReturnFail(){
        when(testRepository.findTestById(1l)).thenReturn(null);
        Assertions.assertEquals(false,testDetailService.createTestDetail(1l, 2l));
    }

    @Test
    public void testCreateTestDetail_WithListQuestionIsEntry_ReturnFail(){
        when(testRepository.findTestById(1l)).thenReturn(test);
        when(questionRepository.findListBySubjectId(1L)).thenReturn(Stream.of(question1,question2).collect(Collectors.toList()));
        Assertions.assertEquals(false,testDetailService.createTestDetail(1l, 1l));
    }

    @Test
    public  void testCreateTestDetail_ReturnTrue(){
        when(testRepository.findTestById(1l)).thenReturn(test);
        when(questionRepository.findListBySubjectId(1L)).thenReturn(Stream.of(question1,question2).collect(Collectors.toList()));

        Assertions.assertEquals(true , testDetailService.createTestDetail(1l , 2l));
    }
}
