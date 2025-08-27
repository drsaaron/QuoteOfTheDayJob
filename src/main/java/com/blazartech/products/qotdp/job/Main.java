/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.blazartech.products.qotdp.job;

import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.batch.autoconfigure.BatchAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ImportResource;

/**
 *
 * @author scott
 */
@SpringBootApplication
@ImportResource("classpath:SpringXMLConfig.xml")
@ComponentScan(basePackages = {"com.blazartech"})
@EnableAutoConfiguration(exclude = BatchAutoConfiguration.class)
@EnableBatchProcessing
public class Main {
    
    public static void main(String... args) {
        SpringApplication.run(Main.class, args);
    }
}
