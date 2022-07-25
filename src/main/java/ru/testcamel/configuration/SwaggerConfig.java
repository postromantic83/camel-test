package ru.testcamel.configuration;

import org.apache.camel.builder.RouteBuilder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpHeaders;

/**
 * Конфигурация swagger
 */
@Configuration
//Конфигурируем первым делом, чтобы настроить restConfiguration
@Order(value = 0)
public class SwaggerConfig extends RouteBuilder {

    @Value("${camel.servlet.mapping.context-path}")
    private String camelContextPath;

    @Override
    public void configure() throws Exception {
        restConfiguration()
                .component("servlet")
                .port(8080)
                .host("localhost")
                .apiContextPath("/api-docs")
                .contextPath(camelContextPath.replaceAll("/\\*$", ""))
                .apiProperty("api.title", "{{api.title}}")
                .apiProperty("api.description", "{{api.description}}")
                .apiProperty("api.version", "{{app.version}}")
                .enableCORS(true)
                .corsHeaderProperty(HttpHeaders.ACCESS_CONTROL_ALLOW_HEADERS, "Content-type, Accept")
                .corsHeaderProperty(HttpHeaders.ACCESS_CONTROL_ALLOW_ORIGIN, "*")
                .corsHeaderProperty(HttpHeaders.ACCESS_CONTROL_ALLOW_METHODS, "GET, POST")
                .dataFormatProperty("prettyPrint", "true");
    }
}


