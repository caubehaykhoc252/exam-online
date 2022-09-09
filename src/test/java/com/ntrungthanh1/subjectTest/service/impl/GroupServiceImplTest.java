package com.ntrungthanh1.subjectTest.service.impl;

import com.ntrungthanh1.subjectTest.exception.ServiceException;
import com.ntrungthanh1.subjectTest.exception.custom.NotFoundException;
import com.ntrungthanh1.subjectTest.model.entity.Group;
import com.ntrungthanh1.subjectTest.model.entity.Role;
import com.ntrungthanh1.subjectTest.model.entity.User;
import com.ntrungthanh1.subjectTest.model.entity.constant.ActiveStatus;
import com.ntrungthanh1.subjectTest.model.entity.constant.DeleteStatus;
import com.ntrungthanh1.subjectTest.model.entity.constant.ERole;
import com.ntrungthanh1.subjectTest.model.projection.UserInfoSummary;
import com.ntrungthanh1.subjectTest.model.projection.UserProjection;
import com.ntrungthanh1.subjectTest.repository.GroupRepository;
import com.ntrungthanh1.subjectTest.repository.RoleRepository;
import com.ntrungthanh1.subjectTest.repository.UserRepository;
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

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class GroupServiceImplTest {
    @InjectMocks
    private GroupServiceImpl groupService;

    @Mock
    private GroupRepository groupRepository;

    @Mock
    private UserRepository userRepository;

    @Mock
    private MessageSource message;

    User userEntity;

    List<Group> list;

    List<Long> listId;

    // Before each test we get a fresh instance for UserService
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
        list = Arrays.asList(new Group());

    }

    @Test
    public void testGetGroupByUser() {
        when(userRepository.existsById(1l)).thenReturn(true);
        when(groupRepository.getGroupByUserId(1l)).thenReturn(listId);
        when(groupRepository.findByIds(listId)).thenReturn(list);
        Assertions.assertEquals(1, groupService.getListGroupByUser(1l).size());
    }


    @Test(expected = NotFoundException.class)
    public void testGetGroupByUser_Fail() {
        when(userRepository.existsById(1l)).thenReturn(true);
        when(groupRepository.getGroupByUserId(1l)).thenReturn(listId);
        when(groupRepository.findByIds(listId)).thenReturn(new ArrayList<>());
        groupService.getListGroupByUser(1l);
    }

}
