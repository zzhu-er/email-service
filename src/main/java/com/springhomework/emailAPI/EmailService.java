package com.springhomework.emailAPI;

import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class EmailService {
    private EmailRepository emailRepository;

    public Email findById(Long id) {
        Optional<Email> findEmail =  emailRepository.findById(id);
        return findEmail.orElse(null);
    }

    public void save(Email savedEmail) {
        emailRepository.save(savedEmail);
    }
}
