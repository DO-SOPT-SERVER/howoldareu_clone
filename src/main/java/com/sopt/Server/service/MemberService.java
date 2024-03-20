package com.sopt.Server.service;

import com.sopt.Server.common.ApiResponse;
import com.sopt.Server.controller.request.MemberPostRequest;
import com.sopt.Server.controller.response.MemberGetResponse;
import com.sopt.Server.domain.Member;
import com.sopt.Server.exception.Success;
import com.sopt.Server.repository.MemberJpaRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class MemberService {

    private final MemberJpaRepository memberJpaRepository;
    private final MemberSaver memberSaver;

    @Transactional
    public MemberGetResponse saveMember(MemberPostRequest request) {
        try {
            Member member = memberJpaRepository.findByNameOrThrow(request.nickName());
            return MemberGetResponse.of(member);
        } catch (EntityNotFoundException e) {
            Member newMember = Member.builder()
                    .name(request.nickName())
                    .realAge(request.age())
                    .build();
            memberSaver.save(newMember);
            return MemberGetResponse.of(newMember);
        }
    }
}
