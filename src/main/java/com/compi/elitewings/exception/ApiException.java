package com.compi.elitewings.exception;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
@Builder
public class ApiException {
    private final String message;
    private final HttpStatus httpStatus;
}
