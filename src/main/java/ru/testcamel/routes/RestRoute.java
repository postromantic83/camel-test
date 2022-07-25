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
        rest()
                .get("/info")
                .tag("info")
                .description("Получениие информации о приложении")
                .bindingMode(RestBindingMode.json)
                .consumes(MediaType.APPLICATION_JSON_VALUE)
                .produces(MediaType.APPLICATION_JSON_VALUE)
                .responseMessage()
                .code(HttpStatus.OK.value()).responseModel(Info.class)
                .endResponseMessage()
                .to("direct:info")

                .post("/pushTg")
                .tag("pushTg")
                .description("Отправка сообщения в telegram")
                .type(String.class)
                .to("direct:push");

                from("direct:push")
                    .convertBodyTo(String.class)
                    .to("telegram:bots?authorizationToken=" + "{{telegram.authtoken}}" +
                        "&chatId=" + "{{telegram.chatid}}");


        from("direct:info")
                .setHeader(HttpHeaders.CONTENT_TYPE, constant(MediaType.APPLICATION_JSON_VALUE))
                .setBody(constant(new Info(applicationVersion)))
                .marshal().json(true)
                .convertBodyTo(String.class);


    }
}
