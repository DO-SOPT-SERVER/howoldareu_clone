package com.sopt.Server.service;

import com.sopt.Server.controller.request.MemberPostRequest;
import com.sopt.Server.controller.response.MemberGetResponse;
import com.sopt.Server.domain.Member;
import com.sopt.Server.repository.MemberJpaRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.DefaultTransactionDefinition;

@Service
@RequiredArgsConstructor
public class MemberService {

    private final MemberJpaRepository memberJpaRepository;
    private final MemberSaver memberSaver;
    private final PlatformTransactionManager transactionManager;


    public MemberGetResponse saveMember(MemberPostRequest request) {
        TransactionDefinition transactionDefinition = new DefaultTransactionDefinition();
        TransactionStatus transactionStatus = transactionManager.getTransaction(transactionDefinition);
        try {
            Member member = memberJpaRepository.findByNameOrThrow(request.nickName());
            return MemberGetResponse.of(member);
        } catch (EntityNotFoundException e) {
            Member newMember = Member.builder()
                    .name(request.nickName())
                    .realAge(request.age())
                    .build();
            memberSaver.save(newMember);
            transactionManager.commit(transactionStatus);
            return MemberGetResponse.of(newMember);
        }
    }
}
