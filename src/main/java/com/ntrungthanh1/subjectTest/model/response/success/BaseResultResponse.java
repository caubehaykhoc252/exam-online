package com.ntrungthanh1.subjectTest.model.response.success;

import com.ntrungthanh1.subjectTest.model.response.AbstractResponse;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class BaseResultResponse<T> extends AbstractResponse {
    protected int code;
    protected String message;
    protected T data;
}
