package com.ntrungthanh1.subjectTest.controller;

import com.ntrungthanh1.subjectTest.model.response.AbstractResponse;
import com.ntrungthanh1.subjectTest.service.TestService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.context.MessageSource;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Arrays;
import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(MockitoJUnitRunner.class)
public class TestControllerTest {
    private MockMvc mockMvc;

    String getListTest = "/api/exams/list";
    @InjectMocks
    private TestController testController;

    @Mock
    private TestService testService;

    @Mock
    private MessageSource message;

    private List<com.ntrungthanh1.subjectTest.model.entity.Test> list;

    AbstractResponse response;

    @Before
    public void setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(testController)
                .addPlaceholderValue("api.url.exams.getListTest", getListTest)
                .build();

        com.ntrungthanh1.subjectTest.model.entity.Test test = new com.ntrungthanh1.subjectTest.model.entity.Test();
        test.setId(1l);
        test.setTitle("abc");

        list = Arrays.asList(test);

    }

    @Test
    public void getListGroupByUser()  throws Exception{
        Mockito.lenient()
                .when(testService.getListTest(1l,1l))
                .thenReturn(list);
        String url = getListTest;
        this.mockMvc.perform(get(url + "?groupId=1&studentId=1").header("Authorization", "Bearer cdfdfe9fegf3423fksgfkds"))
                .andExpect(status().isOk());
    }

}