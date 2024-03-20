package com.sopt.Server.domain;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Result{

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "result_id")
    private Long id;

    private Long memberId;

    private int resultAge;

    @CreatedDate
    private LocalDateTime testedDate;

    @Builder
    private Result(Long memberId, int resultAge, LocalDateTime testedDate) {
        this.memberId = memberId;
        this.resultAge = resultAge;
        this.testedDate = testedDate;
    }
}
