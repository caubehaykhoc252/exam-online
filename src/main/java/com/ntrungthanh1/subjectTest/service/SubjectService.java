package com.ntrungthanh1.subjectTest.service;

import com.ntrungthanh1.subjectTest.model.entity.Subject;
import org.springframework.transaction.annotation.Transactional;

public interface SubjectService {
    @Transactional(readOnly = true)
    Subject getSubjectById(Long id);
}
