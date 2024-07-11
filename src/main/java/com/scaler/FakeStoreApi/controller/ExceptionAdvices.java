package com.scaler.FakeStoreApi.controller;

import com.scaler.FakeStoreApi.exceptions.NotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ExceptionAdvices {

    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<String> naman(Exception exception) {
        return new ResponseEntity<>("Phat gaya", HttpStatus.OK);
    }
}