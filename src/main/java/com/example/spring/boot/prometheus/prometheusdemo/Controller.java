package com.example.spring.boot.prometheus.prometheusdemo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import io.prometheus.client.Counter;
import io.prometheus.client.Gauge;
import io.prometheus.client.Summary;

@RestController
public class Controller {

  private static final Logger LOG = LoggerFactory.getLogger(Controller.class);

  private static final String ERROR_PATH = "/error";

  static final Counter requests = Counter.build().name("hello_service_requests_total")
      .help("Total requests.").register();

  static final Gauge inprogressRequests = Gauge.build().name("hello_service_inprogress_requests")
      .help("Inprogress requests.").register();

  // @formatter:off
  static final Summary requestLatency = Summary.build()
      .quantile(0.5, 0.05)   // Add 50th percentile (= median) with 5% tolerated error
      .quantile(0.9, 0.01)   // Add 90th percentile with 1% tolerated error
      .name("requests_latency_seconds").help("Request latency in seconds.").register();
  // @formatter:on

  @RequestMapping(method = RequestMethod.GET)
  String get() {

    requests.inc();
    inprogressRequests.inc();
    Summary.Timer requestTimer = requestLatency.startTimer();

    LOG.info("received request {}", requests.get());

    String response = "hello " + requests.get();

    LOG.info("responding with {}", response);

    requestTimer.observeDuration();
    inprogressRequests.dec();

    return response;
  }

}
