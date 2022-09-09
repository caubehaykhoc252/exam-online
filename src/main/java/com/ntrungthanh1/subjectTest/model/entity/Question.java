package com.ntrungthanh1.subjectTest.model.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.ntrungthanh1.subjectTest.model.entity.constant.ActiveStatus;
import com.ntrungthanh1.subjectTest.model.entity.constant.DeleteStatus;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.LastModifiedBy;

import javax.persistence.*;
import java.util.Date;
import java.util.List;
import java.util.Set;

@Getter
@Setter
@Entity
@Table(name = "questions")
public class Question {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "QUESTIONS")
    private String question;

    @Column(name = "MARK")
    private double mark;

    @Column(name = "IS_SHUFFLE")
    private boolean isShuffled;

    @Column(name = "STATUS", columnDefinition = "SMALLINT default 0")
    private ActiveStatus status; // 0 = In-activation; 1 = activation

    @Column(name = "IS_DELETED", columnDefinition = "SMALLINT default 0")
    private DeleteStatus isDeleted; // 0 = false; 1 = true

    @ManyToOne
    private QuestionType questionType;

    @ManyToOne
    private Subject subjects;

    @OneToMany(mappedBy = "question", fetch = FetchType.EAGER)
    @JsonManagedReference
    private List<Option> options;

    @Column(name = "CREATED_DATE")
    @CreatedBy
    private Date createdDate;

    @Column(name = "UPDATED_DATE")
    @LastModifiedBy
    private Date updatedDate;

    @OneToMany(mappedBy = "question", fetch = FetchType.EAGER)
    @JsonManagedReference
    private Set<AnswerSheet> answerSheet;


}
