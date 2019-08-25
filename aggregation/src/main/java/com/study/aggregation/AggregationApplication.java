package com.study.aggregation;

import com.study.controller.AggregationController;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackageClasses = AggregationController.class)
public class AggregationApplication {

    public static void main(String[] args) {
        SpringApplication.run(AggregationApplication.class, args);
    }

}
