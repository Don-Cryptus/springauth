package com.springauth.configuration;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Configuration
@Getter
public class TokenConfiguration {

    @Value("${jwtRefreshExpirationMs}")
    private Long refreshTokenDurationMs;

}
