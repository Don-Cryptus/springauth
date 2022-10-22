package com.springauth.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.springauth.payload.request.LoginRequest;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class AuthControllerTest {

    @Autowired
    ObjectMapper objectMapper;
    @Autowired
    MockMvc mockMvc;

    @Test
    void loginUser() throws Exception {

        LoginRequest loginRequest = new LoginRequest("test", "zS5HFXTFxYJsow4k");
        String loginRequestAsString = objectMapper.writeValueAsString(loginRequest);

        MvcResult result = mockMvc.perform(post("/api/auth/login")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(loginRequestAsString)
                        .characterEncoding("utf-8"))
                .andExpectAll(
                        status().isOk(),
                        MockMvcResultMatchers.jsonPath("$.username").value("test")
                )
                .andReturn();

        System.out.println(result.getResponse().getContentAsString());
    }
}