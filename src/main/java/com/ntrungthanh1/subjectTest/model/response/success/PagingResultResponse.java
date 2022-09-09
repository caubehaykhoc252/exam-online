package com.ntrungthanh1.subjectTest.model.response.success;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PagingResultResponse<T> extends BaseResultResponse<T> {
    private PageInfo pageInfo;
    public PagingResultResponse(int errorCode, T data, PageInfo pageInfo) {
        super(errorCode, "Request was successfully!", data);
        this.pageInfo = pageInfo;
    }
}

