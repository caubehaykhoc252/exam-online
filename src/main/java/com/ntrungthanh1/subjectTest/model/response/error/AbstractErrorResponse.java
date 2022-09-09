package com.ntrungthanh1.subjectTest.model.response.error;

import com.ntrungthanh1.subjectTest.model.response.AbstractResponse;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@Builder
public class AbstractErrorResponse extends AbstractResponse {
    protected int code;
    protected String message;
}
