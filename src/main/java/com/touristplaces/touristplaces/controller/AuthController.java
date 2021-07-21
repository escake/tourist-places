package com.touristplaces.touristplaces.controller;

import com.touristplaces.touristplaces.dto.UserDto.AuthReq;
import com.touristplaces.touristplaces.dto.UserDto.LoginRes;
import com.touristplaces.touristplaces.service.auth.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {
    private final AuthService authService;

    @PostMapping("/login")
    public LoginRes login(@RequestBody AuthReq req) {
        return authService.authenticate(req);
    }

    @PostMapping("/register")
    public void register(@RequestBody AuthReq req) {
        authService.create(req);
    }
}
