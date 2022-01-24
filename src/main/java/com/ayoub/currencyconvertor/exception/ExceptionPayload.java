package com.ayoub.currencyconvertor.exception;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;

import java.time.LocalDateTime;

@Data
@Builder(toBuilder = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
@Getter
@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
public class ExceptionPayload {
    private String code;
    private Integer statusCode;
    private LocalDateTime timestamp;
    private String message;
    private String reference;

    @Override
    public String toString() {
        if (reference == null)
            return String.join(";", String.valueOf(code), message, timestamp.toString());
        return String.join(";", String.valueOf(code), message, timestamp.toString(), reference);
    }
}
