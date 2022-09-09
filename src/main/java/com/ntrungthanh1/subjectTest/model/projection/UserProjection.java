package com.ntrungthanh1.subjectTest.model.projection;

import java.util.Date;

public interface UserProjection {
    Long getId();
    String getUsername();
    String getFullName();
    String getEmail();
    String getAddress();
    Date getDateOfBirth();
    String getGender();
    String getPhone();
}
