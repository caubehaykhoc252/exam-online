package com.ntrungthanh1.subjectTest.model.entity.constant;

import com.fasterxml.jackson.annotation.JsonFormat;

@JsonFormat(shape = JsonFormat.Shape.NUMBER)
public enum ActiveStatus {
    IN_ACTIVE,
    ACTIVATION
}
