package com.touristplaces.touristplaces.service.auth;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Getter
@Setter
@ConfigurationProperties(prefix = "jwt")
@Component
public class JwtTokenProperties {
    private String secret;
    private long validityInMs = 3600000;
}
