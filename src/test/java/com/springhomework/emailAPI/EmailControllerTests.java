package com.springhomework.emailAPI;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
public class EmailControllerTests {
    @Autowired
    private MockMvc mvc;
    @MockBean
    private EmailService emailService;

    @Test
    void shouldGetEmptyWhenNoData() throws Exception {
        //given
        Long id = 1L;
        when(emailService.findById(id)).thenReturn(null);
        //when
        //then
        mvc.perform(MockMvcRequestBuilders.get("/emails/1").contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").doesNotExist());
        verify(emailService, times(1)).findById(id);
    }

    @Test
    void shouldGetSuccessWhenSaveEmail() throws Exception {
        //given
        Email savedEmail = new Email();
        savedEmail.setId(1L);
        savedEmail.setEmail("123456@thoughtworks.com");
        doNothing().when(emailService).save(savedEmail);
        //when
        //then
        mvc.perform(MockMvcRequestBuilders.post("/emails")
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(new ObjectMapper().writeValueAsString(savedEmail)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$").value("EMAIL SAVED SUCCESSFULLY"));
        verify(emailService,times(1)).save(savedEmail);
    }
}
