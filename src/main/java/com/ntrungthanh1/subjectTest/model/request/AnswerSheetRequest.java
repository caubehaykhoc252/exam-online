package com.ntrungthanh1.subjectTest.model.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class AnswerSheetRequest {
    @NotNull
    private Long idTestResult;
    @NotNull
    private Long idQuestion;
    @NotEmpty
    private List<Long> idOption;
}
