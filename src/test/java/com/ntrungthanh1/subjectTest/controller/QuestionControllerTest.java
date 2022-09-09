package com.ntrungthanh1.subjectTest.controller;

import com.google.gson.Gson;
import com.ntrungthanh1.subjectTest.dto.OptionDTO;
import com.ntrungthanh1.subjectTest.dto.QuestionDTO;
import com.ntrungthanh1.subjectTest.model.projection.OptionProjection;
import com.ntrungthanh1.subjectTest.model.projection.QuestionProjection;
import com.ntrungthanh1.subjectTest.model.response.custom.QuestionResponse;
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

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.mockito.ArgumentMatchers.any;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;


@RunWith(MockitoJUnitRunner.class)
public class QuestionControllerTest {

    private MockMvc mockMvc;

    @Mock
    private QuestionServiceImpl questionService;

    @InjectMocks
    private QuestionController questionController;

    private String getListQuestion = "/api/questions/list";
    private String getQuestionById = "/api/questions/getQuestionById";
    private String createQuestion = "/api/questions/createQuestion";
    private String getListQuestionByTest = "/api/questions/getListQuestionByTest";

    private ProjectionFactory factory;

    private List<QuestionProjection> projectionList;

    private QuestionProjection projection1, projection2;

    private List<OptionProjection> optionProjectionList1, optionProjectionList2;

    private QuestionDTO questionDTO;

    private OptionDTO optionDTO;

    private List<OptionDTO> listOptionDTO;

    private List<QuestionResponse> listResponse;

    private Gson gson;


    @Before
    public void setUp() {
        factory = new SpelAwareProxyProjectionFactory();

        Map<String, Object> map1 = new HashMap<>();
        map1.put("id", 100);
        map1.put("question", "abc@gmail.com");
        map1.put("isShuffled", "true");

        Map<String, Object> optionsMap1 = new HashMap<>();
        optionsMap1.put("id", 100);
        optionsMap1.put("optionContent", "abc");
        OptionProjection optionProjection1 = factory.createProjection(OptionProjection.class, optionsMap1);

        Map<String, Object> optionsMap2 = new HashMap<>();
        optionsMap1.put("id", 101);
        optionsMap1.put("optionContent", "xyz");
        OptionProjection optionProjection2 = factory.createProjection(OptionProjection.class, optionsMap2);

        optionProjectionList1 = Arrays.asList(optionProjection1, optionProjection2);

        map1.put("options", optionProjectionList1);

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

        optionProjectionList2 = Arrays.asList(optionProjection3, optionProjection4);

        map1.put("options", optionProjectionList2);

        projection1 = factory.createProjection(QuestionProjection.class, map1);
        projection2 = factory.createProjection(QuestionProjection.class, map2);

        projectionList = Arrays.asList(projection1, projection2);

        listResponse = Arrays.asList(new QuestionResponse());

        mockMvc = MockMvcBuilders.standaloneSetup(questionController)
                .addPlaceholderValue("api.url.question.getListQuestion", getListQuestion)
                .addPlaceholderValue("api.url.question.getQuestionById", getQuestionById)
                .addPlaceholderValue("api.url.question.createQuestion", createQuestion)
                .addPlaceholderValue("api.url.question.getListQuestionByTest", getListQuestionByTest)
                .build();

        optionDTO = new OptionDTO();
        optionDTO.setCorrect(true);
        optionDTO.setOptionContent("abc01");

        listOptionDTO = Arrays.asList(optionDTO);

        questionDTO = new QuestionDTO();
        questionDTO.setQuestion("abc");
        questionDTO.setQuestionTypeId(1l);
        questionDTO.setSubjectId(1l);
        questionDTO.setMark(0.4);
        questionDTO.setShuffle(true);
        questionDTO.setOptions(listOptionDTO);

        gson = new Gson();

    }

    @Test
    public void testGetListQuestion() throws Exception {

        Mockito.lenient()
                .when(questionService.getListQuestion())
                .thenReturn(projectionList);
        String url = getListQuestion;
        this.mockMvc.perform(get(url).header("Authorization", "Bearer cdfdfe9fegf3423fksgfkds"))
                .andExpect(status().isInternalServerError());
    }

    @Test
    public void testGetQuestionById() throws Exception {

        Mockito.lenient()
                .when(questionService.getQuestionById(1l))
                .thenReturn(projection1);
        String url = getListQuestion;
        this.mockMvc.perform(get(url  + "?id=1").header("Authorization", "Bearer cdfdfe9fegf3423fksgfkds"))
                .andExpect(status().isOk());
    }

    @Test
    public void testCreateQuestion_ReturnFail() throws Exception {

        Mockito.lenient()
                .when(questionService.createQuestion(questionDTO))
                .thenReturn(false);
        String url = createQuestion;
        String json = gson.toJson(questionDTO);

        this.mockMvc.perform(post(url).header("Authorization", "Bearer cdfdfe9fegf3423fksgfkds")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json))
                .andExpect(status().isInternalServerError());
    }

    @Test
    public void testCreateQuestion_ReturnSuccess() throws Exception {
        Mockito.lenient()
                .when(questionService.createQuestion(any()))
                .thenReturn(true);

        String url = createQuestion;
        Gson gson = new Gson();
        String json = gson.toJson(questionDTO);

        this.mockMvc.perform(post(url).header("Authorization", "Bearer cdfdfe9fegf3423fksgfkds")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json))
                .andExpect(status().isCreated());
    }

//    @Test
//    public void testGetListQuestionByTest() throws Exception{
//        Mockito.lenient()
//                .when(questionService.getListQuestionByTest(1l))
//                .thenReturn(projectionList);
//        String url = getListQuestionByTest;
//
//        this.mockMvc.perform(get(url + "?testId=1").header("Authorization", "Bearer cdfdfe9fegf3423fksgfkds"))
//                .andExpect(status().isInternalServerError());
//    }

}
