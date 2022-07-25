package ru.testcamel.routes;

import lombok.extern.slf4j.Slf4j;
import org.apache.camel.builder.RouteBuilder;
import org.springframework.stereotype.Component;
import ru.testcamel.processors.ItCampProcessor;
import ru.testcamel.processors.TelegramBotProcessor;

@Slf4j
@Component
public class TelegramRoute extends RouteBuilder {
    private TelegramBotProcessor telegramBotProcessor;
    private ItCampProcessor itCampProcessor;

    public TelegramRoute(TelegramBotProcessor telegramBotProcessor, ItCampProcessor itCampProcessor) {
        this.telegramBotProcessor = telegramBotProcessor;
        this.itCampProcessor = itCampProcessor;
    }

    @Override
    public void configure() throws Exception {

        from("telegram:bots?authorizationToken=" + "{{telegram.authtoken}}")
                .routeId("telegram_route")
                .to("log:INFO?showHeaders=true")
                .choice()
                    .when().simple("${body} == 'IT_CAMP'")
                        .process(itCampProcessor)
                    .otherwise()
                        .process(telegramBotProcessor)
                .end()

                .to("telegram:bots?authorizationToken=" + "{{telegram.authtoken}}" +
                        "&chatId=" + "{{telegram.chatid}}");
    }
}
