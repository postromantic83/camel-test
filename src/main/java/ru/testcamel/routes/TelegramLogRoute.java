//package ru.gazpromneft.routes;
//
//import lombok.extern.slf4j.Slf4j;
//import org.apache.camel.builder.RouteBuilder;
//import org.springframework.stereotype.Component;
//
//@Slf4j
//@Component
//public class TelegramLogRoute extends RouteBuilder {
//
//    @Override
//    public void configure() {
//
//        from("telegram:bots?authorizationToken=" + "{{telegram.authtoken}}")
//                .routeId("simple_tg_route")
//                .to("log:INFO?showHeaders=true");
//
//    }
//}
