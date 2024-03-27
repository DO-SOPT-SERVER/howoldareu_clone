package com.sopt.Server.repository;

import com.sopt.Server.domain.Answer;
import com.sopt.Server.domain.Question;
import org.assertj.core.api.Assertions;
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
    private QuestionJpaRepository questionJpaRepository;

    @Autowired
    private AnswerJpaRepository answerJpaRepository;

    @Test
    @DisplayName("질문과 응답 타입으로 답변을 조회할 수 있다.")
    void findByQuestionIdAndAnswerType() {
      // given
        Question question = Question.builder()
                .questionContent("질문1")
                .build();

        Question savedQuestion = questionJpaRepository.save(question);

        Answer answer = Answer.builder()
                .answerScore(10)
                .answerType(true)
                .questionId(savedQuestion.getQuestionId())
                .build();
        answerJpaRepository.save(answer);
      // when then
        Assertions.assertThat(answerJpaRepository.findByQuestionIdAndAnswerType(savedQuestion.getQuestionId(), true))
                .isNotEmpty()
                .get()
                .extracting("answerScore", "answerType", "questionId")
                .containsExactly(10, true, savedQuestion.getQuestionId());

    }
}
