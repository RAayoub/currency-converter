package com.ayoub.currencyconvertor;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class CurrencyConvertorApplication {

    public static void main(String[] args) {
        SpringApplication.run(CurrencyConvertorApplication.class, args);
    }

}
