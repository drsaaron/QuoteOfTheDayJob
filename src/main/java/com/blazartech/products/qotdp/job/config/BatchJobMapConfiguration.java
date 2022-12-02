/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.blazartech.products.qotdp.job.config;

import com.blazartech.batch.IJobParametersBuilder;
import java.util.Map;
import org.springframework.batch.core.Job;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 *
 * @author scott
 */
@Configuration
public class BatchJobMapConfiguration {
   
    @Autowired
    private Job dailyQuoteOfTheDayDistributionJob;
    
    private static final String JOB_NAME = "dailyQuoteOfTheDayDistributionJob";
    
    @Bean
    public Map<String, Job> batchJobMap() {
        return Map.of(JOB_NAME, dailyQuoteOfTheDayDistributionJob);
    }
    
    @Autowired
    private IJobParametersBuilder jobParameterBuilder;
    
    @Bean
    public Map<String, IJobParametersBuilder> batchJobParameterBuilderMap() {
        return Map.of(JOB_NAME, jobParameterBuilder);
    }
}
