package com.ibetar.context;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "ibetar.openapi")
@Getter
@Setter
public class OpenAPIProperties {
    private String devUrl;
    private String prodUrl;
    private String devDescription;
    private String prodDescription;
    private String contactEmail;
    private String contactName;
    private String contactUrl;
    private String licenseName;
    private String licenseUrl;
    private String apiTitle;
    private String apiVersion;
    private String apiDescription;
    private String termsOfService;
    private String apiSummary;
}