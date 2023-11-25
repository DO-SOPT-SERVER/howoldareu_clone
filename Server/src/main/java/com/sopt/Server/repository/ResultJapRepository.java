package com.sopt.Server.repository;

import com.sopt.Server.domain.Result;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ResultJapRepository extends JpaRepository<Result, Long>{
    Result save(Result result);
}
