package com.springhomework.emailAPI;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;

@RestController
@RequestMapping("/emails")
@AllArgsConstructor
public class EmailController {

    private EmailService emailService;

    @GetMapping("/{id}")
    public Email getById(@PathVariable Long id) {
        return emailService.findById(id);
    }
    @PostMapping
    public ResponseEntity<String> save(@RequestBody Email savedEmail) {
        emailService.save(savedEmail);
        return new ResponseEntity<>("EMAIL SAVED SUCCESSFULLY", HttpStatus.CREATED);
    }
}
