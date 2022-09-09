package com.ntrungthanh1.subjectTest.repository;

import com.ntrungthanh1.subjectTest.model.entity.TestingDetail;
import com.ntrungthanh1.subjectTest.model.projection.QuestionProjection;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TestingDetailRepository extends JpaRepository<TestingDetail, Long> {

    @Query(value = "SELECT question_id from testing_details where test_id = :id" , nativeQuery = true)
    List<QuestionProjection> getListQuestionByTest(Long id);

    @Query(value = "SELECT t.test_code from testing_details t where t.test_id = :id" , nativeQuery = true)
    List<String> findTestingDetailById(Long id);
}
