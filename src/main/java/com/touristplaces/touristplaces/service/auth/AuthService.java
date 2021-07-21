package com.touristplaces.touristplaces.service.auth;

import com.touristplaces.touristplaces.data.User;
import com.touristplaces.touristplaces.dto.UserDto.AuthReq;
import com.touristplaces.touristplaces.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import static com.touristplaces.touristplaces.dto.UserDto.LoginRes;
import static org.apache.logging.log4j.util.Strings.EMPTY;

@Service
@RequiredArgsConstructor
public class AuthService {
    private final AuthenticationManager authenticationManager;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtTokenUtil jwtTokenUtil;

    @Transactional
    public void create(AuthReq req) {
        if (userRepository.findByUsername(req.getUsername()).isPresent()) {
            throw new RuntimeException("Username taken.");
        }

        final String encryptedPassword = passwordEncoder.encode(req.getPassword());
        final User user = new User(req.getUsername(), encryptedPassword);

        userRepository.save(user);
    }

    @Transactional
    public LoginRes authenticate(AuthReq req) {
        String token = EMPTY;
        try {
            final Authentication authentication = authenticationManager.authenticate(this.createAuthenticationToken(req));
            token = jwtTokenUtil.create(authentication);
        } catch (AuthenticationException ex) {
            throw new RuntimeException("Wrong username or password.");
        }
        return new LoginRes(token);
    }

    private Authentication createAuthenticationToken(AuthReq req) {
        return new UsernamePasswordAuthenticationToken(
                req.getUsername(), req.getPassword());
    }
}
