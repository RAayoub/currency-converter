package com.ayoub.currencyconvertor.exception;

public class BusinessException extends RuntimeException {
    private ExceptionPayload exceptionPayload;

    public BusinessException(ExceptionPayload exceptionPayload) {
        super(exceptionPayload.getMessage());
        this.exceptionPayload = exceptionPayload;
    }

    public ExceptionPayload getExceptionPayload() {
        return exceptionPayload;
    }
}
