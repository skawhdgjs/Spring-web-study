package com.study.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/aggregation")
public class AggregationController {

    // TODO : How about add Swagger API?
    @RequestMapping("/home")
    public String aggregationHome () {
        return "aggregation home";
    }
}