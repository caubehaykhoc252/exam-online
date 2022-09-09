package com.ntrungthanh1.subjectTest.service.impl;

import com.ntrungthanh1.subjectTest.exception.ServiceException;
import com.ntrungthanh1.subjectTest.exception.custom.NotFoundException;
import com.ntrungthanh1.subjectTest.model.entity.*;
import com.ntrungthanh1.subjectTest.model.projection.AnswerProjection;
import com.ntrungthanh1.subjectTest.model.request.AnswerSheetRequest;
import com.ntrungthanh1.subjectTest.repository.*;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.context.MessageSource;
import org.springframework.data.projection.ProjectionFactory;
import org.springframework.data.projection.SpelAwareProxyProjectionFactory;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

import static org.assertj.core.internal.bytebuddy.matcher.ElementMatchers.any;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class AnswerSheetServiceImplTest {
    @InjectMocks
    private AnswerSheetServiceImpl answerSheetService;

    @Mock
    private AnswerSheetRepository answerSheetRepository;

    @Mock
    private QuestionRepository questionRepository;

    @Mock
    private OptionRepository optionRepository;

    @Mock
    private TestingResultRepository testingResultRepository;

    @Mock
    private MessageSource message;

    private AnswerSheetRequest answerSheetRequest;

    private TestingResult testingResult;

    private List<Option> listOptions;
    private Option option,option2;

    private Question question;

    private AnswerSheet answerSheet , answerSheet1;

    private com.ntrungthanh1.subjectTest.model.entity.Test test;
    private List<AnswerSheet> result;

    private QuestionType singleChoiceType , multiChoiceType;

    ProjectionFactory factory;

    List<AnswerProjection> listProjection;


    // Before each test we get a fresh instance for QuestionService
    @Before
    public void setUp() {
        factory = new SpelAwareProxyProjectionFactory();

        Map<String, Object> map1 = new HashMap<>();
        map1.put("id", 1l);
        map1.put("question", "OOP?");

        listProjection = Arrays.asList(factory.createProjection(AnswerProjection.class , map1));


        List<Long> listChoiceOption = new ArrayList<>();
        listChoiceOption.add(1l);


        answerSheetRequest = new AnswerSheetRequest();
        answerSheetRequest.setIdQuestion(1l);
        answerSheetRequest.setIdOption(listChoiceOption);
        answerSheetRequest.setIdTestResult(1l);

        test = new com.ntrungthanh1.subjectTest.model.entity.Test();
        test.setId(1l);
        test.setDoingDuration(60);

        testingResult = new TestingResult();
        testingResult.setId(1L);
        testingResult.setTest(test);

        option = new Option();
        option.setId(1L);
        option.setCorrect(true);
        option.setOptionContent("avc");

        option2 = new Option();
        option2.setId(2L);
        option2.setCorrect(false);
        option2.setOptionContent("vcxx");

        listOptions = new ArrayList<>();
        listOptions.add(option);

        singleChoiceType = new QuestionType();
        singleChoiceType.setId(1L);
        singleChoiceType.setTypeName("SINGLE_CHOICE");

        multiChoiceType = new QuestionType();
        multiChoiceType.setId(2L);
        multiChoiceType.setTypeName("MULTI_CHOICE");

        question = new Question();
        question.setMark(0.4);
        question.setId(1L);
        question.setOptions(Arrays.asList(option, option2));
        question.setQuestionType(singleChoiceType);
        option.setQuestion(question);
        option2.setQuestion(question);

        answerSheet = new AnswerSheet();
        answerSheet.setId(1L);
        answerSheet.setTestingResult(testingResult);
        answerSheet.setGrade(0.4);
        answerSheet.setChosenTime(new Date());
        answerSheet.setOption(option);
        answerSheet.setChosenAnswer(option.getOptionContent());

        answerSheet1 = new AnswerSheet();
        answerSheet1.setId(1L);
        answerSheet1.setTestingResult(testingResult);
        answerSheet1.setGrade(0.4);
        answerSheet1.setChosenTime(new Date());
        answerSheet1.setOption(option2);
        answerSheet1.setChosenAnswer(option2.getOptionContent());

        result = new ArrayList<>();
        result.add(answerSheet);
        result.add(answerSheet1);

    }

    @Test(expected = NotFoundException.class)
    public void testCreateAnswer_WithQuestionNull_ReturnFail() {
        when(testingResultRepository.getTestResultById(answerSheetRequest.getIdTestResult())).thenReturn(testingResult);
        when(questionRepository.getQuestionById(answerSheetRequest.getIdQuestion())).thenReturn(null);

        Assertions.assertEquals(answerSheetRequest, answerSheetService.createAnswerSheet(answerSheetRequest));
    }

    @Test(expected = NotFoundException.class)
    public void testCreateAnswer_WithOptionNull_ReturnFail() {
        when(testingResultRepository.getTestResultById(answerSheetRequest.getIdTestResult())).thenReturn(testingResult);
        when(questionRepository.getQuestionById(answerSheetRequest.getIdQuestion())).thenReturn(new Question());
        when(optionRepository.findAllById(answerSheetRequest.getIdOption())).thenReturn(null);

        Assertions.assertEquals(answerSheetRequest, answerSheetService.createAnswerSheet(answerSheetRequest));
    }

    @Test(expected = NotFoundException.class)
    public void testCreateAnswer_WithTestResultNull_ReturnFail() {
        when(testingResultRepository.getTestResultById(answerSheetRequest.getIdTestResult())).thenReturn(null);

        Assertions.assertEquals(answerSheetRequest, answerSheetService.createAnswerSheet(answerSheetRequest));
    }

    @Test(expected = ServiceException.class)
    public void testCreateAnswer_WithTimedSave_Fail() {
        SimpleDateFormat DateFor = new SimpleDateFormat("dd/MM/yyyy");
        Date failTime = new Date();
        try{
            failTime = DateFor.parse("18/08/2022");
        }catch (ParseException e) {e.printStackTrace();}
        testingResult.setStartTime(failTime);

        when(questionRepository.getQuestionById(answerSheetRequest.getIdQuestion())).thenReturn(question);
        when(testingResultRepository.getTestResultById(answerSheetRequest.getIdTestResult())).thenReturn(testingResult);

        answerSheetService.createAnswerSheet(answerSheetRequest);
    }

    @Test(expected = ServiceException.class)
    public void testCreateAnswer_WithSingleChoice_Fail() {
        testingResult.setStartTime(new Date());
        question.setQuestionType(singleChoiceType);
        when(questionRepository.getQuestionById(answerSheetRequest.getIdQuestion())).thenReturn(question);
        when(optionRepository.findAllById(answerSheetRequest.getIdOption())).thenReturn(listOptions);
        when(testingResultRepository.getTestResultById(answerSheetRequest.getIdTestResult())).thenReturn(testingResult);

        answerSheetService.createAnswerSheet(answerSheetRequest);
    }

    @Test(expected = ServiceException.class)
    public void testCreateAnswerSheet_WithMultiChoice() {
        listOptions.add(option2);
        testingResult.setStartTime(new Date());
        question.setQuestionType(multiChoiceType);
        when(optionRepository.findListByQuestion(question)).thenReturn(listOptions);
        when(questionRepository.getQuestionById(answerSheetRequest.getIdQuestion())).thenReturn(question);
        when(optionRepository.findAllById(answerSheetRequest.getIdOption())).thenReturn(listOptions);
                testingResult.setAnswerSheet(Arrays.asList(answerSheet));
        when(testingResultRepository.getTestResultById(answerSheetRequest.getIdTestResult())).thenReturn(testingResult);
        when(answerSheetRepository.save(answerSheet)).thenReturn(answerSheet);
        when(answerSheetRepository
                .findByTestingResultAndQuestion(answerSheetRequest.getIdTestResult() , answerSheetRequest.getIdQuestion()))
                .thenReturn(result);

        answerSheetService.createAnswerSheet(answerSheetRequest);
    }

    @Test(expected = ServiceException.class)
    public void testCreateAnswerSheet_WithSingleChoice_Success() {
        testingResult.setStartTime(new Date());
        question.setQuestionType(singleChoiceType);
        answerSheet.setQuestion(question);

        when(questionRepository.getQuestionById(answerSheetRequest.getIdQuestion())).thenReturn(question);
        when(optionRepository.findAllById(answerSheetRequest.getIdOption())).thenReturn(listOptions);
        testingResult.setAnswerSheet(Arrays.asList(answerSheet));
        when(testingResultRepository.getTestResultById(answerSheetRequest.getIdTestResult())).thenReturn(testingResult);
        when(answerSheetRepository.save(answerSheet)).thenReturn(answerSheet);

        answerSheetService.createAnswerSheet(answerSheetRequest);
    }

    @Test
    public void testGetListAnswer_Success(){
        when(testingResultRepository.existsById(1l)).thenReturn(true);
        when(answerSheetRepository.findByTestingResult(1l)).thenReturn(listProjection);
        Assertions.assertEquals(1 , answerSheetService.getListAnswer(1l).size());
    }

    @Test(expected = NotFoundException.class)
    public void testGetListAnswer_Fail1(){
        when(testingResultRepository.existsById(1l)).thenReturn(false);
        answerSheetService.getListAnswer(1l);
    }

    @Test(expected = NotFoundException.class)
    public void testGetListAnswer_Fail2(){
        when(testingResultRepository.existsById(1l)).thenReturn(true);
        when(answerSheetRepository.findByTestingResult(1l)).thenReturn(Arrays.asList());
        answerSheetService.getListAnswer(1l);
    }

}
