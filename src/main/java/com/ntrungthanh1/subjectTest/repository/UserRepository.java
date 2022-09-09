package com.ntrungthanh1.subjectTest.repository;

import com.ntrungthanh1.subjectTest.model.entity.Group;
import com.ntrungthanh1.subjectTest.model.entity.Role;
import com.ntrungthanh1.subjectTest.model.entity.User;
import com.ntrungthanh1.subjectTest.model.entity.constant.ActiveStatus;
import com.ntrungthanh1.subjectTest.model.entity.constant.DeleteStatus;
import com.ntrungthanh1.subjectTest.model.projection.UserInfoSummary;
import com.ntrungthanh1.subjectTest.model.projection.UserProjection;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    UserProjection findUserById(Long id);

    @Query(value = "SELECT u from User u where u.status = 1 and u.isDeleted = 0 and u.id = :id")
    User getUserById(Long id);

    @Query(value = "select u from User u where u.status = 1 and u.isDeleted = 0")
    List<UserProjection> findAllUser();

    Optional<User> findByUsernameAndStatusAndIsDeleted(String username, ActiveStatus status, DeleteStatus isDeleted);

    Collection<UserInfoSummary> findByUsernameAndRolesInAndStatusAndIsDeleted(String username,
                                                                              Collection<Role> roles,
                                                                              ActiveStatus status,
                                                                              DeleteStatus isDeleted);
}
