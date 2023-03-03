package com.springhomework.emailAPI;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collections;
import java.util.List;

@RestController
@RequestMapping("/emails")
public class EmailController {
    @GetMapping
    public List<Email> getById(Long id) {
        return Collections.emptyList();
    }
}
