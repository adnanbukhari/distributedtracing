package com.ups.css.connector.source.routes;

import lombok.extern.slf4j.Slf4j;
import org.apache.camel.LoggingLevel;
import org.apache.camel.builder.RouteBuilder;
import org.json.JSONObject;
import org.json.XML;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class JmsQueueToTopicRouter extends RouteBuilder {
	
	private final int printFormatStyle = 4;

    @Override
    public void configure() throws Exception {
        System.out.println("Configuring route");

        from("{{input.queue}}")
                .log(LoggingLevel.DEBUG, log, "New message received")
                .process(exchange -> {
                    String XmlResponse = (String) exchange.getIn().getBody();
                    JSONObject jsonObject = XML.toJSONObject(XmlResponse);
                    String convertedMessage = jsonObject.toString(printFormatStyle);                   
                    exchange.getMessage().setBody(convertedMessage);
                })
                .to("{{output.topic}}")
                .log(LoggingLevel.DEBUG, log, "Message sent to the other queue")
        .end();

    }   
}
