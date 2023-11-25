package com.sopt.Server.repository;

import com.sopt.Server.domain.Question;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface QuestionJpaRepository extends JpaRepository<Question, Long>{
    List<Question> findAll();
}
