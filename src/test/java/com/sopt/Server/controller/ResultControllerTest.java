package com.sopt.Server.controller;


import com.sopt.Server.service.ResultService;

import static org.mockito.Mockito.mock;

public class ResultControllerTest extends RestDocsSupport {

    private final ResultService resultService = mock(ResultService.class);

    @Override
    protected Object initializeController() {
        return new ResultController(resultService);
    }
}
