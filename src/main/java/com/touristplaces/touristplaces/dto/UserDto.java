package com.touristplaces.touristplaces.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

import static lombok.AccessLevel.PRIVATE;

@NoArgsConstructor(access = PRIVATE)
public class UserDto {
    @Data
    public static class AuthReq {
        @NonNull String username;
        @NonNull String password;
    }

    @Data
    public static class LoginRes {
        @NonNull String token;
    }
}
