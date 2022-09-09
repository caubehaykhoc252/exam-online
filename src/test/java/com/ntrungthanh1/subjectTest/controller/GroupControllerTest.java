package com.ntrungthanh1.subjectTest.controller;

import com.ntrungthanh1.subjectTest.model.entity.Group;
import com.ntrungthanh1.subjectTest.model.response.AbstractResponse;
import com.ntrungthanh1.subjectTest.model.response.success.BaseResultResponse;
import com.ntrungthanh1.subjectTest.service.GroupService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(MockitoJUnitRunner.class)
public class GroupControllerTest {
    private MockMvc mockMvc;

    String getListGroup = "/api/groups/list";
    @InjectMocks
    private GroupController groupController;

    @Mock
    private GroupService groupService;

    @Mock
    private MessageSource message;

    private List<Group> list;

    AbstractResponse response;

    @Before
    public void setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(groupController)
                .addPlaceholderValue("api.url.group.getListGroup", getListGroup)
                .build();

        Group group = new Group();
        group.setId(1l);
        group.setName("abc");

        list = Arrays.asList(group);

    }

    @Test
    public void getListGroupByUser()  throws Exception{
        Mockito.lenient()
                .when(groupService.getListGroupByUser(1l))
                .thenReturn(list);
        String url = getListGroup;
        this.mockMvc.perform(get(url + "?studentId=1").header("Authorization", "Bearer cdfdfe9fegf3423fksgfkds"))
                .andExpect(status().isOk());
    }

}