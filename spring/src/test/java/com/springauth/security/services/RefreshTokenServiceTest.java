package com.springauth.security.services;

import com.springauth.configuration.TokenConfiguration;
import com.springauth.entity.RefreshToken;
import com.springauth.entity.User;
import com.springauth.payload.exception.TokenRefreshException;
import com.springauth.repository.RefreshTokenRepository;
import com.springauth.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.mockito.AdditionalAnswers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.Instant;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.UUID;

import static com.springauth.testdata.UserTestData.*;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@SpringBootTest
@AutoConfigureMockMvc
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@Slf4j
class RefreshTokenServiceTest {

    @Mock
    private UserRepository userRepository;

    @Mock
    private TokenConfiguration tokenConfiguration;

    @Mock
    private RefreshTokenRepository refreshTokenRepository;

    @InjectMocks
    private RefreshTokenService refreshTokenService;

    @BeforeAll
    public void init() {
        User user = new User(1L, USERNAME, EMAIL, PASSWORD);
        when(userRepository.findById(1L)).thenReturn(Optional.of(user));
        when(refreshTokenRepository.save(any())).then(AdditionalAnswers.returnsFirstArg());
    }

    @Test
    void createRefreshToken() {
        RefreshToken refreshToken = refreshTokenService.createRefreshToken(1L);
        assertTrue(refreshToken.getToken().length() > 0);
    }

    @Test
    void createRefreshToken_noUserFound() {
        NoSuchElementException thrown = assertThrows(
                NoSuchElementException.class,
                () -> refreshTokenService.createRefreshToken(2L)
        );

        assertTrue(thrown.getMessage().contains("No value present"));
    }


    @Test
    void verifyExpiration_validToken() {
        RefreshToken refreshToken = new RefreshToken();
        refreshToken.setUser(userRepository.findById(1L).get());
        refreshToken.setExpiryDate(Instant.now().plusMillis(1000000));
        refreshToken.setToken(UUID.randomUUID().toString());

        assertTrue(refreshTokenService.verifyExpiration(refreshToken).getToken().length() > 0);
    }

    @Test
    void verifyExpiration_expiredToken() {
        RefreshToken refreshToken = new RefreshToken();
        refreshToken.setUser(userRepository.findById(1L).get());
        refreshToken.setExpiryDate(Instant.now().minusMillis(1000));
        refreshToken.setToken(UUID.randomUUID().toString());

        TokenRefreshException thrown = assertThrows(
                TokenRefreshException.class,
                () -> refreshTokenService.verifyExpiration(refreshToken),
                "Expected not expired token to throw, but it didn't"
        );

        assertTrue(thrown.getMessage().contains("Refresh token was expired. Please make a new signin request"));
    }
}