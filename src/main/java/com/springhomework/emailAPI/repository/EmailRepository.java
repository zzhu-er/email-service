package com.springhomework.emailAPI.repository;

import com.springhomework.emailAPI.model.Email;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface EmailRepository extends JpaRepository<Email, Long> {
    List<Email> findAllByUserId(Long id);
}
