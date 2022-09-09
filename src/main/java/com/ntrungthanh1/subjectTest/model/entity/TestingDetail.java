package com.ntrungthanh1.subjectTest.model.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Entity
@Table(name = "Testing_details")
public class TestingDetail {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "TEST_ORDER")
    private Integer testOrder;

    @Column(name = "TEST_CODE")
    private String testCode;

    @ManyToOne
    @JsonManagedReference
    private Test test;

    @ManyToOne
    @JsonManagedReference
    private Question question;

}
