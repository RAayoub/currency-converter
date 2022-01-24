package com.ayoub.currencyconvertor.service;

import com.ayoub.currencyconvertor.exception.BusinessException;
import com.ayoub.currencyconvertor.exception.ExceptionPayload;
import com.ayoub.currencyconvertor.exception.ExceptionPayloadFactory;
import com.ayoub.currencyconvertor.properties.CoreProperties;
import com.ayoub.currencyconvertor.util.Assert;
import com.ayoub.currencyconvertor.util.Utils;
import com.ayoub.currencyconvertor.command.ConversionCommand;
import com.ayoub.currencyconvertor.core.ConversionResponse;
import com.ayoub.currencyconvertor.core.ConvertCurrencyFacade;
import com.ayoub.currencyconvertor.dto.ConversionResultDTO;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.Arrays;
import java.util.stream.Collectors;

@Slf4j
@Service
@AllArgsConstructor
public class ConversionServiceImp implements ConversionService {

    private final ConvertCurrencyFacade convertCurrencyFacade;
    private final CoreProperties coreProperties;

    @Override
    public ConversionResultDTO convert(ConversionCommand command) {
        log.info("Start converting with command {}", Utils.toJson(command));
        LocalDateTime startExecution = LocalDateTime.now();

        validate(command);

        String sourceCurrency = command.getSourceCurrency();
        String targetCurrency = command.getTargetCurrency();

        String currencies = joinCurrencies(sourceCurrency, targetCurrency);

        ConversionResponse response = convertCurrencyFacade.getRates(coreProperties.getAccessKey(), currencies);

        BigDecimal sourceCurrencyAmount = response.getRates().get(command.getSourceCurrency());
        BigDecimal targetCurrencyAmount = response.getRates().get(command.getTargetCurrency());

        log.info("Currency converter api responded successfully {}", Utils.toJson(response));

        if (sourceCurrencyAmount == null) {
            log.error("Source currency not provided");
            throw new BusinessException(ExceptionPayloadFactory.SOURCE_CURRENCY_NOT_FOUND.get());
        }
        if (targetCurrencyAmount == null) {
            log.error("Target currency not provided");
            throw new BusinessException(ExceptionPayloadFactory.TARGET_CURRENCY_NOT_FOUND.get());
        }

        Double rate = sourceCurrencyAmount.doubleValue() / targetCurrencyAmount.doubleValue();
        BigDecimal finalAmount = command.getAmount().multiply(BigDecimal.valueOf(rate)).setScale(2, RoundingMode.CEILING);
        ConversionResultDTO result =  ConversionResultDTO.builder()
                .amount(finalAmount)
                .build();
        Long executionTime = ChronoUnit.MILLIS.between(startExecution, LocalDateTime.now());
        log.info("Conversion process done successfully with result {} and execution time {} millis", Utils.toJson(result), executionTime);

        return result;
    }

    private String joinCurrencies(String... inputs){
        return Arrays.stream(inputs).collect(Collectors.joining(","));
    }

    private void validate(ConversionCommand command) {
        Assert.assertNotNull(command.getSourceCurrency(), ExceptionPayloadFactory.SOURCE_CURRENCY_REQUIRED.get());
        Assert.assertNotNull(command.getTargetCurrency(), ExceptionPayloadFactory.TARGET_CURRENCY_REQUIRED.get());
        Assert.assertNotNull(command.getAmount(), ExceptionPayloadFactory.AMOUNT_REQUIRED.get());
    }
}
