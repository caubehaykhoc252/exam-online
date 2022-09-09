package com.ntrungthanh1.subjectTest.model.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;
import java.util.Set;

@Getter
@Setter
@Entity
@Table(name = "options")
public class Option {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "OPTION_CONTENT")
    private String optionContent;

    @Column(name = "CORRECT")
    private boolean correct;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.DETACH)
    @JoinColumn(name = "QUESTION_ID")
    @JsonBackReference
    private Question question;


    @OneToMany(mappedBy = "option", fetch = FetchType.EAGER)
    @JsonManagedReference
    private Set<AnswerSheet> answerSheet;
}
