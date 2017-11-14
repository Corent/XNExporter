package com.xiangni.exporter.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Random;

@RestController
@RequestMapping("/metrics")
public class ExporterController {

    private static Random random = new Random();

    @RequestMapping(value = "", method = { RequestMethod.GET })
    public String metrics() {
        StringBuilder builder = new StringBuilder();
        builder.append("xiangni_de_yanzhi 100").append("\n").append("custom_random_value ").append(random.nextDouble());
        return builder.toString();
    }
}