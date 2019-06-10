package com.ups.css.connector.sink.routes;

import lombok.extern.slf4j.Slf4j;
import org.apache.camel.LoggingLevel;
import org.apache.camel.builder.RouteBuilder;
import org.json.JSONObject;
import org.json.XML;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class JmsQueueToTRESTRouter extends RouteBuilder {
	
	private final int printFormatStyle = 4;

    @Override
    public void configure() throws Exception {
        System.out.println("Configuring route");

        from("{{input.queue}}")
		        .log(LoggingLevel.DEBUG, log, "New message received")
		        .to("http://localhost:8091/process?message=hello")
                .log(LoggingLevel.DEBUG, log, "Message sent to Hello Rest Service")
        .end();

    }
}
