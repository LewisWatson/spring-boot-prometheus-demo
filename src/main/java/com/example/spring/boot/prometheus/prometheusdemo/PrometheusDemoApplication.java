package com.example.spring.boot.prometheus.prometheusdemo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import io.prometheus.client.spring.boot.EnablePrometheusEndpoint;
import io.prometheus.client.spring.boot.EnableSpringBootMetricsCollector;

@SpringBootApplication
@EnablePrometheusEndpoint
@EnableSpringBootMetricsCollector
public class PrometheusDemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(PrometheusDemoApplication.class, args);
	}
}
