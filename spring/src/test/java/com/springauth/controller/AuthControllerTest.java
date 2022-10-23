package com.springauth.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.springauth.entity.User;
import com.springauth.payload.request.LoginRequest;
import com.springauth.payload.request.RegisterRequest;
import com.springauth.repository.UserRepository;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static com.springauth.testdata.UserTestData.*;
import static org.hamcrest.Matchers.containsString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class AuthControllerTest {

    @Autowired
    PasswordEncoder encoder;
    @Autowired
    ObjectMapper objectMapper;
    @Autowired
    MockMvc mockMvc;
    @Autowired
    UserRepository userRepository;

    @BeforeAll
    public void setup() {
        userRepository.save(new User(USERNAME, EMAIL, encoder.encode(PASSWORD)));
    }

    @Test
    void registerUser() throws Exception {

        RegisterRequest registerRequest = new RegisterRequest("test1", "test1@test.test", PASSWORD);
        String registerRequestAsString = objectMapper.writeValueAsString(registerRequest);

        mockMvc.perform(post("/api/auth/register")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(registerRequestAsString)
                        .characterEncoding("utf-8"))
                .andExpectAll(
                        status().isOk(),
                        MockMvcResultMatchers.jsonPath("$.message").value("User registered successfully!")
                )
                .andReturn();
    }

    @Test
    void registerUser_usernameAlreadyExist() throws Exception {

        RegisterRequest registerRequest = new RegisterRequest(USERNAME, "test2@test.test", PASSWORD);

        String registerRequestAsString = objectMapper.writeValueAsString(registerRequest);

        mockMvc.perform(post("/api/auth/register")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(registerRequestAsString)
                        .characterEncoding("utf-8"))
                .andExpectAll(
                        status().is4xxClientError(),
                        MockMvcResultMatchers.jsonPath("$.message").value("Error: Username is already taken!")
                )
                .andReturn();
    }

    @Test
    void registerUser_emailAlreadyExist() throws Exception {

        RegisterRequest registerRequest = new RegisterRequest("test3", EMAIL, PASSWORD);

        String registerRequestAsString = objectMapper.writeValueAsString(registerRequest);

        mockMvc.perform(post("/api/auth/register")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(registerRequestAsString)
                        .characterEncoding("utf-8"))
                .andExpectAll(
                        status().is4xxClientError(),
                        MockMvcResultMatchers.jsonPath("$.message").value("Error: Email is already in use!")
                )
                .andReturn();
    }

    @Test
    void loginUser() throws Exception {
        LoginRequest loginRequest = new LoginRequest(USERNAME, PASSWORD);
        String loginRequestAsString = objectMapper.writeValueAsString(loginRequest);

        mockMvc.perform(post("/api/auth/login")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(loginRequestAsString)
                        .characterEncoding("utf-8"))
                .andExpectAll(
                        status().isOk(),
                        MockMvcResultMatchers.jsonPath("$.username").value("test")
                )
                .andReturn();
    }

    @Test
    void loginUser_notFound() throws Exception {
        LoginRequest loginRequest = new LoginRequest("random", "random");
        String loginRequestAsString = objectMapper.writeValueAsString(loginRequest);

        mockMvc.perform(post("/api/auth/login")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(loginRequestAsString)
                        .characterEncoding("utf-8"))
                .andExpectAll(
                        status().is4xxClientError(),
                        status().reason(containsString("Unauthorized"))
                )
                .andReturn();

    }
}