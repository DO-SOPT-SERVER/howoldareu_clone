package com.sopt.Server.domain;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Result {

    @Id @GeneratedValue
    @Column(name = "result_id")
    private Long id;

    @ManyToOne
    private Member member;

    private int resultAge;

    private LocalDateTime testedDate;


}
