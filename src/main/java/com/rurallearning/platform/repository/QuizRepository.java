package com.rurallearning.platform.repository;

import com.rurallearning.platform.entity.Quiz;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface QuizRepository extends JpaRepository<Quiz, Long> {

    List<Quiz> findBySubjectId(Long subjectId);
}