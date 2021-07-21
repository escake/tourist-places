package com.touristplaces.touristplaces.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

import static lombok.AccessLevel.PRIVATE;

@NoArgsConstructor(access = PRIVATE)
public class ExceptionDto {
    @Data
    public static class ExceptionRes {
        @NonNull String message;
    }
}
