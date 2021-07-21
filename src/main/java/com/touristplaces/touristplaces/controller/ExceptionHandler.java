package com.touristplaces.touristplaces.controller;

import com.touristplaces.touristplaces.dto.ExceptionDto.ExceptionRes;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;

@ControllerAdvice
public class ExceptionHandler {

    @org.springframework.web.bind.annotation.ExceptionHandler({Exception.class, RuntimeException.class})
    public ResponseEntity<ExceptionRes> handle(Exception ex) {
        final ExceptionRes exceptionMessage = new ExceptionRes(ex.getMessage());
        return new ResponseEntity<>(
                exceptionMessage,
                HttpStatus.BAD_REQUEST // TODO remove hardcoded status code and pass err as param
        );
    }
}
