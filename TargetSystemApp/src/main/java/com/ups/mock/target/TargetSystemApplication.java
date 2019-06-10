package com.ups.mock.target;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

import io.jaegertracing.Configuration;
import io.jaegertracing.Configuration.ReporterConfiguration;
import io.jaegertracing.Configuration.SamplerConfiguration;
import io.jaegertracing.internal.samplers.ConstSampler;
import io.opentracing.Tracer;

@SpringBootApplication
public class TargetSystemApplication {

	@Bean
	public RestTemplate restTemplate(RestTemplateBuilder restTemplateBuilder) {
		return restTemplateBuilder.build();
	}
	
	@Bean
	public io.opentracing.Tracer jaegerTracer() {
		Tracer tracer = null;
		String serviceName="target-system";
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
			SpringApplication.run(TargetSystemApplication.class, args);
	}
}
