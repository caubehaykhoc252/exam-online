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
import com.ntrungthanh1.subjectTest.repository.RoleRepository;
import com.ntrungthanh1.subjectTest.repository.UserRepository;
import com.ntrungthanh1.subjectTest.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;

    @Override
    public List<UserProjection> getListUser() {
        return userRepository.findAllUser();
    }

    @Override
    @Cacheable(value = "userCache", key = "#userId")
    public UserProjection getUserById(Long userId) {
        if (userId == null) throw new NullPointerException("Get User fail - User Id is not null");
        else return userRepository.findUserById(userId);
    }

    @Override
    public UserInfoSummary getUserInfoWithRole(String username, String nameURL) {
        List<ERole> listEnumRoles = null;
        nameURL = nameURL.trim();
        if (nameURL.compareToIgnoreCase("student") == 0) {
            listEnumRoles = Collections.singletonList(ERole.ROLE_STUDENT);
        } else if (nameURL.compareToIgnoreCase("admin") == 0) {
            listEnumRoles = Collections.singletonList(ERole.ROLE_ADMIN);
        } else if (nameURL.compareToIgnoreCase("lecturer") == 0) {
            listEnumRoles = Collections.singletonList(ERole.ROLE_LECTURER);
        }

        Collection<Role> roles = getListRoles(listEnumRoles);

        return findUserInfoSummary(username.trim(), roles, ActiveStatus.ACTIVATION, DeleteStatus.NOT_DELETED);
    }

    @Override
    public Collection<Role> getListRoles(List<ERole> roles) {
        if (roles.isEmpty()) {
            throw new ServiceException("Role not found");
        } else {
            return roleRepository.findRolesByNameIn(roles);
        }
    }

    @Override
    public UserInfoSummary findUserInfoSummary(String username,
                                               Collection<Role> roles,
                                               ActiveStatus status,
                                               DeleteStatus isDeleted) {
        Collection<UserInfoSummary> userInfoSummaries = userRepository.findByUsernameAndRolesInAndStatusAndIsDeleted(
                username.trim(),
                roles,
                status,
                isDeleted
        );
        if (userInfoSummaries.size() == 1) {
            Iterator<UserInfoSummary> iterable = userInfoSummaries.stream().iterator();
            return iterable.next();
        } else {
            throw new NotFoundException(String.format("User %s not found", username));
        }
    }

}
