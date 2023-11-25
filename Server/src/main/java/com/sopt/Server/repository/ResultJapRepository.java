package com.sopt.Server.repository;

import com.sopt.Server.domain.Result;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ResultJapRepository extends JpaRepository<Result, Long>{
    Optional<Result> save(Result result);
}
