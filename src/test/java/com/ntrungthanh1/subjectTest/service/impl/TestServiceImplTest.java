package com.ntrungthanh1.subjectTest.service.impl;

import com.ntrungthanh1.subjectTest.exception.custom.NotFoundException;
import com.ntrungthanh1.subjectTest.model.entity.User;
import com.ntrungthanh1.subjectTest.model.entity.constant.ActiveStatus;
import com.ntrungthanh1.subjectTest.model.entity.constant.DeleteStatus;
import com.ntrungthanh1.subjectTest.repository.GroupRepository;
import com.ntrungthanh1.subjectTest.repository.TestRepository;
import com.ntrungthanh1.subjectTest.repository.UserRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.context.MessageSource;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class TestServiceImplTest {
    @InjectMocks
    private TestServiceImpl testService;

    @Mock
    private TestRepository testRepository;

    @Mock
    private GroupRepository groupRepository;

    @Mock
    private UserRepository userRepository;

    @Mock
    private MessageSource message;

    User userEntity;

    List<com.ntrungthanh1.subjectTest.model.entity.Test> list;

    List<Long> listId;

    @Before
    public void setUp() {

        userEntity = new User();
        userEntity.setId(1L);
        userEntity.setUsername("abc");
        userEntity.setPassword("password");
        userEntity.setFullName("Meta Blog");
        userEntity.setEmail("abc@gmail.com");
        userEntity.setStatus(ActiveStatus.ACTIVATION);
        userEntity.setIsDeleted(DeleteStatus.NOT_DELETED);
        userEntity.setAddress("abc");

        listId = Arrays.asList(1l);
        list = Arrays.asList(new com.ntrungthanh1.subjectTest.model.entity.Test());

    }

    @Test
    public void testGetGroupByUser() {
        when(userRepository.existsById(1l)).thenReturn(true);
        when(groupRepository.existsById(1l)).thenReturn(true);
        when(testRepository.findListBySubject(1l,1l)).thenReturn(list);
        Assertions.assertEquals(1, testService.getListTest(1l , 1l).size());
    }


    @Test(expected = NotFoundException.class)
    public void testGetGroupByUser_WithUserNull_Fail() {
        when(userRepository.existsById(1l)).thenReturn(false);
        testService.getListTest(1l , 1l);
    }

    @Test(expected = NotFoundException.class)
    public void testGetGroupByUser_WithGroupNull_Fail() {
        when(userRepository.existsById(1l)).thenReturn(true);
        when(groupRepository.existsById(1l)).thenReturn(false);
        testService.getListTest(1l , 1l);
    }

}
