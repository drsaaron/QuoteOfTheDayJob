/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.blazartech.products.qotdp.job.task;

import com.blazartech.products.qotdp.data.QuoteOfTheDay;
import org.apache.log4j.Logger;
import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

/**
 *
 * @author scott
 */
@Component("createQuoteOfTheDayTasklet")
@Scope("step")
public class GetQuoteOfTheDayTasklet extends BaseTasklet implements Tasklet {

    private static final Logger logger = Logger.getLogger(GetQuoteOfTheDayTasklet.class);
    
    
    
    @Override
    public RepeatStatus execute(StepContribution sc, ChunkContext cc) throws Exception {
        logger.info("getting quote of the day for " + getRunDate());
        
        QuoteOfTheDay qotd = getQuoteOfTheDay();
        logger.info("found quote #" + qotd.getQuoteNumber());
        
        return RepeatStatus.FINISHED;
    }
    
}
