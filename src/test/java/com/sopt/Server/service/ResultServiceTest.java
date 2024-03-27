package com.sopt.Server.service;

import com.sopt.Server.controller.request.AnswerListRequest;
import com.sopt.Server.controller.request.AnswerRequest;
import com.sopt.Server.controller.response.ResultResponse;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.BDDMockito;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.mockito.ArgumentMatchers.any;

@SpringBootTest
public class ResultServiceTest {

    @Mock
    ResultService resultService;

    @Test
    @DisplayName("결과를 저장할 수 있다.")
    void saveResult() throws Exception {
      // given
        ResultResponse response = ResultResponse.of(
                "testNickname",
                20,
                "testTitle",
                "testContent",
                "testImgUrl1",
                "testImgUrl2"
        );

        AnswerRequest answerRequest = AnswerRequest.of(
                1L,
                true
        );

        AnswerListRequest request = AnswerListRequest.of(
                "testNickname",
                List.of(answerRequest)
        );

        BDDMockito.given(resultService.saveResult(any(AnswerListRequest.class)))
                .willReturn(response);

        // when
        ResultResponse result = resultService.saveResult(request);

        // then
        Assertions.assertThat(result)
                .extracting("nickname", "resultAge", "title", "content", "imgUrl1", "imgUrl2")
                .contains("testNickname", 20, "testTitle", "testContent", "testImgUrl1", "testImgUrl2");

    }
}
