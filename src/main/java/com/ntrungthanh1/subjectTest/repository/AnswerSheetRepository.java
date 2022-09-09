package com.ntrungthanh1.subjectTest.repository;

import com.ntrungthanh1.subjectTest.model.entity.AnswerSheet;
import com.ntrungthanh1.subjectTest.model.projection.AnswerProjection;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AnswerSheetRepository extends JpaRepository<AnswerSheet, Long> {
    @Query(value = "SELECT * from answer_sheets  where testing_result_id = :testResultId and question_id = :questionId" , nativeQuery = true)
    List<AnswerSheet> findByTestingResultAndQuestion(Long testResultId , Long questionId);

    @Query(value = "SELECT a from AnswerSheet a where a.testingResult.id = :testResultId " )
    List<AnswerProjection> findByTestingResult(Long testResultId);

}
