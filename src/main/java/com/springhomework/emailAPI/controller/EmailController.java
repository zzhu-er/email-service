package com.springhomework.emailAPI.controller;

import com.springhomework.emailAPI.service.EmailService;
import com.springhomework.emailAPI.model.Email;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/emails")
@AllArgsConstructor
public class EmailController {

    private EmailService emailService;

    @GetMapping("/{id}")
    public List<Email> getByUserId(@PathVariable Long id) {
        return emailService.findByUserId(id);
    }
    @PostMapping("/{userId}")
    public ResponseEntity<String> save(@PathVariable Long userId, @RequestBody List<Email> savedEmails) {
        emailService.save(userId, savedEmails);
        return new ResponseEntity<>("EMAIL SAVED SUCCESSFULLY", HttpStatus.CREATED);
    }
}
