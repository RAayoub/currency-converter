package com.ayoub.currencyconvertor.core;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Map;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ConversionResponse {

    private Boolean success;
    private String base;
    private LocalDate date;
    private Map<String, BigDecimal> rates;
    private Error error;

    @Getter
    @Setter
    @AllArgsConstructor
    @NoArgsConstructor
    public class Error {
        private String code;
        private String message;
    }

}
