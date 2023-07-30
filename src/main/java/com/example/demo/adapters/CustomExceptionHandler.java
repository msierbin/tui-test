package com.example.demo.adapters;

import com.example.demo.domain.model.NotFoundException;
import com.example.springreactiveopenapicodegen.dto.ErrorDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.server.NotAcceptableStatusException;

@ControllerAdvice
@Slf4j
class CustomExceptionHandler {

    @ExceptionHandler(NotFoundException.class)
    ResponseEntity<ErrorDTO> handle(NotFoundException e) {
        return buildErrorDto(HttpStatus.NOT_FOUND, e.getMessage());
    }

    @ExceptionHandler(NotAcceptableStatusException.class)
    ResponseEntity<ErrorDTO> handle(NotAcceptableStatusException e) {
        return buildErrorDto(HttpStatus.NOT_ACCEPTABLE, "Unsupported header type");
    }

    private static ResponseEntity<ErrorDTO> buildErrorDto(HttpStatus status, String message) {
        return ResponseEntity.status(status)
                .contentType(MediaType.APPLICATION_JSON)
                .body(ErrorDTO.builder()
                        .status(status.value())
                        .message(message)
                        .build());
    }

}
