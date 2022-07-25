package ru.testcamel.processors;

import lombok.extern.slf4j.Slf4j;
import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.apache.camel.component.telegram.model.IncomingMessage;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class TelegramBotProcessor implements Processor {
    @Override
    public void process(Exchange exchange) throws Exception {
        IncomingMessage incomingMessage = exchange.getIn().getBody(IncomingMessage.class);
        String messageText = incomingMessage.getText();
        log.info(messageText);
        String userName = incomingMessage.getFrom().getUsername();
        if(userName == null){
            userName = incomingMessage.getFrom().getFirstName();
            if (userName == null) {
                userName = "друг";
            }
        }
        String answer = "Привет, " + userName + " от веселого верблюда Camel";
        exchange.getIn().setBody(answer);
    }
}
