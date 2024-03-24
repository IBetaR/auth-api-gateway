package com.ibetar.context;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import io.swagger.v3.oas.models.servers.Server;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.PropertySource;

import java.util.List;

@Configuration
@RequiredArgsConstructor
//@PropertySource("classpath:config.json")
public class OpenAPIConfig {

    private final OpenAPIProperties openAPIProperties;

    @Value("${ibetar.openapi.dev-url}")
    private String devUrl;

    @Value("${ibetar.openapi.prod-url}")
    private String prodUrl;

    @Value("${ibetar.openapi.dev-description}")
    private String devDescription;

    @Value("${ibetar.openapi.prod-description}")
    private String prodDescription;

    @Value("${ibetar.openapi.contact-email}")
    private String contactEmail;

    @Value("${ibetar.openapi.contact-name}")
    private String contactName;

    @Value("${ibetar.openapi.contact-url}")
    private String contactUrl;

    @Value("${ibetar.openapi.license-name}")
    private String licenseName;

    @Value("${ibetar.openapi.license-url}")
    private String licenseUrl;

    @Value("${ibetar.openapi.api-title}")
    private String apiTitle;

    @Value("${ibetar.openapi.api-version}")
    private String apiVersion;

    @Value("${ibetar.openapi.api-description}")
    private String apiDescription;

    @Value("${ibetar.openapi.terms-of-service}")
    private String termsOfService;

    @Value("${ibetar.openapi.api-summary}")
    private String apiSummary;

    @Bean
    @Primary
    public OpenAPI myOpenAPI() {
        SecurityScheme securityScheme = new SecurityScheme()
                .type(SecurityScheme.Type.HTTP)
                .scheme("bearer")
                .bearerFormat("JWT")
                .in(SecurityScheme.In.HEADER)
                .name("Authorization");

        SecurityRequirement securityItem = new SecurityRequirement().addList("bearerAuth");

        Server devServer = new Server();
        devServer.setUrl(devUrl);
        devServer.setDescription(devDescription);

        Server prodServer = new Server();
        prodServer.setUrl(prodUrl);
        prodServer.setDescription(prodDescription);

        Contact contact = new Contact();
        contact.setEmail(contactEmail);
        contact.setName(contactName);
        contact.setUrl(contactUrl);

        License mitLicense = new License().name(licenseName).url(licenseUrl);

        Info info = new Info()
                .title(apiTitle)
                .version(apiVersion)
                .contact(contact)
                .description(apiDescription)
                .termsOfService(termsOfService)
                .summary(apiSummary)
                .license(mitLicense);

        return new OpenAPI().info(info)
                .addSecurityItem(securityItem)
                .components(new Components().addSecuritySchemes("bearerAuth", securityScheme))
                .servers(List.of(devServer, prodServer));
    }

}