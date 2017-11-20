package com.xiangni.exporter.config;

import com.codahale.metrics.MetricRegistry;
import io.prometheus.client.CollectorRegistry;
import io.prometheus.client.Counter;
import io.prometheus.client.dropwizard.DropwizardExports;
import io.prometheus.client.exporter.PushGateway;
import io.prometheus.client.hotspot.DefaultExports;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

@Component
public class PrometheusConfig {

    @Value("${spring.application.name}")
    private String applicationName;

    @Value("${prometheus.pushgateway.host}")
    private String pushHost;

    @Value("${prometheus.pushgateway.intervalInMillis:10000}")
    private long intervalInMillis;

    private final CollectorRegistry prometheusRegistry = new CollectorRegistry();

    @PostConstruct
    public void initialize() {

        DefaultExports.initialize();
        MetricRegistry dropwizardRegistry = new MetricRegistry();
        Counter counter = Counter.build().name("xiangni_counter").help("Total requests.").register(prometheusRegistry);
        DropwizardExports prometheus = new DropwizardExports(dropwizardRegistry);
        prometheus.register(prometheusRegistry);

        PushGateway prometheusPush = new PushGateway(pushHost);

        Executors.newSingleThreadScheduledExecutor().scheduleAtFixedRate(() -> {
            try {
                counter.inc();
                prometheus.collect();
                prometheusPush.push(prometheusRegistry, applicationName);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }, 5000, intervalInMillis, TimeUnit.MILLISECONDS);
    }
}
