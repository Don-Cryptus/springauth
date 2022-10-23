package com.springauth.controller;

import com.springauth.entity.User;
import com.springauth.repository.UserRepository;
import com.springauth.security.jwt.JwtUtils;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpHeaders;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.web.servlet.MockMvc;

import static com.springauth.testdata.UserTestData.*;
import static org.hamcrest.Matchers.containsString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@AutoConfigureMockMvc
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_CLASS)
class DashboardControllerTest {
    @Autowired
    PasswordEncoder encoder;

    @Autowired
    MockMvc mockMvc;
    @Autowired
    UserRepository userRepository;

    @Autowired
    JwtUtils jwtUtils;

    @BeforeAll
    public void setup() {
        userRepository.save(new User(USERNAME, EMAIL, encoder.encode(PASSWORD)));
    }

    @Test
    void userAccess() throws Exception {
        String token = jwtUtils.generateTokenFromUsername(USERNAME);

        mockMvc.perform(get("/api/dashboard/user")
                        .header(HttpHeaders.AUTHORIZATION, "Bearer " + token))
                .andExpectAll(
                        status().isOk(),
                        content().string("User Content.")
                )
                .andReturn();
    }

    @Test
    void userAccess_noToken() throws Exception {
        var result = mockMvc.perform(get("/api/dashboard/user"))
                .andExpectAll(
                        status().is4xxClientError(),
                        status().reason(containsString("Unauthorized"))
                )
                .andReturn();

        System.out.println(result.getResponse().getErrorMessage());
    }
}