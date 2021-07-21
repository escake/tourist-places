package com.touristplaces.touristplaces.service.auth;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.touristplaces.touristplaces.data.User;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;

import static io.jsonwebtoken.Jwts.parser;
import static org.apache.logging.log4j.util.Strings.EMPTY;
import static org.springframework.http.HttpHeaders.AUTHORIZATION;
import static org.springframework.util.ObjectUtils.isEmpty;

@Component
@RequiredArgsConstructor
public class JwtTokenUtil {

    private final JwtTokenProperties jwtTokenProperties;
    private final ObjectMapper objectMapper;

    private static final String BEARER_PREFIX = "Bearer ";

    @SneakyThrows // TODO handle exceptions
    public String create(Authentication authentication) {
        final Date validity = new Date(System.currentTimeMillis() + jwtTokenProperties.getValidityInMs());

        final User user = (User) authentication.getPrincipal();
        final String serializedUser = objectMapper.writeValueAsString(user);

        return Jwts.builder()
                .setSubject(serializedUser)
                .signWith(SignatureAlgorithm.HS512, jwtTokenProperties.getSecret())
                .setExpiration(validity)
                .compact();
    }

    @SneakyThrows
    public Authentication getAuthentication(String token) {
        final String serializedUser = Jwts.parser().setSigningKey(jwtTokenProperties.getSecret()).parseClaimsJws(token).getBody().getSubject();

        final User user = objectMapper.readValue(serializedUser, User.class);

        return new UsernamePasswordAuthenticationToken(
                user, token, user.getAuthorities());
    }

    public String getTokenFromHeaders(HttpServletRequest httpServletRequest) {
        final String header = httpServletRequest.getHeader(AUTHORIZATION);
        if (isEmpty(header) || !header.startsWith(BEARER_PREFIX)) {
            return EMPTY;
        }
        final String token = header.split(" ")[1].trim();
        if (!validate(token)) {
            return EMPTY;
        }
        return token;
    }

    private boolean validate(String token) {
        try {
            parser().setSigningKey(jwtTokenProperties.getSecret()).parseClaimsJws(token);
            return true;
        } catch (JwtException ex) {
            return false;
        }
    }
}
