package com.springhomework.emailAPI;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class EmailService {
    private EmailRepository emailRepository;

    public List<Email> findByUserId(Long id) {
        return emailRepository.findAllByUserId(id);
    }

    public void save(Long userId, List<Email> savedEmails) {
        savedEmails.forEach(email -> email.setUserId(userId));
        emailRepository.saveAll(savedEmails);
    }
}
