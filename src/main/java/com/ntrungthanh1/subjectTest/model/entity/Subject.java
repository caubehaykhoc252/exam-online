package com.ntrungthanh1.subjectTest.model.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.ntrungthanh1.subjectTest.model.entity.constant.ActiveStatus;
import com.ntrungthanh1.subjectTest.model.entity.constant.DeleteStatus;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "subjects")
public class Subject {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "COURSE_NAME")
    private String courseName;

    @Column(name = "STATUS", columnDefinition = "SMALLINT default 0")
    private ActiveStatus status; // 0 = In-activation; 1 = activation

    @Column(name = "IS_DELETED", columnDefinition = "SMALLINT default 0")
    private DeleteStatus isDeleted; // 0 = false; 1 = true

    @OneToMany(mappedBy = "subject")
    @JsonBackReference
    private List<Group> group;

}
