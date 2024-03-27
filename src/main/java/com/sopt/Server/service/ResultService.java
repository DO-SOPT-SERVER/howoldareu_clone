package com.sopt.Server.service;

import com.sopt.Server.common.AgeEnum;
import com.sopt.Server.controller.request.AnswerListRequest;
import com.sopt.Server.controller.request.AnswerRequest;
import com.sopt.Server.controller.response.ResultResponse;
import com.sopt.Server.domain.Answer;
import com.sopt.Server.domain.Member;
import com.sopt.Server.domain.Question;
import com.sopt.Server.domain.Result;
import com.sopt.Server.exception.Error;
import com.sopt.Server.exception.model.CustomException;
import com.sopt.Server.repository.AnswerJpaRepository;
import com.sopt.Server.repository.MemberJpaRepository;
import com.sopt.Server.repository.QuestionJpaRepository;
import com.sopt.Server.repository.ResultJpaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class ResultService {

    private final ResultJpaRepository resultJpaRepository;
    private final AnswerJpaRepository answerJpaRepository;
    private final MemberJpaRepository memberJpaRepository;
    private final QuestionJpaRepository questionJpaRepository;

    @Transactional
    public ResultResponse saveResult(AnswerListRequest request) {
        Member member = memberJpaRepository.findByName(request.nickname()).orElseThrow(() -> new CustomException(Error.NOT_FOUND_MEMBER_EXCEPTION, Error.NOT_FOUND_MEMBER_EXCEPTION.getMessage()));
        int memberAge = member.getRealAge();
        for (AnswerRequest result : request.results()) {
            Question question = questionJpaRepository.findById(result.questionId()).orElseThrow(() -> new CustomException(Error.NOT_FOUND_QUESTION_EXCEPTION, Error.NOT_FOUND_QUESTION_EXCEPTION.getMessage()));
            Answer answer = answerJpaRepository.findByQuestionIdAndAnswerType(question.getQuestionId(), result.answerType()).orElseThrow(() -> new CustomException(Error.NOT_FOUND_ANSWER_EXCEPTION, Error.NOT_FOUND_ANSWER_EXCEPTION.getMessage()));
            memberAge += answer.getAnswerScore();
        }
        AgeEnum ageEnum = AgeEnum.byAge(memberAge);
        resultJpaRepository.save(
                Result.builder()
                .memberId(member.getId())
                .resultAge(memberAge)
                .build());

        return ResultResponse.of(
                request.nickname(),
                memberAge,
                ageEnum.getTitle(),
                ageEnum.getContent(),
                ageEnum.getImageUrl1(),
                ageEnum.getImageUrl2());
    }

    public List<ResultResponse> getAllResults(Long memberId) {

        List<Result> results = resultJpaRepository.findAllByMemberIdOrderByIdDesc(memberId);
        Member member = memberJpaRepository.findById(memberId).orElseThrow(() -> new CustomException(Error.NOT_FOUND_MEMBER_EXCEPTION, Error.NOT_FOUND_MEMBER_EXCEPTION.getMessage()));

        return results.stream()
                .map(
                        r -> ResultResponse.of(
                                member.getName(),
                                r.getResultAge(),
                                AgeEnum.byAge(r.getResultAge()).getTitle(),
                                AgeEnum.byAge(r.getResultAge()).getContent(),
                                AgeEnum.byAge(r.getResultAge()).getImageUrl1(),
                                AgeEnum.byAge(r.getResultAge()).getImageUrl2()
                        )
                )
                .toList();
    }

    private String getStringDate(LocalDateTime time) {
        return time.getMonthValue() + "월 " + time.getDayOfMonth() + "일";
    }

}
