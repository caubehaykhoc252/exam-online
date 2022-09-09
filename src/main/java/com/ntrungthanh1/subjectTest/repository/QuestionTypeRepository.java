package com.ntrungthanh1.subjectTest.repository;

import com.ntrungthanh1.subjectTest.model.entity.QuestionType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface QuestionTypeRepository extends JpaRepository<QuestionType, Long> {

    QuestionType findQuestionTypeById(Long id);
}
