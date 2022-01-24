package com.ayoub.currencyconvertor.exception;

public class TechnicalException extends RuntimeException {

    private ExceptionPayload exceptionPayload;

    public TechnicalException(Throwable cause) {
        super(cause);
    }

    public TechnicalException(ExceptionPayload exceptionPayload) {
        super(exceptionPayload.getMessage());
        this.exceptionPayload = exceptionPayload;
    }

    public TechnicalException(String s) {
        super(s);
    }
}
