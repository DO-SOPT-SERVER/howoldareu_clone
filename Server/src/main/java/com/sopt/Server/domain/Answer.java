package com.sopt.Server.domain;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Answer {

    @Id @GeneratedValue
    @Column(name = "answer_id")
    private Long id;

    @ManyToOne
    private Question question;

    private boolean answer_type;

    private int answer_score;

}
