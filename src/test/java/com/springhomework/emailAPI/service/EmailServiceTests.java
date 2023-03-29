package com.springhomework.emailAPI.service;

import com.springhomework.emailAPI.model.Email;
import com.springhomework.emailAPI.repository.EmailRepository;
import com.springhomework.emailAPI.service.EmailService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;

import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.*;

@SpringBootTest
@ActiveProfiles("test")
public class EmailServiceTests {
    @MockBean
    private EmailRepository emailRepository;
    @Autowired
    private EmailService emailService;

    @Test
    void shouldReturnEmptyWhenNoData() {
        //given
        Long id = 1L;
        when(emailRepository.findAllByUserId(id)).thenReturn(Collections.emptyList());
        //when
        List<Email> result = emailService.findByUserId(id);
        //then
        assertNotNull(result);
        assertTrue(result.isEmpty());
        verify(emailRepository,times(1)).findAllByUserId(id);
    }

    @Test
    void shouldSaveSuccessfully() {
        //given
        Long userId = 1L;
        Email savedEmail = new Email(1L, userId, "123456@thoughtworks.com");
        List<Email> savedEmails = List.of(savedEmail);
        //when
        emailService.save(userId, savedEmails);
        //then
        verify(emailRepository,times(1)).saveAll(savedEmails);
    }
}
