package com.sopt.Server.domain;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import static lombok.AccessLevel.PROTECTED;

@Entity
@NoArgsConstructor(access = PROTECTED)
@Getter
public class Answer {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long answerId;

    private Long questionId;

    private boolean answerType;

    private int answerScore;

    @Builder
    private Answer(Long questionId, boolean answerType, int answerScore) {
        this.questionId = questionId;
        this.answerType = answerType;
        this.answerScore = answerScore;
    }
}
