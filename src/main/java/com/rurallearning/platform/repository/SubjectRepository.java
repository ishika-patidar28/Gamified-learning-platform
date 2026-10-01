package com.rurallearning.platform.repository;

import com.rurallearning.platform.entity.Subject;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SubjectRepository extends JpaRepository<Subject, Long> {
}
