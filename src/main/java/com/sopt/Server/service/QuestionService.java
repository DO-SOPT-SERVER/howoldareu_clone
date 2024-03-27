package com.sopt.Server.service;

import com.sopt.Server.controller.response.GetQuestionResponse;
import com.sopt.Server.repository.QuestionJpaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class QuestionService {
    private final QuestionJpaRepository questionJpaRepository;

    public List<GetQuestionResponse> getQuestionResponseList() {
        return questionJpaRepository.findAll()
                .stream()
                .map(GetQuestionResponse::of)
                .toList();
    }

}
