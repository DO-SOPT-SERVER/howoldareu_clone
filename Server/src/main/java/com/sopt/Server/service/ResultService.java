package com.sopt.Server.service;

import com.sopt.Server.common.AgeEnum;
import com.sopt.Server.controller.request.AnswerListRequestDTO;
import com.sopt.Server.controller.request.AnswerRequestDTO;
import com.sopt.Server.controller.response.ResultResponseDTO;
import com.sopt.Server.domain.Answer;
import com.sopt.Server.domain.Member;
import com.sopt.Server.domain.Question;
import com.sopt.Server.domain.Result;
import com.sopt.Server.exception.Error;
import com.sopt.Server.exception.model.CustomException;
import com.sopt.Server.repository.AnswerJpaRepository;
import com.sopt.Server.repository.MemberJpaRepository;
import com.sopt.Server.repository.QuestionJpaRepository;
import com.sopt.Server.repository.ResultJapRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class ResultService {
    private final ResultJapRepository resultJapRepository;
    private final AnswerJpaRepository answerJpaRepository;
    private final MemberJpaRepository memberJpaRepository;
    private final QuestionJpaRepository questionJpaRepository;

    @Transactional
    public ResultResponseDTO saveResult(AnswerListRequestDTO request) {
        Member member = memberJpaRepository.findByName(request.nickname()).orElseThrow(()->new CustomException(Error.NOT_FOUND_MEMBER_EXCEPTION,Error.NOT_FOUND_MEMBER_EXCEPTION.getMessage()));
        int memberAge = member.getRealAge();
        for(AnswerRequestDTO result : request.results()){
            Question question = questionJpaRepository.findById(result.questionId()).orElseThrow(()->new CustomException(Error.NOT_FOUND_QUESTION_EXCEPTION,Error.NOT_FOUND_QUESTION_EXCEPTION.getMessage()));
            Answer answer = answerJpaRepository.findByQuestionAndAnswerType(question, result.answerType()).orElseThrow(()->new CustomException(Error.NOT_FOUND_ANSWER_EXCEPTION,Error.NOT_FOUND_ANSWER_EXCEPTION.getMessage()));
            memberAge += answer.getAnswerScore();
        }
        AgeEnum ageEnum = getAgeEnum(memberAge);
        resultJapRepository.save(Result.builder().member(member).resultAge(memberAge).build());
        return ResultResponseDTO.of(request.nickname(),memberAge,ageEnum.getTitle(),ageEnum.getContent(),ageEnum.getImageUrl());
    }

    private AgeEnum getAgeEnum(int age){
        if(age < 20)
            return AgeEnum.TEENAGER;
        else if(age < 30)
            return AgeEnum.TWENTIES;
        else if(age < 40)
            return AgeEnum.THIRTIES;
        else if(age < 50)
            return AgeEnum.FORTIES;
        else
            return AgeEnum.FIFTIES;
    }

}
