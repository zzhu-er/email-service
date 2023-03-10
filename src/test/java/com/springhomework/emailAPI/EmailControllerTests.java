package com.springhomework.emailAPI;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.Collections;
import java.util.List;

import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
public class EmailControllerTests {
    @Autowired
    private MockMvc mvc;
    @MockBean
    private EmailService emailService;

    @Test
    void shouldGetEmptyWhenNoData() throws Exception {
        //given
        Long userId = 1L;
        when(emailService.findByUserId(userId)).thenReturn(Collections.emptyList());
        //when
        //then
        mvc.perform(MockMvcRequestBuilders.get("/emails/1").contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isOk())
                .andExpect(content().string("[]"));
        verify(emailService, times(1)).findByUserId(userId);
    }

    @Test
    void shouldGetEmailsWhenHasData() throws Exception {
        //given
        Long userId = 1L;
        Email firstSavedEmail = new Email();
        firstSavedEmail.setEmail("test1@thoughtworks.com");
        firstSavedEmail.setUserId(1L);
        Email secondSavedEmail = new Email();
        secondSavedEmail.setEmail("test2@thoughtworks.com");
        secondSavedEmail.setUserId(1L);
        List<Email> emailList = List.of(firstSavedEmail, secondSavedEmail);
        when(emailService.findByUserId(userId)).thenReturn(emailList);
        //when
        //then
        mvc.perform(MockMvcRequestBuilders.get("/emails/1").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].email", is("test1@thoughtworks.com")))
                .andExpect(jsonPath("$[0].userId", is(1)))
                .andExpect(jsonPath("$[1].email", is("test2@thoughtworks.com")))
                .andExpect(jsonPath("$[1].userId", is(1)));
        verify(emailService, times(1)).findByUserId(userId);
    }

    @Test
    void shouldGetSuccessWhenSaveEmail() throws Exception {
        //given
        Long userId = 1L;
        Email savedEmail = new Email();
        savedEmail.setId(1L);
        savedEmail.setEmail("123456@thoughtworks.com");
        List<Email> emailList = List.of(savedEmail);
        doNothing().when(emailService).save(userId, emailList);
        //when
        //then
        mvc.perform(MockMvcRequestBuilders.post("/emails/1")
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(new ObjectMapper().writeValueAsString(emailList)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$").value("EMAIL SAVED SUCCESSFULLY"));
        verify(emailService,times(1)).save(userId, emailList);
    }
}
