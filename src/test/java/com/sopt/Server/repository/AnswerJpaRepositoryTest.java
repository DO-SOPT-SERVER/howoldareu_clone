package com.sopt.Server.repository;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;

import java.util.concurrent.Callable;

@DataJpaTest
@ActiveProfiles("test")
public class AnswerJpaRepositoryTest {

    @Autowired
    private AnswerJpaRepository answerJpaRepository;

    @Test
    @DisplayName("질문과 응답 타입으로 답변을 조회할 수 있다.")
    void findByQuestionAndAnswerType() {
      // given

      // when

      // then

    }
}
