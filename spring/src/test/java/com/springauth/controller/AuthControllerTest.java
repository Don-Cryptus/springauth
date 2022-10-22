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

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class AuthControllerTest {

    private final String username = "test";
    private final String password = "testtest";
    private final String email = "test@test.test";
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
        User user = new User(username, email, encoder.encode(password));
        userRepository.save(user);
    }

    @Test
    void registerUser() throws Exception {

        RegisterRequest registerRequest = new RegisterRequest("test1", "test1@test.test", password);
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
    void registerUser_alreadyExist() throws Exception {

        RegisterRequest registerRequest = new RegisterRequest(email, password, password);
        String registerRequestAsString = objectMapper.writeValueAsString(registerRequest);

        var result = mockMvc.perform(post("/api/auth/register")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(registerRequestAsString)
                        .characterEncoding("utf-8"))
                .andExpectAll(
                        status().isOk(),
                        MockMvcResultMatchers.jsonPath("$.message").value("User registered successfully!")
                )
                .andReturn();

        System.out.println(111111);
        System.out.println(result.getResponse().getContentAsString());
        System.out.println(111111);
    }

    @Test
    void loginUser() throws Exception {
        LoginRequest loginRequest = new LoginRequest(username, password);
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
}