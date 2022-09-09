package com.ntrungthanh1.subjectTest.service;

import com.ntrungthanh1.subjectTest.model.entity.Option;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface OptionService {

    /**
     * Get List Option
     * @return
     */
    @Transactional(readOnly = true)
    List<Option> getListOption();

    /**
     * Create option
     * @param option
     * @return
     */
    @Transactional
    Option createOption(Option option);

}
