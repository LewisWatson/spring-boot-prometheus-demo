# Spring Boot Prometheus Demo
Demonstrate how to collect and expose prometheus metrics in Spring Boot.

## Build

```bash
mvn clean package
```

## Run

```bash
mvn spring-boot:run
```

Open http://localhost:8081/prometheus to see the metrics.

Load http://localhost:8080 a few times to generate some metrics as specified in `com.example.spring.boot.prometheus.prometheusdemo.Controller`