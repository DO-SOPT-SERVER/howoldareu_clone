package com.sopt.Server.controller;

import com.sopt.Server.common.ApiResponse;
import com.sopt.Server.controller.request.AnswerListRequest;
import com.sopt.Server.controller.response.ResultResponse;
import com.sopt.Server.exception.Success;
import com.sopt.Server.service.ResultService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/result")
@RequiredArgsConstructor
public class ResultController {

    private final ResultService resultService;

    @PostMapping
    public ApiResponse<ResultResponse> saveResult(
            @RequestBody final AnswerListRequest answerListRequest) {
        return ApiResponse.success(Success.CREATE_RESULT_SUCCESS, resultService.saveResult(answerListRequest));
    }

    @GetMapping("/{memberId}")
    public ApiResponse<List<ResultResponse>> getAllResults(
            @PathVariable final Long memberId) {
        return ApiResponse.success(Success.GET_USER_LIST_SUCCESS, resultService.getAllResults(memberId));
    }

}