package com.ayoub.currencyconvertor.exception;


import lombok.extern.slf4j.Slf4j;
import org.apache.catalina.connector.ClientAbortException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.UUID;


@ControllerAdvice
@Slf4j
@RestController
public class ExceptionAdvice {

    @ExceptionHandler({BusinessException.class})
    public ResponseEntity<ExceptionPayload> handle(final BusinessException throwable) {
        final ExceptionPayload exceptionPayload = throwable.getExceptionPayload();
        log.error("Businesss Exception at: ", throwable);
        return ResponseEntity.status(exceptionPayload.getStatusCode()).body(exceptionPayload);
    }

    @ExceptionHandler({Throwable.class})
    public ResponseEntity<ExceptionPayload> handle(final Throwable throwable) {
        final String guid = UUID.randomUUID().toString();
        final ExceptionPayload exceptionPayload = ExceptionPayloadFactory.UNKNOWN_ERROR.get().toBuilder().reference(guid).build();
        log.error(String.format("error occurred with reference %s ", guid), throwable);
        return ResponseEntity.status(exceptionPayload.getStatusCode()).body(exceptionPayload);
    }
}
