package com.xiangni.exporter.collector;

import io.prometheus.client.Collector;
import io.prometheus.client.GaugeMetricFamily;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class XNCollector extends Collector {

    private static Random random = new Random();

    public List<MetricFamilySamples> collect() {
        List<MetricFamilySamples> mfs = new ArrayList<MetricFamilySamples>();

        mfs.add(new GaugeMetricFamily("xiangni_gauge", "help", random.nextInt(100)));// With no labels.

        GaugeMetricFamily labeledGauge = new GaugeMetricFamily("xiangni_other_gauge", "help", Arrays.asList("labelname"));// With labels
        labeledGauge.addMetric(Arrays.asList("foo"), random.nextInt(10));
        labeledGauge.addMetric(Arrays.asList("bar"), random.nextInt(10));
        mfs.add(labeledGauge);
        return mfs;
    }
}
