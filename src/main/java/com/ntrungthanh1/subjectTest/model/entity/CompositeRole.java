package com.ntrungthanh1.subjectTest.model.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;
import java.util.Objects;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Embeddable
public class CompositeRole implements Serializable {
    private static final long serialVersionUID = 1L;

    @Column(name = "ROLE_ID")
    private Long roleId;

    @Column(name = "USER_ID")
    private Long userId;

    @Override
    public int hashCode() {
        return Objects.hash(roleId, userId);
    }
}
