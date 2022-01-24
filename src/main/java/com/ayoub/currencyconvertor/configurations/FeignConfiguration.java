package com.ayoub.currencyconvertor.configurations;

import feign.RequestInterceptor;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Bean;

@AllArgsConstructor
public class FeignConfiguration {


    @Bean
        public RequestInterceptor requestInterceptor() {
        return requestTemplate -> requestTemplate.query("access_key", "5fe71138a78591bb883e570b65834ec6");
    }
}
