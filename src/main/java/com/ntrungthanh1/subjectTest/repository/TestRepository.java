package com.ntrungthanh1.subjectTest.repository;

import com.ntrungthanh1.subjectTest.model.entity.Test;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TestRepository extends JpaRepository<Test, Long> {

    @Query(value =  "SELECT t from Test t where t.id = :id and status  = 1 ")
    Test findTestById(Long id);

    @Query(value = "SELECT  c.* from group_tests a " +
            "join group_details b on a.group_id = b.group_id " +
            "join tests c on c.id = a.test_id " +
            " where a.group_id = :groupId and b.user_id = :userId and c.status = 1 " +
            "and now() between c.start_date and c.end_date" , nativeQuery = true)
    List<Test> findListBySubject(@Param("groupId")  Long groupId , @Param("userId") Long userId);

}
