package com.ayoub.currencyconvertor.exception;


import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;

public enum ExceptionPayloadFactory {

    UNKNOWN_ERROR("00", "unknown.error", HttpStatus.BAD_REQUEST.value()),
    SOURCE_CURRENCY_NOT_FOUND("01", "source currency not found", HttpStatus.NOT_FOUND.value()),
    TARGET_CURRENCY_NOT_FOUND("02", "target currency not found", HttpStatus.NOT_FOUND.value()),
    SOURCE_CURRENCY_REQUIRED("03", "source currency is required", HttpStatus.NOT_FOUND.value()),
    TARGET_CURRENCY_REQUIRED("04", "target currency is required", HttpStatus.NOT_FOUND.value()),
    AMOUNT_REQUIRED("05", "amount is required", HttpStatus.NOT_FOUND.value()),


    RESPONSE_BODY_MISSING("06", "response body is missing", HttpStatus.BAD_REQUEST.value()),
    UNHANDLED_ERROR("07", "unhandled error", HttpStatus.BAD_REQUEST.value()),
   ;

    private ExceptionPayload exceptionPayload;

    ExceptionPayloadFactory(final String code, final String message, final Integer statusCode) {
        this.exceptionPayload = ExceptionPayload.builder()
                .code(code)
                .message(message)
                .statusCode(statusCode)
                .build();
    }

    public ExceptionPayload get() {
        return exceptionPayload.toBuilder()
                .timestamp(LocalDateTime.now())
                .build();
    }
}
