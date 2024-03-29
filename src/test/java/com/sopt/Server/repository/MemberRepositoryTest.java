package com.sopt.Server.repository;

import com.sopt.Server.domain.Member;
import com.sopt.Server.service.MemberSaver;
import org.aspectj.lang.annotation.After;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;

import java.util.Optional;


@DataJpaTest
@ActiveProfiles("test")
public class MemberRepositoryTest {

    @Autowired
    MemberJpaRepository memberJpaRepository;

    @AfterEach
    void tearDown() {
        memberJpaRepository.deleteAllInBatch();
    }

    @Test
    @DisplayName("이름으로 회원을 조회할 수 있다.")
    void findByName() {

      // given
        Member member = Member.builder()
                .name("오해영")
                .realAge(28)
                .build();
        memberJpaRepository.save(member);

      // when
        Optional<Member> findMember = memberJpaRepository.findByName("오해영");

      // then
        Assertions.assertThat(findMember)
                .isNotNull();

        findMember.ifPresent(m ->
            Assertions.assertThat(m)
                            .extracting("name", "realAge")
                                    .containsExactly("오해영", 28)
        );

    }

}
