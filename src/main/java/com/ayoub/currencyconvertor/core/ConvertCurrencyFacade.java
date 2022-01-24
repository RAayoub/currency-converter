package com.ayoub.currencyconvertor.core;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(value = "converter", url = "${core.url}")
public interface ConvertCurrencyFacade {

    @GetMapping(value = "latest")
    ConversionResponse getRates(@RequestParam("access_key") String accessKey, @RequestParam("symbols") String currencies);
}
