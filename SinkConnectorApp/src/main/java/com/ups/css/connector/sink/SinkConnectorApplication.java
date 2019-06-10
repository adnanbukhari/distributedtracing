package com.ups.css.connector.sink;

import org.apache.camel.opentracing.starter.CamelOpenTracing;
import org.springframework.boot.SpringApplication;
import org.springframework.jms.annotation.EnableJms;

import io.jaegertracing.Configuration;
import io.jaegertracing.Configuration.ReporterConfiguration;
import io.jaegertracing.Configuration.SamplerConfiguration;

import io.jaegertracing.internal.samplers.ConstSampler;
import io.opentracing.Tracer;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
@EnableJms
@CamelOpenTracing
public class SinkConnectorApplication {
	@Bean
	public io.opentracing.Tracer jaegerTracer() {
		Tracer tracer = null;
		String serviceName="sink-connector-app";
		SamplerConfiguration samplerConfig = SamplerConfiguration.fromEnv()
                .withType(ConstSampler.TYPE)
                .withParam(1);

        ReporterConfiguration reporterConfig = ReporterConfiguration.fromEnv()
                .withLogSpans(true);

        Configuration config = new Configuration(serviceName)
                .withSampler(samplerConfig)
                .withReporter(reporterConfig);

        tracer = config.getTracer();
		return tracer;
	}
	
	public static void main(String[] args) {
		SpringApplication.run(SinkConnectorApplication.class, args);
	}

}
