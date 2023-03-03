package com.springhomework.emailAPI;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.Collections;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

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
        when(emailService.findAllById(id)).thenReturn(Collections.emptyList());

        //when
        //then
        mvc.perform(MockMvcRequestBuilders.get("/emails").contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isOk())
                .andExpect(content().string("[]"));
    }
}
