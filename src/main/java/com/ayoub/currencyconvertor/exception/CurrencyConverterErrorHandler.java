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
            return new TechnicalException(ExceptionPayloadFactory.RESPONSE_BODY_MISSING.get());
        }

        InputStream inputStream = response.body().asInputStream();
        ObjectMapper mapper = new ObjectMapper();
        ConversionResponse conversionResponse = mapper.readValue(inputStream, ConversionResponse.class);
        ConversionResponse.Error errorResponse = conversionResponse.getError();
        if (errorResponse != null) {
            log.error("Error calling the api {}" , Utils.toJson(errorResponse));
            return new BusinessException(errorResponse.getCode(), errorResponse.getMessage());
        }
        return new TechnicalException(ExceptionPayloadFactory.UNHANDLED_ERROR.get());
    }
}
