package com.ntrungthanh1.subjectTest.service.impl;

import com.ntrungthanh1.subjectTest.exception.ServiceException;
import com.ntrungthanh1.subjectTest.exception.custom.NotFoundException;
import com.ntrungthanh1.subjectTest.model.entity.Group;
import com.ntrungthanh1.subjectTest.repository.GroupRepository;
import com.ntrungthanh1.subjectTest.repository.UserRepository;
import com.ntrungthanh1.subjectTest.service.GroupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class GroupServiceImpl implements GroupService {
    @Autowired
    GroupRepository groupRepository;

    @Autowired
    UserRepository userRepository;

    @Override
    public List<Group> getListGroupByUser(Long userId){
        if(!userRepository.existsById(userId)){
            throw new NotFoundException("Not found User");
        }
        List<Long> listGroupId = Optional.ofNullable(groupRepository.getGroupByUserId(userId))
                .orElseThrow(() -> new ServiceException("Not found Group By User"));

        List<Group> list = Optional.ofNullable(groupRepository.findByIds(listGroupId))
                .orElseThrow(() -> new ServiceException("Not found Group By User"));
        if (list.isEmpty()){
            throw new NotFoundException("Not found list groups of student with id = " + userId);
        }
        return list;
    }
}
