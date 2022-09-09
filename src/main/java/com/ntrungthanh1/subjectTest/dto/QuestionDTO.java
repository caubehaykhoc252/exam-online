package com.ntrungthanh1.subjectTest.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.util.List;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class QuestionDTO {
    @NotEmpty
    @NotNull
    private String question;
    @Positive
    private double mark;
    @NotNull
    private Long questionTypeId;
    private boolean shuffle;
    @NotNull
    private Long subjectId;
    @NotNull
    private List<OptionDTO> options;

}
