package com.ntrungthanh1.subjectTest.service;

import com.ntrungthanh1.subjectTest.model.entity.Group;
import com.ntrungthanh1.subjectTest.model.response.AbstractResponse;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface GroupService {
    List<Group> getListGroupByUser(Long userId);
}
