package com.sopt.Server.service;


import com.sopt.Server.controller.request.MemberPostRequest;
import com.sopt.Server.controller.response.MemberGetResponse;
import com.sopt.Server.domain.Member;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.BDDMockito;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;

@SpringBootTest
public class MemberServiceTest {

    @Mock
    private MemberService memberService;

    @Test
    @DisplayName("회원을 생성할 수 있다.")
    void saveMember() {
      // given

        MemberGetResponse memberGetResponse = new MemberGetResponse(
                1L,
                "testNickname",
                20
        );

        MemberPostRequest request = new MemberPostRequest(
                "testNickname",
                20
        );

        BDDMockito.given(memberService.saveMember(any(MemberPostRequest.class)))
                .willReturn(memberGetResponse);
        // when
        MemberGetResponse response = memberService.saveMember(request);

        // then
        assertThat(response)
                .extracting("nickName", "realAge")
                .containsExactly("testNickname", 20);

    }

}
