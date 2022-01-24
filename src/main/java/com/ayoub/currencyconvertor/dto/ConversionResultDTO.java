package com.ayoub.currencyconvertor.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Setter
@Getter
@Builder
@AllArgsConstructor
public class ConversionResultDTO {

    private BigDecimal amount;

}
