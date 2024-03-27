package com.sopt.Server.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.autoconfigure.restdocs.AutoConfigureRestDocs;
import org.springframework.restdocs.RestDocumentationContextProvider;
import org.springframework.restdocs.RestDocumentationExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.documentationConfiguration;

@AutoConfigureRestDocs
@ExtendWith(RestDocumentationExtension.class)
public abstract class RestDocsEnv {

    protected MockMvc mockMvc;
    protected ObjectMapper objectMapper = new ObjectMapper();

    // RestDocsEnv를 상속받아서 각 Controller Test에서 사용하기 때문에
    @BeforeEach
    void setUp(RestDocumentationContextProvider provider) {
        this.mockMvc = MockMvcBuilders
                .standaloneSetup(initializeController())
                .apply(documentationConfiguration(provider))
        .build();
    }

    protected abstract Object initializeController();
}
