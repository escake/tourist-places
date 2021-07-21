package com.touristplaces.touristplaces.service.auth;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static org.springframework.security.core.context.SecurityContextHolder.getContext;
import static org.springframework.util.ObjectUtils.isEmpty;

@Component
@RequiredArgsConstructor
public class JwtTokenFilter extends OncePerRequestFilter {

    private final JwtTokenUtil jwtTokenUtil;

    @Override
    protected void doFilterInternal(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, FilterChain filterChain) throws ServletException, IOException {

        final String token = jwtTokenUtil.getTokenFromHeaders(httpServletRequest);
        if (!isEmpty(token)) {
            this.setAuthenticationFromJwtToken(token);
        }

        filterChain.doFilter(httpServletRequest, httpServletResponse);
    }

    private void setAuthenticationFromJwtToken(String token) {
        final Authentication authentication = jwtTokenUtil.getAuthentication(token);

        getContext().setAuthentication(authentication);
    }
}
