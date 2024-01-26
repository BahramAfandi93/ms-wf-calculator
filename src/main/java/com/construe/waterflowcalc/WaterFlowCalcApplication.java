package com.construe.waterflowcalc;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@SpringBootApplication
public class WaterFlowCalcApplication implements CommandLineRunner {

    public static void main(String[] args) {
        SpringApplication.run(WaterFlowCalcApplication.class, args);
    }

    public void run(String... args) throws Exception {
//        double dimater = 2.5;
//        double slope = 2.5;
//        int percent = 70;
//
//        System.out.println("FLOW AREA = " + PipeServiceImpl.getFlowArea(dimater, percent));
    }
}
