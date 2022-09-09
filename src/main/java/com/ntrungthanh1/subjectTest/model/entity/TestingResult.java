package com.ntrungthanh1.subjectTest.model.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.sql.Time;
import java.util.Date;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "testing_results")
public class TestingResult extends BaseTimeGeneration {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "GRADE")
    private double grade;

    @Column(name = "STATUS")
    private int status;

    @Column(name = "TEST_CODE")
    private String testCode;

    @Column(name = "FINISH_TIME")
    private Date finishTime;

    @Column(name = "START_TIME")
    private Date startTime;

    @Column(name = "TIMER")
    private Long timer;

    @ManyToOne
    @JsonManagedReference
    private Test test;

    @ManyToOne
    @JsonManagedReference
    private User user;

    @OneToMany(mappedBy = "testingResult", fetch = FetchType.EAGER)
    @JsonBackReference
    private List<AnswerSheet> answerSheet;


}
