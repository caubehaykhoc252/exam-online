package com.ntrungthanh1.subjectTest.model.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.ntrungthanh1.subjectTest.model.entity.constant.ActiveStatus;
import com.ntrungthanh1.subjectTest.model.entity.constant.DeleteStatus;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;
import java.util.Set;

@Getter
@Setter
@Entity
@Table(name = "groupss")
@NoArgsConstructor
public class Group extends BaseTimeGeneration {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "NAME")
    private String name;

    @Column(name = "STATUS", columnDefinition = "SMALLINT default 0")
    private ActiveStatus status; // 0 = In-activation; 1 = activation

    @Column(name = "IS_DELETED", columnDefinition = "SMALLINT default 0")
    private DeleteStatus isDeleted; // 0 = false; 1 = true

    @ManyToOne
    @JsonBackReference
    private User lecturer;

    @ManyToMany(fetch = FetchType.EAGER)
    @JsonBackReference
    @JoinTable(
            name = "group_details",
            joinColumns = {@JoinColumn(name = "group_id")},
            inverseJoinColumns = {@JoinColumn(name = "user_id")}
    )
    private Set<User> students;


    @ManyToMany(fetch = FetchType.EAGER)
    @JsonBackReference
    @JoinTable(
            name = "group_tests",
            joinColumns = {@JoinColumn(name = "group_id")},
            inverseJoinColumns = {@JoinColumn(name = "test_id")}
    )
    private Set<Test> tests;
    @ManyToOne
    @JsonManagedReference
    private Subject subject;

    public Group(String name, Subject subject, User lecturer, Set<User> studentList) {
        this.name = name;
        this.subject = subject;
        this.lecturer = lecturer;
        this.students = studentList;
    }
}
