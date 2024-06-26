package com.sopt.Server.controller;

import com.sopt.Server.common.ApiResponse;
import com.sopt.Server.controller.response.GetQuestionResponse;
import com.sopt.Server.exception.Success;
import com.sopt.Server.service.QuestionService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/question")
@RequiredArgsConstructor
public class QuestionController {

    private final QuestionService questionService;

    @GetMapping
    public ApiResponse<List<GetQuestionResponse>> getQuestions() {
        final List<GetQuestionResponse> questionResponseList = questionService.getQuestionResponseList();
        return ApiResponse.success(Success.GET_QUESTION_LIST_SUCCESS, questionResponseList);
    }

}
