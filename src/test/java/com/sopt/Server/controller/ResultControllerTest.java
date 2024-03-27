package com.sopt.Server.controller;


import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.sopt.Server.controller.request.AnswerListRequest;
import com.sopt.Server.controller.request.AnswerRequest;
import com.sopt.Server.controller.response.ResultResponse;
import com.sopt.Server.domain.Result;
import com.sopt.Server.service.ResultService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.BDDMockito;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.ResultActions;

import java.time.LocalDateTime;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.get;
import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.post;
import static org.springframework.restdocs.payload.PayloadDocumentation.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class ResultControllerTest extends RestDocsEnv {

    private final ResultService resultService = mock(ResultService.class);

    @Override
    protected Object initializeController() {
        return new ResultController(resultService);
    }

    @Test
    @DisplayName("결과를 저장할 수 있다.")
    void saveResult() throws Exception {
      // given
        AnswerListRequest request = new AnswerListRequest(
                "테스트닉네임",
                List.of(
                        AnswerRequest.of(1L, true),
                        AnswerRequest.of(2L, false)
                )
        );

        ResultResponse response = ResultResponse.of(
                "테스트닉네임",
                20,
                "테스트 제목",
                "테스트 내용",
                "image url",
                "image url2"
        );

        given(resultService.saveResult(any(AnswerListRequest.class)))
                .willReturn(response);

      // when
        ResultActions result = mockMvc.perform(post("/result")
                .content(objectMapper.writeValueAsString(request))
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .accept(MediaType.APPLICATION_JSON_VALUE)
        );

      // then
        result.andExpect(status().isOk())
                .andDo(print())
                .andDo(document("save-result",
                        requestFields(
                                fieldWithPath("nickname").description("회원 닉네임"),
                                fieldWithPath("results[].questionId").description("질문 식별자"),
                                fieldWithPath("results[].answerType").description("답변 타입")
                        ),
                        responseFields(
                                fieldWithPath("code").description("응답 코드"),
                                fieldWithPath("message").description("응답 메시지"),
                                fieldWithPath("data.nickname").description("회원 닉네임"),
                                fieldWithPath("data.resultAge").description("회원 나이"),
                                fieldWithPath("data.title").description("연령대 제목"),
                                fieldWithPath("data.content").description("연령대 내용"),
                                fieldWithPath("data.imgUrl1").description("이미지 URL1"),
                                fieldWithPath("data.imgUrl2").description("이미지 URL2")
                        ))
                );

    }

    @Test
    @DisplayName("결과 목록을 조회할 수 있다.")
    void getAllResults() throws Exception {
        LocalDateTime time = LocalDateTime.of(
                2021,
                5, 20, 0, 0, 0);

        // given
        Result result = Result.builder()
                .resultAge(20)
                .memberId(1L)
                .testedDate(time)
                .build();

       ResultResponse response = ResultResponse.of(
                "테스트닉네임",
                20,
                "테스트 제목",
                "테스트 내용",
                "image url",
                "image url2"
        );

        ResultResponse response2 = ResultResponse.of(
                "테스트닉네임",
                20,
                "테스트 제목",
                "테스트 내용",
                "image url",
                "image url2"
        );

        BDDMockito.given(resultService.getAllResults(any(Long.class)))
                .willReturn(List.of(response,response2));

      // when
        ResultActions resultActions = mockMvc.perform(get("/result/{memberId}", 1L)
                .content(objectMapper
                        .registerModule(new JavaTimeModule())
                        .writeValueAsString(result))
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .accept(MediaType.APPLICATION_JSON_VALUE)
        );

      // then
        resultActions.andExpect(status().isOk())
                .andDo(print())
                .andDo(document("get-all-results",
                        responseFields(
                                fieldWithPath("code").description("응답 코드"),
                                fieldWithPath("message").description("응답 메시지"),
                                fieldWithPath("data[].nickname").description("회원 닉네임"),
                                fieldWithPath("data[].resultAge").description("회원 나이"),
                                fieldWithPath("data[].title").description("연령대 제목"),
                                fieldWithPath("data[].content").description("연령대 내용"),
                                fieldWithPath("data[].imgUrl1").description("이미지 URL1"),
                                fieldWithPath("data[].imgUrl2").description("이미지 URL2")
                        )
                ));

    }
}
