package com.construe.waterflowcalc;

import com.construe.waterflowcalc.model.Pipe;
import com.construe.waterflowcalc.service.PipeServiceImpl;
import org.jboss.jandex.Main;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.ArrayList;
import java.util.List;


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
