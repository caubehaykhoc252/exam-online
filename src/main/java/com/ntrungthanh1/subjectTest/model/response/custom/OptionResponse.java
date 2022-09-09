package com.ntrungthanh1.subjectTest.model.response.custom;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Value;

@Getter
@Setter
@NoArgsConstructor
public class OptionResponse {
    Long id;
    String optionContent;
    @Value("${some.key:false}")
    boolean choice;
}
