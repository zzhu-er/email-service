package com.springhomework.emailAPI;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.SecurityProperties;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.Mockito.*;

@SpringBootTest
public class EmailServiceTests {
    @MockBean
    private EmailRepository emailRepository;
    @Autowired
    private EmailService emailService;

    @Test
    void shouldReturnEmptyWhenNoData() {
        //given
        Long id = 1L;
        when(emailRepository.findById(id)).thenReturn(Optional.ofNullable(null));
        //when
        Email result = emailService.findById(id);
        //then
        assertNull(result);
        verify(emailRepository,times(1)).findById(id);
    }

    @Test
    void shouldSaveSuccessfully() {
        //given
        Email savedEmail = new Email();
        savedEmail.setEmail("123456@thoughtworks.com");
        savedEmail.setId(1L);
        //when
        emailService.save(savedEmail);
        //then
        verify(emailRepository,times(1)).save(savedEmail);
    }
}
