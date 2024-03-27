package com.sopt.Server.service;

import com.sopt.Server.controller.request.AnswerListRequest;
import com.sopt.Server.controller.request.AnswerRequest;
import com.sopt.Server.controller.response.ResultResponse;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.BDDMockito;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;

@ExtendWith(MockitoExtension.class)
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

    @Test
    @DisplayName("모든 결과를 조회할 수 있다.")
    void getAllResults() throws Exception {
        // given
        final long memberId = 1L;
        ResultResponse response1 = ResultResponse.of(
                "testNickname1",
                20,
                "testTitle1",
                "testContent1",
                "testImgUrl1",
                "testImgUrl2"
        );

        ResultResponse response2 = ResultResponse.of(
                "testNickname2",
                30,
                "testTitle2",
                "testContent2",
                "testImgUrl1",
                "testImgUrl2"
        );

        BDDMockito.given(resultService.getAllResults(anyLong()))
                .willReturn(List.of(response1, response2));

      // when
        List<ResultResponse> response = resultService.getAllResults(memberId);

      // then
        Assertions.assertThat(response)
                .extracting("nickname", "resultAge", "title", "content", "imgUrl1", "imgUrl2")
                .containsExactlyInAnyOrder(
                        Assertions.tuple("testNickname1", 20, "testTitle1", "testContent1", "testImgUrl1", "testImgUrl2"),
                        Assertions.tuple("testNickname2", 30, "testTitle2", "testContent2", "testImgUrl1", "testImgUrl2")
                );

    }
}
