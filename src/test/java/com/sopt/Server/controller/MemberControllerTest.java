package com.sopt.Server.controller;


import com.sopt.Server.controller.request.MemberPostRequest;
import com.sopt.Server.controller.response.MemberGetResponse;
import com.sopt.Server.service.MemberService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.BDDMockito;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.restdocs.payload.JsonFieldType;
import org.springframework.test.web.servlet.ResultActions;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.post;
import static org.springframework.restdocs.payload.PayloadDocumentation.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class MemberControllerTest extends RestDocsEnv {

    @MockBean
    private final MemberService memberService = mock(MemberService.class);

    @Override
    protected Object initializeController() {
        return new MemberController(memberService);
    }

    @Test
    @DisplayName("회원을 생성할 수 있다.")
    void saveMember() throws Exception {
      // given
        MemberGetResponse response = new MemberGetResponse(1L, "테스트닉네임", 20);

        BDDMockito.given(memberService.saveMember(any(MemberPostRequest.class)))
                .willReturn(response);

        MemberPostRequest request = new MemberPostRequest("테스트닉네임", 20);

        // when
        ResultActions result = this.mockMvc.perform(
                post("/member")
                    .content(objectMapper.writeValueAsString(request))
                    .contentType(MediaType.APPLICATION_JSON_VALUE)
                    .accept(MediaType.APPLICATION_JSON_VALUE)
                );
        // then
        result.andExpect(status().isOk())
                .andDo(print())
                .andDo(document("save-member",
                        requestFields(
                                fieldWithPath("nickName").type(JsonFieldType.STRING).description("회원 닉네임"),
                                fieldWithPath("age").type(JsonFieldType.NUMBER).description("회원 나이")
                        ),
                        responseFields(
                                fieldWithPath("code").type(JsonFieldType.NUMBER).description("응답 코드"),
                                fieldWithPath("message").type(JsonFieldType.STRING).description("응답 메시지"),
                                fieldWithPath("data.memberId").type(JsonFieldType.NUMBER).description("회원 식별자"),
                                fieldWithPath("data.nickName").type(JsonFieldType.STRING).description("회원 닉네임"),
                                fieldWithPath("data.realAge").type(JsonFieldType.NUMBER).description("회원 나이")
                        )
                ));

    }


}
