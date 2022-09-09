package com.ntrungthanh1.subjectTest.service.impl;

import com.ntrungthanh1.subjectTest.exception.custom.NotFoundException;
import com.ntrungthanh1.subjectTest.model.entity.Test;
import com.ntrungthanh1.subjectTest.repository.GroupRepository;
import com.ntrungthanh1.subjectTest.repository.TestRepository;
import com.ntrungthanh1.subjectTest.repository.UserRepository;
import com.ntrungthanh1.subjectTest.service.TestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TestServiceImpl implements TestService {
    @Autowired
    private TestRepository testRepository;

    @Autowired
    private GroupRepository groupRepository;

    @Autowired
    private UserRepository userRepository;

    public List<Test> getListTest(Long groupId , Long userId){
        if(!userRepository.existsById(userId) ){
            throw new NotFoundException("Not found User");
        }
        if( !groupRepository.existsById(groupId)){
            throw new NotFoundException("Not found Group");
        }

        List<Test> list = Optional.ofNullable(testRepository.findListBySubject(groupId , userId))
                .orElseThrow(() -> new NotFoundException("Not found Test"));
        return list;
    }
}
