package com.ayoub.currencyconvertor.api;

import com.ayoub.currencyconvertor.command.ConversionCommand;
import com.ayoub.currencyconvertor.constants.ResourcePaths;
import com.ayoub.currencyconvertor.dto.ConversionResultDTO;
import com.ayoub.currencyconvertor.service.ConversionService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = ResourcePaths.CONVERT)
@AllArgsConstructor
public class ConversionResource {

    private final ConversionService conversionService;


    @PostMapping
    public ResponseEntity<ConversionResultDTO> convert(@RequestBody ConversionCommand command) {
        return ResponseEntity.ok(conversionService.convert(command));
    }
}
