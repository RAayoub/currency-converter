package com.ayoub.currencyconvertor.exception;

import com.ayoub.currencyconvertor.core.ConversionResponse;
import com.ayoub.currencyconvertor.util.Utils;
import com.fasterxml.jackson.databind.ObjectMapper;
import feign.Response;
import feign.codec.ErrorDecoder;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

import java.io.InputStream;

@Slf4j
@Primary
@Component
@RequiredArgsConstructor
public class CurrencyConverterErrorHandler implements ErrorDecoder {

    @SneakyThrows
    @Override
    public Exception decode(String s, Response response) {
        if (response.body() == null) {
            return new TechnicalException("Response Body is null");
        }

        InputStream inputStream = response.body().asInputStream();
        ObjectMapper mapper = new ObjectMapper();
        ConversionResponse conversionResponse = mapper.readValue(inputStream, ConversionResponse.class);
        if (conversionResponse.getError() != null) {
            log.error("Error calling the api {}" , Utils.toJson(conversionResponse.getError()));
            return new BusinessException(ExceptionPayloadFactory.UNKNOWN_ERROR.get());
        }
        return new TechnicalException("Unhandled Exception");
    }
}
