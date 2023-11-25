package com.sopt.Server.repository;

import com.sopt.Server.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberJpaRepository extends JpaRepository<Member, Long> {
    Member findByName(String name);
}
