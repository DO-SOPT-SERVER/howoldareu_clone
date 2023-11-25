package com.sopt.Server.service;

import com.sopt.Server.common.ApiResponse;
import com.sopt.Server.controller.response.MemberGetResponse;
import com.sopt.Server.domain.Member;
import com.sopt.Server.exception.Success;
import com.sopt.Server.repository.MemberJpaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class MemberService {

    private final MemberJpaRepository memberJpaRepository;

    public ApiResponse<MemberGetResponse> saveMember(String nickName, int age) {

        Member member = memberJpaRepository.findByName(nickName);
        if(member != null)//있다면
            return ApiResponse.success(Success.GET_MEMBER_SUCCESS, MemberGetResponse.of(member));
        //없다면
        Member newMember = new Member(nickName, age);
        memberJpaRepository.save(newMember);

        return ApiResponse.success(Success.CREATE_MEMBER_SUCCESS, MemberGetResponse.of(newMember));



    }
}
