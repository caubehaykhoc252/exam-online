package com.ntrungthanh1.subjectTest.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class OptionDTO {
    @JsonProperty("optionContent")
    private String optionContent;
    @JsonProperty("correct")
    private boolean correct;

}
