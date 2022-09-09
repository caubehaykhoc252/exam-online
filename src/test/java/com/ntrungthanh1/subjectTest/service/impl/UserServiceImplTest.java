package com.ntrungthanh1.subjectTest.service.impl;

import com.ntrungthanh1.subjectTest.exception.ServiceException;
import com.ntrungthanh1.subjectTest.exception.custom.NotFoundException;
import com.ntrungthanh1.subjectTest.model.entity.Role;
import com.ntrungthanh1.subjectTest.model.entity.User;
import com.ntrungthanh1.subjectTest.model.entity.constant.ActiveStatus;
import com.ntrungthanh1.subjectTest.model.entity.constant.DeleteStatus;
import com.ntrungthanh1.subjectTest.model.entity.constant.ERole;
import com.ntrungthanh1.subjectTest.model.projection.UserInfoSummary;
import com.ntrungthanh1.subjectTest.model.projection.UserProjection;
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
public class UserServiceImplTest {
    @InjectMocks
    private UserServiceImpl userService;

    @Mock
    private UserRepository userRepository;

    @Mock
    private RoleRepository roleRepository;

    @Mock
    private MessageSource message;

    User userEntity;

    Collection<Role> roles;

    Collection<UserInfoSummary> userList;

    List<ERole> eRole;
    Long userId;
    ProjectionFactory factory;
    UserProjection user1, user2;//Since userprojection doesn't has setter, so that we use Map<> such as setUp()

    UserInfoSummary userInfoSummary1 ,userInfoSummary2;

    // Before each test we get a fresh instance for UserService
    @Before
    public void setUp() {
        eRole = new ArrayList<>();

        factory = new SpelAwareProxyProjectionFactory();
        userId = 1L;

        Map<String, Object> map1 = new HashMap<>();
        map1.put("id", 100);
        map1.put("email", "abc@gmail.com");
        map1.put("username", "Alice");
        map1.put("password", "password1");
        map1.put("fullName","Alice Hell");
        map1.put("roles",eRole);

        Map<String, Object> map2 = new HashMap<>();
        map1.put("id", 101);
        map2.put("email", "abc@gmail.com");
        map2.put("username", "Dave");
        map2.put("password", "password1");
        map2.put("fullName","Dave Vans");
        map2.put("roles",eRole);



        user1 = factory.createProjection(UserProjection.class, map1);
        user2 = factory.createProjection(UserProjection.class, map2);
        userInfoSummary1 = factory.createProjection(UserInfoSummary.class , map1);
        userInfoSummary2 = factory.createProjection(UserInfoSummary.class , map2);

        userEntity = new User();
        userEntity.setId(1L);
        userEntity.setUsername("abc");
        userEntity.setPassword("password");
        userEntity.setFullName("Meta Blog");
        userEntity.setEmail("abc@gmail.com");
        userEntity.setStatus(ActiveStatus.ACTIVATION);
        userEntity.setIsDeleted(DeleteStatus.NOT_DELETED);
        userEntity.setAddress("abc");

    }

    /**
     * For getAllUser()
     * Scenario: Get all user
     */
    @Test
    public void testGetAllUserTest() {
        when(userRepository.findAllUser()).thenReturn(Stream.of(user1, user2).collect(Collectors.toList()));
        assertThat(user1.getUsername()).isEqualTo("Alice");
        Assertions.assertEquals(2, userService.getListUser().size());
    }

    @Test
    public void testGetUserById() {
        when(userRepository.findUserById(100L)).thenReturn(user1);
        assertThat(user1.getUsername()).isEqualTo("Alice");
        Assertions.assertEquals(1, Arrays.asList(userService.getUserById(100L)).size());
    }

    @Test
    public void testFindUserInfoSummary_ReturnSuccess(){
        when(userRepository.findByUsernameAndRolesInAndStatusAndIsDeleted("Alice", roles , ActiveStatus.ACTIVATION , DeleteStatus.NOT_DELETED )).thenReturn((Stream.of(userInfoSummary1).collect(Collectors.toList())));
        assertThat(userInfoSummary1.getUsername()).isEqualTo("Alice");
        Assertions.assertEquals(1,Arrays.asList(userService.findUserInfoSummary("Alice", roles , ActiveStatus.ACTIVATION , DeleteStatus.NOT_DELETED)).size());
    }


    @Test(expected = NotFoundException.class)
    public void testFindUserInfoSummary_ReturnFail() throws Exception{
        when(userRepository.findByUsernameAndRolesInAndStatusAndIsDeleted("Alice", roles , ActiveStatus.ACTIVATION , DeleteStatus.NOT_DELETED )).thenReturn((Stream.of(userInfoSummary1,userInfoSummary2).collect(Collectors.toList())));
        userService.findUserInfoSummary("Alice", roles , ActiveStatus.ACTIVATION , DeleteStatus.NOT_DELETED);
    }

    @Test(expected = ServiceException.class)
    public void testGetListRoles_ReturnFail() throws Exception{
        userService.getListRoles(eRole);
    }

    @Test
    public void testGetListRoles_ReturnSuccess(){
        eRole.add(ERole.ROLE_ADMIN);
        when(roleRepository.findRolesByNameIn(eRole)).thenReturn((Stream.of(new Role()).collect(Collectors.toList())));
        Assertions.assertEquals(1,userService.getListRoles(eRole).size());
    }

    @Test
    public void testGetUserInfoWithRole_WithRoleStudent_ReturnSuccess(){
        Role role = new Role();
        role.setName(ERole.ROLE_STUDENT);

        Collection<Role> listRoles = new ArrayList<>();
        listRoles.add(role);

        eRole.add(ERole.ROLE_STUDENT);
        when(roleRepository.findRolesByNameIn(eRole)).thenReturn(listRoles);
        when(userRepository.findByUsernameAndRolesInAndStatusAndIsDeleted("Alice", listRoles , ActiveStatus.ACTIVATION , DeleteStatus.NOT_DELETED )).thenReturn((Stream.of(userInfoSummary1).collect(Collectors.toList())));

        Assertions.assertEquals(1,Arrays.asList(userService.getUserInfoWithRole("Alice", "student")).size());
    }

    @Test
    public void testGetUserInfoWithRole_WithRoleLecture_ReturnSuccess(){
        Role role = new Role();
        role.setName(ERole.ROLE_LECTURER);

        Collection<Role> listRoles = new ArrayList<>();
        listRoles.add(role);

        eRole.add(ERole.ROLE_LECTURER);
        when(roleRepository.findRolesByNameIn(eRole)).thenReturn(listRoles);
        when(userRepository.findByUsernameAndRolesInAndStatusAndIsDeleted("Alice", listRoles , ActiveStatus.ACTIVATION , DeleteStatus.NOT_DELETED )).thenReturn((Stream.of(userInfoSummary1).collect(Collectors.toList())));

        Assertions.assertEquals(1,Arrays.asList(userService.getUserInfoWithRole("Alice", "lecturer")).size());
    }

    @Test
    public void testGetUserInfoWithRole_WithRoleAdmin_ReturnSuccess(){
        Role role = new Role();
        role.setName(ERole.ROLE_ADMIN);

        Collection<Role> listRoles = new ArrayList<>();
        listRoles.add(role);

        eRole.add(ERole.ROLE_ADMIN);
        when(roleRepository.findRolesByNameIn(eRole)).thenReturn(listRoles);
        when(userRepository.findByUsernameAndRolesInAndStatusAndIsDeleted("Alice", listRoles , ActiveStatus.ACTIVATION , DeleteStatus.NOT_DELETED )).thenReturn((Stream.of(userInfoSummary1).collect(Collectors.toList())));

        Assertions.assertEquals(1,Arrays.asList(userService.getUserInfoWithRole("Alice", "admin")).size());
    }
}
