package com.ntrungthanh1.subjectTest.model.projection;

import java.util.Collection;

public interface UserInfoSummary {
    Long getId();
    String getUsername();
    String getFullName();
    Collection<RoleInfoSummary> getRoles();
}
