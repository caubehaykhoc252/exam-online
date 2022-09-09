package com.ntrungthanh1.subjectTest.repository;

import com.ntrungthanh1.subjectTest.model.entity.Group;
import com.ntrungthanh1.subjectTest.model.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface GroupRepository extends JpaRepository<Group, Long> {

    @Query( "select o from Group o where isDeleted = 0 and status = 1 and id in :ids" )
    List<Group> findByIds(@Param("ids") List<Long> inventoryIdList);

    @Query(value = "SELECT group_id from group_details where user_id = :userId", nativeQuery = true)
    List<Long> getGroupByUserId(Long userId);
}
