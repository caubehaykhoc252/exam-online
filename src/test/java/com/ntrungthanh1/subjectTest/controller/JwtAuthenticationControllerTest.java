package com.ntrungthanh1.subjectTest.controller;

import com.ntrungthanh1.subjectTest.dto.OptionDTO;
import com.ntrungthanh1.subjectTest.dto.QuestionDTO;
import com.ntrungthanh1.subjectTest.model.projection.OptionProjection;
import com.ntrungthanh1.subjectTest.model.projection.QuestionProjection;
import com.ntrungthanh1.subjectTest.service.UserService;
import com.ntrungthanh1.subjectTest.service.impl.QuestionServiceImpl;
import com.ntrungthanh1.subjectTest.service.impl.UserServiceImpl;
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
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.mockito.ArgumentMatchers.any;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@RunWith(MockitoJUnitRunner.class)
public class JwtAuthenticationControllerTest {

    private MockMvc mockMvc;

    @Mock
    private UserServiceImpl userService;

    @Mock
    private AuthenticationManager authenticationManager;



    @InjectMocks
    private JwtAuthenticationController authenticationController;

    private String login = "/api/questions/list";

    private ProjectionFactory factory;

    private List<QuestionProjection> projectionList;

    private QuestionProjection projection1, projection2;

    private QuestionDTO questionDTO;

    private OptionDTO optionDTO1, optionDTO2;

    private List<OptionDTO> listOptionDTO;

    private Long userId;

    private List<OptionProjection> optionProjectionList1, optionProjectionList2;

    @Before
    public void setUp() {
        factory = new SpelAwareProxyProjectionFactory();
    }


}