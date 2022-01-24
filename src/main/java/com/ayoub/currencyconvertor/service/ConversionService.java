package com.ayoub.currencyconvertor.service;

import com.ayoub.currencyconvertor.command.ConversionCommand;
import com.ayoub.currencyconvertor.dto.ConversionResultDTO;

public interface ConversionService {

    ConversionResultDTO convert(ConversionCommand command);
}
