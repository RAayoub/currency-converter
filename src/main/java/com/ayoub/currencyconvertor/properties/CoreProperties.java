package com.ayoub.currencyconvertor.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;
import javax.validation.constraints.NotNull;

@Data
@Component
@ConfigurationProperties(prefix = "core")
@Validated
public class CoreProperties {

    @NotNull
    private String accessKey;
}
