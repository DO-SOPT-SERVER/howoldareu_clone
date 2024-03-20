package com.sopt.Server.repository;


import com.sopt.Server.domain.Question;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;

import java.util.List;

@DataJpaTest
@ActiveProfiles("test")
public class QuestionJpaRepositoryTest {

    @Autowired
    QuestionJpaRepository questionJpaRepository;

    @Test
    @DisplayName("질문 목록을 조회할 수 있다.")
    void findAll() {
      // given
        questionJpaRepository.saveAll(
                List.of(createQuestion("질문1"),
                        createQuestion("질문2"),
                        createQuestion("질문3"))
        );
        // when
        List<Question> questions = questionJpaRepository.findAll();

        // then
        Assertions.assertThat(questions)
                .extracting("questionContent")
                .containsExactlyInAnyOrder("질문1", "질문2", "질문3");

    }

    private Question createQuestion(String content) {
        return Question.builder()
                .questionContent(content)
                .build();
    }
}
