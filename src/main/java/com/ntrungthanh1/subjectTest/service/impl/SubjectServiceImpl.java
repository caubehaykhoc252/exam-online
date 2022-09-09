package com.ntrungthanh1.subjectTest.service.impl;

import com.ntrungthanh1.subjectTest.model.entity.Subject;
import com.ntrungthanh1.subjectTest.repository.SubjectRepository;
import com.ntrungthanh1.subjectTest.service.SubjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SubjectServiceImpl implements SubjectService {
    @Autowired
    private SubjectRepository subjectRepository;

    @Override
    public Subject getSubjectById(Long id) {
        return subjectRepository.findSubjectById(id);
    }
}
