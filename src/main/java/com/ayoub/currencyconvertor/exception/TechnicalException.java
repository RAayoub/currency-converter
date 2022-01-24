package com.ayoub.currencyconvertor.exception;

public class TechnicalException extends RuntimeException {

    public TechnicalException(Throwable cause) {
        super(cause);
    }

    public TechnicalException(String s) {
        super(s);
    }
}
