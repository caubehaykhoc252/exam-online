package com.ntrungthanh1.subjectTest.repository;

import com.ntrungthanh1.subjectTest.model.entity.Question;
import com.ntrungthanh1.subjectTest.model.projection.QuestionProjection;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface QuestionRepository extends JpaRepository<Question, Long> {

    @Query(value = "SELECT q FROM Question q where q.isDeleted = 0 and q.status = 1")
    List<QuestionProjection> findAllQuestion();

    @Query(value = "SELECT q from Question q where q.id = :id and q.isDeleted = 0 and q.status = 1")
    QuestionProjection findQuestionById(@Param("id") Long id);

    @Query(value = "SELECT q from Question q where q.question = :question and q.isDeleted = 0 and q.status = 1")
    Question findByQuestion(@Param("question") String question);

    @Query(value = "SELECT q from Question q where q.isDeleted = 0 and q.status = 1 and q.subjects.id = :subjectId")
    List<Question> findListBySubjectId(@Param("subjectId") Long subjectId);

    @Query(value = "SELECT q from Question q where q.id = :id and q.isDeleted = 0 and q.status = 1")
    Question getQuestionById(@Param("id") Long id);

    @Query(value = "select a from Question a join TestingDetail b on a.id = b.question.id where b.test.id = :testId and b.testCode = :code" )
    List<QuestionProjection> findListByTestCode(@Param("code") String code ,Long testId);

}
