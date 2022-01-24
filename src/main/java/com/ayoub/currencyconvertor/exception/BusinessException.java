package com.ayoub.currencyconvertor.exception;

import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;

public class BusinessException extends RuntimeException {
    private ExceptionPayload exceptionPayload;

    public BusinessException(ExceptionPayload exceptionPayload) {
        super(exceptionPayload.getMessage());
        this.exceptionPayload = exceptionPayload;
    }

    public BusinessException(final String code, final String message) {
        super(message);
        this.exceptionPayload = ExceptionPayload.builder()
                .message(message)
                .code(code)
                .statusCode(HttpStatus.BAD_REQUEST.value())
                .timestamp(LocalDateTime.now())
                .build();
    }

    public ExceptionPayload getExceptionPayload() {
        return exceptionPayload;
    }
}
