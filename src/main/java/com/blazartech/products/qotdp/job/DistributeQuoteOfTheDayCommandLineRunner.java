/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.blazartech.products.qotdp.job;

import com.blazartech.batch.IJobManager;
import com.blazartech.batch.IJobParametersBuilder;
import com.blazartech.batch.JobStatus;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

/**
 *
 * @author scott
 */
@Component
public class DistributeQuoteOfTheDayCommandLineRunner implements CommandLineRunner {

    private static final Logger logger = LoggerFactory.getLogger(DistributeQuoteOfTheDayCommandLineRunner.class);
    
    @Autowired
    private IJobManager jobManager;
    
    @Autowired
    private IJobParametersBuilder parametersBuilder;
    
    private static final String jobName = "dailyQuoteOfTheDayDistributionJob";
    
    @Override
    public void run(String... args) throws Exception {
        logger.info("starting job " + jobName);
        
        JobStatus status = jobManager.runJob(jobName, args, parametersBuilder);
        
        if (status == JobStatus.Success) {
            logger.info("job completed successfully");
        } else {
            logger.info("job failed");
            System.exit(1);
        }
    }
    
}
