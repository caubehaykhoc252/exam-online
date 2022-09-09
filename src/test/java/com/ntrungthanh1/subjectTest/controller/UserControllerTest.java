package com.ntrungthanh1.subjectTest.controller;

import com.ntrungthanh1.subjectTest.model.projection.UserProjection;
import com.ntrungthanh1.subjectTest.service.UserService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.data.projection.ProjectionFactory;
import org.springframework.data.projection.SpelAwareProxyProjectionFactory;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultMatcher;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.lang.reflect.Array;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.assertj.core.internal.bytebuddy.matcher.ElementMatchers.is;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(MockitoJUnitRunner.class)
public class UserControllerTest {
    private MockMvc mockMvc;

    String getListUser = "/api/users/getListUser";
    String getUserById = "/api/users/getUserById";
    @InjectMocks
    private UserController userController;

    @Mock
    private UserService userService;

    @Mock
    private MessageSource message;

    private ProjectionFactory factory;

    private UserProjection user1, user2;

    private List<UserProjection> projectionList ;

    @Before
    public void setUp() {

        factory = new SpelAwareProxyProjectionFactory();

        Map<String, Object> map1 = new HashMap<>();
        map1.put("id", 100);
        map1.put("email", "abc@gmail.com");
        map1.put("username", "Alice");
        map1.put("password", "password1");
        map1.put("fullName","Alice Hell");

        user1 = factory.createProjection(UserProjection.class , map1);

        mockMvc = MockMvcBuilders.standaloneSetup(userController)
                .addPlaceholderValue("api.url.user.getListUser", getListUser)
                .addPlaceholderValue("api.url.user.getUserById", getUserById)
                .build();

        projectionList = Arrays.asList(user1);
    }

    @Test
    public void getListUser()  throws Exception{
        Mockito.lenient()
                .when(userService.getListUser())
                .thenReturn(projectionList);
        String url = getListUser;
        this.mockMvc.perform(get(url).header("Authorization", "Bearer cdfdfe9fegf3423fksgfkds"))
                .andExpect(status().isOk());
    }

    @Test
    public void getUserById() throws Exception{
        Mockito.lenient()
                .when(userService.getUserById(1l))
                .thenReturn(user1);
        String url = getUserById;
        this.mockMvc.perform(post(url  + "?id=1").header("Authorization", "Bearer cdfdfe9fegf3423fksgfkds"))
                .andExpect(status().isOk());
    }
}