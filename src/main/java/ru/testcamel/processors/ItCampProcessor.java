package ru.testcamel.processors;

import lombok.extern.slf4j.Slf4j;
import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class ItCampProcessor implements Processor {
    @Override
    public void process(Exchange exchange) throws Exception {
        String answer = "Приветствуем участников IT Camp 2022!";
        exchange.getIn().setBody(answer);
    }
}
