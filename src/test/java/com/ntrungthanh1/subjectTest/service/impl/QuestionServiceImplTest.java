package com.ntrungthanh1.subjectTest.service.impl;

import com.ntrungthanh1.subjectTest.dto.OptionDTO;
import com.ntrungthanh1.subjectTest.dto.QuestionDTO;
import com.ntrungthanh1.subjectTest.exception.ServiceException;
import com.ntrungthanh1.subjectTest.exception.custom.NotFoundException;
import com.ntrungthanh1.subjectTest.model.entity.*;
import com.ntrungthanh1.subjectTest.model.projection.AnswerProjection;
import com.ntrungthanh1.subjectTest.model.projection.QuestionProjection;
import com.ntrungthanh1.subjectTest.repository.*;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.mockito.stubbing.Answer;
import org.springframework.context.MessageSource;
import org.springframework.data.projection.ProjectionFactory;
import org.springframework.data.projection.SpelAwareProxyProjectionFactory;

import javax.validation.constraints.Null;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class QuestionServiceImplTest {
    @InjectMocks
    private QuestionServiceImpl questionService;

    @Mock
    private TestRepository testRepository;

    @Mock
    private AnswerSheetRepository answerSheetRepository;
    @Mock
    private TestingResultRepository testingResultRepository;
    @Mock
    private TestingDetailRepository testingDetailRepository;

    @Mock
    private QuestionRepository questionRepository;

    @Mock
    private OptionRepository optionRepository;

    @Mock
    private QuestionTypeRepository questionTypeRepository;

    @Mock
    private SubjectRepository subjectRepository;

    @Mock
    private UserRepository userRepository;

    @Mock
    private MessageSource message;

    ProjectionFactory factory;

    QuestionProjection question1, question2;

    List<QuestionProjection> list;

    List<AnswerProjection> answerList;
    AnswerProjection answer;

    List<Question> listQuestions;

    List<Option> listOptions;

    List<String> listCode;
    List<OptionDTO> listOptionDTO;

    Subject subject;

    QuestionType questionType;

    Question question;

    QuestionDTO questionDTO;

    OptionDTO optionDTO1, optionDTO2;

    List<TestingResult> listTestingResult;

    TestingResult testingResult;

    com.ntrungthanh1.subjectTest.model.entity.Test test;

    // Before each test we get a fresh instance for QuestionService
    @Before
    public void setUp() {
        factory = new SpelAwareProxyProjectionFactory();

        Map<String , Object> mapAnswer = new HashMap<>();
        mapAnswer.put("id",1l);
        mapAnswer.put("optionId",1l);
        answer = factory.createProjection(AnswerProjection.class , mapAnswer);
        answerList = new ArrayList<>();

        Map<String, Object> map1 = new HashMap<>();
        map1.put("id", 1l);
        map1.put("question", "OOP?");

        Map<String, Object> map2 = new HashMap<>();
        map1.put("id", 2l);
        map1.put("question", "How much OOP?");

        question1 = factory.createProjection(QuestionProjection.class, map1);
        question2 = factory.createProjection(QuestionProjection.class , map2);

        question = new Question();
        question.setQuestion("OOP");

        subject = new Subject();
        subject.setId(1l);

        questionDTO = new QuestionDTO();

        optionDTO1 = new OptionDTO();
        optionDTO1.setOptionContent("2");
        optionDTO1.setCorrect(true);

        listOptionDTO = new ArrayList<>();
        listOptionDTO.add(optionDTO1);

        listCode = Arrays.asList("Code1");

        testingResult = new TestingResult();
        testingResult.setId(1l);
        listTestingResult = new ArrayList<>();

        test = new com.ntrungthanh1.subjectTest.model.entity.Test();
    }

    /**
     * For getAllUser()
     * Scenario: Get all user
     */

    @Test
    public void testGetListQuestion(){
        when(questionRepository.findAllQuestion()).thenReturn(Stream.of(question1, question2).collect(Collectors.toList()));
        Assertions.assertEquals(2, questionService.getListQuestion().size());
    }

    @Test
    public void testGetQuestionByQuestion(){
        when(questionRepository.findByQuestion("OOP?")).thenReturn(question);
        Assertions.assertEquals(questionService.getQuestionByQuestion("OOP?") , question);
    }

    @Test
    public void testGetQuestionById(){
        when(questionRepository.findQuestionById(1l)).thenReturn(question1);
        Assertions.assertEquals(question1 , questionService.getQuestionById(1l));
    }

    @Test(expected = NullPointerException.class)
    public void testCreateQuestion_WithQuestionNull_ReturnFail(){
        Assertions.assertEquals(false , questionService.createQuestion(questionDTO));
    }

    @Test
    public void testCreateQuestion_WithQuestionTypeNull_ReturnFail(){
        questionDTO.setQuestion("How many OOP?");
        questionDTO.setQuestionTypeId(1l);
        questionDTO.setOptions(listOptionDTO);
        Assertions.assertEquals(false , questionService.createQuestion(questionDTO));
    }

    @Test
    public void testCreateQuestion_WithSubjectNull_ReturnFail(){
        questionDTO.setQuestion("How many OOP?");
        questionDTO.setQuestionTypeId(1l);
        questionDTO.setOptions(listOptionDTO);
        questionDTO.setSubjectId(1l);
        when(subjectRepository.findSubjectById(1l)).thenReturn(new Subject());
        Assertions.assertEquals(false , questionService.createQuestion(questionDTO));
    }

    @Test()
    public void testCreateQuestion_WithMarkLess0_ReturnFail(){
        questionDTO.setQuestion("How many OOP?");
        questionDTO.setSubjectId(1l);
        questionDTO.setQuestionTypeId(1l);
        questionDTO.setOptions(listOptionDTO);
        questionDTO.setMark(-0.4);
        Assertions.assertEquals(false , questionService.createQuestion(questionDTO));
    }

    @Test
    public void testCreateQuestion_WithQuestionTypeIs1_ReturnFail(){
        optionDTO2 = new OptionDTO();
        optionDTO2.setOptionContent("3");
        optionDTO2.setCorrect(false);

        questionDTO.setQuestion("How many OOP?");
        questionDTO.setSubjectId(1l);
        questionDTO.setMark(0.4);
        questionDTO.setQuestionTypeId(1l);
        questionDTO.setOptions(listOptionDTO);
        Assertions.assertEquals(false , questionService.createQuestion(questionDTO));
    }

    @Test
    public void testCreateQuestion_WithQuestionTypeIs2_ReturnFail(){
        optionDTO2 = new OptionDTO();
        optionDTO2.setOptionContent("3");
        optionDTO2.setCorrect(true);

        listOptionDTO.add(optionDTO2);

        questionDTO.setQuestion("How many OOP?");
        questionDTO.setSubjectId(1l);
        questionDTO.setMark(0.4);
        questionDTO.setQuestionTypeId(2l);
        questionDTO.setOptions(listOptionDTO);
        Assertions.assertEquals(false , questionService.createQuestion(questionDTO));
    }

    @Test
    public void testCreateQuestion_WithDuplicateOptionContent_ReturnFail(){
        optionDTO2 = new OptionDTO();
        optionDTO2.setOptionContent("2");
        optionDTO2.setCorrect(true);

        listOptionDTO.add(optionDTO2);

        questionDTO.setQuestion("How many OOP?");
        questionDTO.setSubjectId(1l);
        questionDTO.setMark(0.4);
        questionDTO.setQuestionTypeId(2l);
        questionDTO.setOptions(listOptionDTO);
        Assertions.assertEquals(false , questionService.createQuestion(questionDTO));
    }

    @Test
    public void testCreateQuestion_WithSubjectNotExists_ReturnFail(){
        optionDTO2 = new OptionDTO();
        optionDTO2.setOptionContent("3");
        optionDTO2.setCorrect(true);

        listOptionDTO.add(optionDTO2);
        questionDTO.setQuestion("How many OOP?");
        questionDTO.setSubjectId(1l);
        questionDTO.setMark(0.4);
        questionDTO.setQuestionTypeId(2l);
        questionDTO.setOptions(listOptionDTO);

        when(subjectRepository.findSubjectById(questionDTO.getSubjectId())).thenReturn(null);
        Assertions.assertEquals(false , questionService.createQuestion(questionDTO));
    }

    @Test
    public void testCreateQuestion_WithQuestionTypeNotExists_ReturnFail(){
        optionDTO2 = new OptionDTO();
        optionDTO2.setOptionContent("3");
        optionDTO2.setCorrect(true);

        listOptionDTO.add(optionDTO2);

        questionDTO.setQuestion("How many OOP?");
        questionDTO.setSubjectId(1l);
        questionDTO.setMark(0.4);
        questionDTO.setQuestionTypeId(2l);
        questionDTO.setOptions(listOptionDTO);

        when(questionTypeRepository.findQuestionTypeById(questionDTO.getQuestionTypeId())).thenReturn(null);
        Assertions.assertEquals(false , questionService.createQuestion(questionDTO));
    }

    @Test
    public void testCreateQuestion_WithQuestionNameExists_ReturnFail(){
        optionDTO2 = new OptionDTO();
        optionDTO2.setOptionContent("3");
        optionDTO2.setCorrect(true);

        listOptionDTO.add(optionDTO2);

        questionDTO.setQuestion("How many OOP?");
        questionDTO.setSubjectId(1l);
        questionDTO.setMark(0.4);
        questionDTO.setQuestionTypeId(2l);
        questionDTO.setOptions(listOptionDTO);

        when(questionRepository.findByQuestion(questionDTO.getQuestion())).thenReturn(new Question());
        Assertions.assertEquals(false , questionService.createQuestion(questionDTO));
    }

    @Test
    public void testCreateQuestion_ReturnSuccess(){
        optionDTO2 = new OptionDTO();
        optionDTO2.setOptionContent("3");
        optionDTO2.setCorrect(true);

        listOptionDTO.add(optionDTO2);

        questionDTO.setQuestion("How many OOP?");
        questionDTO.setSubjectId(1l);
        questionDTO.setMark(0.4);
        questionDTO.setQuestionTypeId(2l);
        questionDTO.setOptions(listOptionDTO);

        when(subjectRepository.findSubjectById(questionDTO.getSubjectId())).thenReturn(new Subject());
        when(questionTypeRepository.findQuestionTypeById(questionDTO.getQuestionTypeId())).thenReturn(new QuestionType());
        when(questionRepository.findByQuestion(questionDTO.getQuestion())).thenReturn(null);

        Assertions.assertEquals(true , questionService.createQuestion(questionDTO));
    }


    @Test
    public void testGetListQuestionByTest_Success(){
        list = Arrays.asList(question1,question2);
        when(testRepository.existsById(1l)).thenReturn(true);
        when(testingDetailRepository.findTestingDetailById(1l)).thenReturn(listCode);
        when(questionRepository.findListByTestCode(listCode.get(0),1l)).thenReturn(list);
        Assertions.assertEquals(2 , questionService.getListQuestionByTest(1l , "Code1").size());

    }

    @Test(expected = NotFoundException.class)
    public void testGetListQuestionByTest_WithTestNull_Fail(){
        when(testRepository.existsById(1l)).thenReturn(false);
        questionService.getListQuestionByTest(1l , "abc");
    }

    @Test(expected = ServiceException.class)
    public void testGetListQuestionByTest_WithListEmpty_Success(){
        list = Arrays.asList();
        when(testRepository.existsById(1l)).thenReturn(true);
        when(testingDetailRepository.findTestingDetailById(1l)).thenReturn(listCode);
        when(questionRepository.findListByTestCode(listCode.get(0) , 1l)).thenReturn(list);
        Assertions.assertEquals(0 , questionService.getListQuestionByTest(1l, "Code1").size());
    }

    @Test
    public void testGetListQuestionByTest(){
        list = Arrays.asList(question1);
        answerList.add(answer);
        test.setDoingDuration(60);
        when(userRepository.existsById(1l)).thenReturn(true);
        when(testRepository.findTestById(1l)).thenReturn(test);
        when(testingResultRepository.getTestResultById(1l )).thenReturn(testingResult);
        when(answerSheetRepository.findByTestingResult(1l)).thenReturn(answerList);
        when(testRepository.existsById(1l)).thenReturn(true);
        when(questionRepository.findListByTestCode("Code1" , 1l)).thenReturn(list);
        Assertions.assertEquals(list , questionService.getListQuestionByTest(1l , 1l ,1l).getListQuestion());

    }
}
