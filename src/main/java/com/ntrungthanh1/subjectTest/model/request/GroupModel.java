package com.ntrungthanh1.subjectTest.model.request;

import io.swagger.annotations.ApiModel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@ApiModel(value = "Group Creation Request")
public class GroupModel {
    private long id;

    @NotNull
    @Size(min = 2, max = 50)
    private String name;

    @NotNull
    @Size(min = 2, max = 50)
    private String courseName;

    @NotNull
    @Size(min = 3, max = 50)
    private String supervisor;

    private List<String> studentList;
}
