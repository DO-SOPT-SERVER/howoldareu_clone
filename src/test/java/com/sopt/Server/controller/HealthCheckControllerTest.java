package com.sopt.Server.controller;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class HealthCheckControllerTest extends RestDocsEnv {

    @Override
    protected Object initializeController() {
        return new HealthCheckController();
    }

    @Test
    @DisplayName("서버 상태 확인")
    void healthCheck() throws Exception {
        mockMvc.perform(get("/health"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().string("OK"));
    }


}
