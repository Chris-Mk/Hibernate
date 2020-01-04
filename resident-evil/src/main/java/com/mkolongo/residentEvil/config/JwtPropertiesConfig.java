package com.mkolongo.residentEvil.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.http.HttpHeaders;

@Getter
@Setter
@EnableConfigurationProperties(JwtPropertiesConfig.class)
@ConfigurationProperties(prefix = "application.jwt")
public class JwtPropertiesConfig {

    private String tokenPrefix;
    private String secretKey;
    private long tokenExpirationDays;

    public String authorizationHeader() {
        return HttpHeaders.AUTHORIZATION;
    }
}
