package com.sopt.Server.repository;


import com.sopt.Server.domain.Member;
import com.sopt.Server.domain.Result;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;

import java.time.LocalDateTime;
import java.util.List;

@DataJpaTest
@ActiveProfiles("test")
public class ResultJpaRepositoryTest {

    private static final LocalDateTime time = LocalDateTime.of(2021, 1, 1, 0, 0, 0);

    @Autowired
    ResultJpaRepository resultJpaRepository;

    @Test
    @DisplayName("사용자 ID로 내림차순 조회를 할 수 있다.")
    void findAllByMemberIdOrderByIdDesc() throws Exception {
      // given
        Member member = Member.builder()
                .name("홍길동")
                .realAge(20)
                .build();

        Result result1 = createResult(member.getId(), 20, time);
        resultJpaRepository.save(result1);
        Result result2 = createResult(member.getId(), 21, time);
        resultJpaRepository.save(result2);
        Result result3 = createResult(member.getId(), 22, time);
        resultJpaRepository.save(result3);

        resultJpaRepository.saveAll(List.of(result1, result2, result3));
        // when
        List<Result> results = resultJpaRepository.findAllByMemberIdOrderByIdDesc(member.getId());

        // then
        Assertions.assertThat(results.get(0)).extracting(
                "resultAge", "memberId", "testedDate"
        ).containsExactly(22, member.getId(), time);
        Assertions.assertThat(results.get(1).getId()).isEqualTo(result2.getId());
        Assertions.assertThat(results.get(2).getId()).isEqualTo(result1.getId());

    }

    private Result createResult(Long memberId, int resultAge, LocalDateTime testedDate) {
        return Result.builder()
                .resultAge(resultAge)
                .memberId(memberId)
                .testedDate(testedDate)
                .build();
    }
}
