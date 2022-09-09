package com.ntrungthanh1.subjectTest.controller;

import com.google.gson.Gson;
import com.ntrungthanh1.subjectTest.dto.OptionDTO;
import com.ntrungthanh1.subjectTest.dto.QuestionDTO;
import com.ntrungthanh1.subjectTest.model.entity.*;
import com.ntrungthanh1.subjectTest.model.projection.OptionProjection;
import com.ntrungthanh1.subjectTest.model.projection.QuestionProjection;
import com.ntrungthanh1.subjectTest.model.request.AnswerSheetRequest;
import com.ntrungthanh1.subjectTest.service.impl.AnswerSheetServiceImpl;
import com.ntrungthanh1.subjectTest.service.impl.QuestionServiceImpl;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.data.projection.ProjectionFactory;
import org.springframework.data.projection.SpelAwareProxyProjectionFactory;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.sql.Time;
import java.util.*;

import static org.mockito.ArgumentMatchers.any;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@RunWith(MockitoJUnitRunner.class)
public class AnswerSheetControllerTest {

    private MockMvc mockMvc;

    @InjectMocks
    private AnswerSheetController  answerSheetController;

    @Mock
    private AnswerSheetServiceImpl answerSheetService;

    private String createAnswerSheet = "/api/answerSheet/createAnswerSheet";

    private ProjectionFactory factory;

    private AnswerSheetRequest answerSheetRequest;

    private TestingResult testingResult;

    private Option option,option2;

    private Question question;

    private AnswerSheet answerSheet;

    private List<AnswerSheet> listAnswerSheets;
    private Gson gson;

    @Before
    public void setUp() {
        factory = new SpelAwareProxyProjectionFactory();

        Map<String, Object> map2 = new HashMap<>();
        map2.put("id", 101);
        map2.put("question", "abc2@gmail.com");
        map2.put("isShuffled", "false");

        Map<String, Object> optionsMap3 = new HashMap<>();
        optionsMap3.put("id", 102);
        optionsMap3.put("optionContent", "fdfa");
        OptionProjection optionProjection3 = factory.createProjection(OptionProjection.class, optionsMap3);

        Map<String, Object> optionsMap4 = new HashMap<>();
        optionsMap4.put("id", 104);
        optionsMap4.put("optionContent", "ewewsd");
        OptionProjection optionProjection4 = factory.createProjection(OptionProjection.class, optionsMap4);

        mockMvc = MockMvcBuilders.standaloneSetup(answerSheetController)
                .addPlaceholderValue("api.url.answerSheet.createAnswerSheet", createAnswerSheet)
                .build();

        List<Long> listOptionId = new ArrayList<>();
        listOptionId.add(1l);
        answerSheetRequest = new AnswerSheetRequest();
        answerSheetRequest.setIdQuestion(1l);
        answerSheetRequest.setIdOption(listOptionId);
        answerSheetRequest.setIdTestResult(1l);

        testingResult = new TestingResult();
        testingResult.setId(1L);
        testingResult.setFinishTime(new Time(44000));

        option = new Option();
        option.setId(1L);
        option.setCorrect(true);
        option.setOptionContent("avc");

        option2 = new Option();
        option2.setId(2L);
        option2.setCorrect(false);
        option2.setOptionContent("vcxx");

        QuestionType type = new QuestionType();
        type.setId(1L);
        type.setTypeName("SINGLE_CHOICE");

        question = new Question();
        question.setMark(0.4);
        question.setId(1L);
        question.setQuestionType(type);
        question.setOptions(Arrays.asList(option, option2));
        option.setQuestion(question);
        option2.setQuestion(question);

        answerSheet = new AnswerSheet();
        answerSheet.setId(1L);
        answerSheet.setTestingResult(testingResult);
        answerSheet.setQuestion(question);
        answerSheet.setGrade(0.4);
        answerSheet.setChosenTime(new Date());
        answerSheet.setOption(option);
        answerSheet.setChosenAnswer(option.getOptionContent());
        gson = new Gson();

    }

//    @Test
//    public void testCreateQuestion_ReturnFail() throws Exception {
//
//        Mockito.lenient()
//                .when(questionService.createQuestion(questionDTO))
//                .thenReturn(false);
//        String url = createQuestion;
//        String json = gson.toJson(questionDTO);
//
//        this.mockMvc.perform(post(url).header("Authorization", "Bearer cdfdfe9fegf3423fksgfkds")
//                        .contentType(MediaType.APPLICATION_JSON)
//                        .content(json))
//                .andExpect(status().isInternalServerError());
//    }

    @Test
    public void testCreateAnswerSheet_ReturnSuccess() throws Exception {
        Mockito.lenient()
                .when(answerSheetService.createAnswerSheet(any()))
                .thenReturn(listAnswerSheets);

        String url = createAnswerSheet;
        Gson gson = new Gson();
        String json = gson.toJson(answerSheetRequest);

        this.mockMvc.perform(post(url).header("Authorization", "Bearer cdfdfe9fegf3423fksgfkds")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json))
                .andExpect(status().isCreated());
    }

}
