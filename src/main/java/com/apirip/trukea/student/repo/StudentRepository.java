package com.apirip.trukea.student.repo;

import com.apirip.trukea.student.domain.Student;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface StudentRepository extends JpaRepository<Student, Integer> {
    Optional<Student> findByEmail(String email);
    boolean existsByEmail(String email);
}