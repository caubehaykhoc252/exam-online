package com.ntrungthanh1.subjectTest.controller;

import com.google.gson.Gson;
import com.ntrungthanh1.subjectTest.service.impl.TestDetailServiceImpl;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.data.projection.ProjectionFactory;
import org.springframework.data.projection.SpelAwareProxyProjectionFactory;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@RunWith(MockitoJUnitRunner.class)
public class TestDetailControllerTest {

    private MockMvc mockMvc;

    @Mock
    private TestDetailServiceImpl testDetailService;

    @InjectMocks
    private TestDetailsController testDetailsController;

    private final String createTestingDetail = "/api/testingDetails/createTestingDetail";

    private ProjectionFactory factory;

    private Gson gson;


    @Before
    public void setUp() {
        factory = new SpelAwareProxyProjectionFactory();

        mockMvc = MockMvcBuilders.standaloneSetup(testDetailsController)
                .addPlaceholderValue("api.url.testingDetail.createTestingDetail", createTestingDetail)
                .build();

        gson = new Gson();
    }

    @Test
    public void testCreateQuestion_ReturnSuccess() throws Exception {

        Mockito.lenient()
                .when(testDetailService.createTestDetail(1L, 1L))
                .thenReturn(true);
        String url = createTestingDetail;

        this.mockMvc.perform(post(url + "?testId=1&NumberCode=1").header("Authorization", "Bearer cdfdfe9fegf3423fksgfkds"))
                .andExpect(status().isCreated());
    }

}