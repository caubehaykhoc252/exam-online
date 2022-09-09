package com.ntrungthanh1.subjectTest.service;

import com.ntrungthanh1.subjectTest.model.entity.Group;
import com.ntrungthanh1.subjectTest.model.entity.Role;
import com.ntrungthanh1.subjectTest.model.entity.constant.ActiveStatus;
import com.ntrungthanh1.subjectTest.model.entity.constant.DeleteStatus;
import com.ntrungthanh1.subjectTest.model.entity.constant.ERole;
import com.ntrungthanh1.subjectTest.model.projection.UserInfoSummary;
import com.ntrungthanh1.subjectTest.model.projection.UserProjection;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;
import java.util.List;

public interface UserService {

    @Transactional(readOnly = true)
    List<UserProjection> getListUser();

    @Transactional(readOnly = true)
    UserProjection getUserById(Long id);

    @Transactional(readOnly = true)
    Collection<Role> getListRoles(List<ERole> roles);

    @Transactional(readOnly = true)
    UserInfoSummary getUserInfoWithRole(String username, String nameURL);

    @Transactional(readOnly = true)
    UserInfoSummary findUserInfoSummary(String username,
                                        Collection<Role> roles,
                                        ActiveStatus status,
                                        DeleteStatus isDeleted);
}
