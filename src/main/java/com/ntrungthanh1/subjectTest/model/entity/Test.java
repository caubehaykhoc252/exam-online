package com.ntrungthanh1.subjectTest.model.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "tests")
public class Test extends BaseTimeGeneration {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "TITLE")
    private String title;

    @Column(name = "DESCRIPTION")
    private String description;

    @Column(name = "START_DATE" , columnDefinition="TIMESTAMP")
    @Temporal(TemporalType.TIMESTAMP)
    private Date startDate;

    @Column(name = "END_DATE" , columnDefinition="TIMESTAMP")
    @Temporal(TemporalType.TIMESTAMP)
    private Date endDate;

    @Column(name = "DOING_DURATION" )
    private int doingDuration;

    @Column(name = "CODE")
    private int code;

    @Column(name = "NUMBER_TEST")
    private int numberTest;

    @Column(name = "STATUS")
    private int status;

    @ManyToOne
    private Subject subject;

    @OneToMany(mappedBy = "test" )
    @JsonBackReference
    private List<TestingDetail> testingDetail;

    @OneToMany(mappedBy = "test" )
    @JsonBackReference
    private List<TestingResult> testingResult;

}
