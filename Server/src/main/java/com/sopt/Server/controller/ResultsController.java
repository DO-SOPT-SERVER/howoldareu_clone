package com.sopt.Server.controller;

import com.sopt.Server.common.ApiResponse;
import com.sopt.Server.controller.request.AnswerListRequestDTO;
import com.sopt.Server.controller.response.ResultResponseDTO;
import com.sopt.Server.exception.Success;
import com.sopt.Server.service.ResultService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/result")
@RequiredArgsConstructor
public class ResultsController {
    private final ResultService resultService;

    @PostMapping("")
    public ApiResponse<ResultResponseDTO> saveResult(AnswerListRequestDTO answerListRequestDTO){
        return ApiResponse.success(Success.CREATE_RESULT_SUCCESS,resultService.saveResult(answerListRequestDTO));
    }
}
