package com.vsnitko.pet.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * @author v.snitko
 * @since 2022.10.15
 */
@Getter
@Setter
@Configuration
@ConfigurationProperties("cors")
public class CorsConfig {

    private String allowedOrigin;
    private String allowedMethod;
    private String allowedHeader;
}
