package com.ntrungthanh1.subjectTest.model.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jdk.jfr.Timestamp;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

@Getter
@Setter
@Entity
@Table(name = "answer_sheets")
public class AnswerSheet {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "CHOSEN_ANSWER")
    private String chosenAnswer;

    @Column(name = "CHOSEN_TIME")
    @Timestamp
    private Date chosenTime;

    @Column(name = "GRADE")
    private double grade;

    @ManyToOne
    @JsonManagedReference
    private TestingResult testingResult;

    @ManyToOne
    @JsonManagedReference
    private Question question;

    @ManyToOne
    @JsonManagedReference
    private Option option;

}
