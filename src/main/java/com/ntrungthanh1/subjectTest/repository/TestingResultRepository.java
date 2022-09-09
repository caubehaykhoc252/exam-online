package com.ntrungthanh1.subjectTest.repository;

import com.ntrungthanh1.subjectTest.model.entity.TestingResult;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TestingResultRepository extends JpaRepository<TestingResult, Long> {

    @Query(value = "SELECT t from TestingResult t where t.id = :id and t.status = 1")
    TestingResult getTestResultById(@Param("id") Long id);

    @Query(value = "SELECT * from testing_results  where test_id = :test and user_id = :user and status = 1" , nativeQuery = true)
    List<TestingResult> findByTestIdAndUserId(Long test , Long user);
}
