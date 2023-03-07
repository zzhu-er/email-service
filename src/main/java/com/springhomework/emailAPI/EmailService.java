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

    public List<Email> findById(Long id) {
        return emailRepository.findAllByUserId(id);
    }

    public void save(List<Email> savedEmails) {
        emailRepository.saveAll(savedEmails);
    }
}
