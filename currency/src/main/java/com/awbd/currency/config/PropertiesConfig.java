package com.awbd.currency.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties("currency")
@Getter
@Setter
public class PropertiesConfig {
    private String version;
    private String value;
}
