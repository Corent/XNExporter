package com.xiangni.exporter.controller;

import io.prometheus.client.Counter;
import io.prometheus.client.Gauge;
import io.prometheus.client.Histogram;
import io.prometheus.client.Summary;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.Random;

@RestController
@RequestMapping("/metrics")
public class ExporterController {

    private static Random random = new Random();

    /*private static final Counter counter = Counter.build().name("requests_total_new").help("Total requests.").register();
    private static final Gauge gauge = Gauge.build().name("inprogress_requests_new").help("Inprogress requests.").register();
    private static final Summary summaryBytes = Summary.build().name("requests_size_bytes_new").help("Request size in bytes.").register();
    private static final Summary summaryLatency = Summary.build().name("requests_latency_seconds_new").help("Request latency in seconds.").register();
    private static final Histogram histogram = Histogram.build().name("requests_latency_seconds_new").help("Request latency in seconds.").register();*/

    @RequestMapping(value = "", method = { RequestMethod.GET })
    public String metrics() {
        /*Summary.Timer summaryTimer = summaryLatency.startTimer();
        Histogram.Timer histogramTimer = histogram.startTimer();
        counter.inc();
        if (random.nextInt(10) < 5) gauge.inc();
        else gauge.dec();
        summaryBytes.observe(random.nextInt(100));*/

        StringBuilder builder = new StringBuilder();
        builder.append("xiangni_de_yanzhi 100").append("\n").append("custom_random_value ").append(random.nextDouble()).append("\n");
        /*summaryTimer.observeDuration();
        histogramTimer.observeDuration();*/

        return builder.toString();
    }
}