package com.ntrungthanh1.subjectTest.service.impl;

import com.ntrungthanh1.subjectTest.model.entity.Option;
import com.ntrungthanh1.subjectTest.repository.OptionRepository;
import com.ntrungthanh1.subjectTest.service.OptionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OptionServiceImpl implements OptionService {
    @Autowired
    private OptionRepository optionRepository;

    @Override
    public List<Option> getListOption() {
        return optionRepository.findAll();
    }

    @Override
    public Option createOption(Option option) {
        return optionRepository.save(option);
    }

}
