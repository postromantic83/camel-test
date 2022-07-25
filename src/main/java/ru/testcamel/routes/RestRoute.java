package ru.testcamel.routes;

import lombok.extern.slf4j.Slf4j;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.model.rest.RestBindingMode;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class RestRoute extends RouteBuilder {

    @Value("${app.version}")
    private String applicationVersion;

    @Override
    public void configure() throws Exception {
        rest().tag("info")
                .get("/info")
                .bindingMode(RestBindingMode.json)
                .consumes(MediaType.APPLICATION_JSON_VALUE)
                .produces(MediaType.APPLICATION_JSON_VALUE)
                .responseMessage()
                .code(HttpStatus.OK.value()).responseModel(Info.class)
                .endResponseMessage()
                .to("direct:info");

        from("direct:info")
                .setHeader(HttpHeaders.CONTENT_TYPE, constant(MediaType.APPLICATION_JSON_VALUE))
                .setBody(constant(new Info(applicationVersion)))
                .convertBodyTo(String.class);
    }
}
