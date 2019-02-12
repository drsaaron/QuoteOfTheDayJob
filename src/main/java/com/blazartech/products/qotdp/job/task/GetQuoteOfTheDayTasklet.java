/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.blazartech.products.qotdp.job.task;

import com.blazartech.products.qotdp.data.QuoteOfTheDay;
import org.apache.log4j.Logger;
import org.springframework.batch.core.ExitStatus;
import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.StepExecution;
import org.springframework.batch.core.StepExecutionListener;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.item.ExecutionContext;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

/**
 *
 * @author scott
 */
@Component("createQuoteOfTheDayTasklet")
@Scope("step")
public class GetQuoteOfTheDayTasklet extends BaseTasklet implements Tasklet, StepExecutionListener {

    private static final Logger logger = Logger.getLogger(GetQuoteOfTheDayTasklet.class);
    
    private QuoteOfTheDay qotd;

    @Override
    public RepeatStatus execute(StepContribution sc, ChunkContext cc) throws Exception {
        logger.info("getting quote of the day for " + getRunDate());

        qotd = getQuoteOfTheDay();
        logger.info("found quote #" + qotd.getQuoteNumber());

        return RepeatStatus.FINISHED;
    }

    private StepExecution stepExecution;

    @Override
    public void beforeStep(StepExecution stepExecution) {
        logger.info("saving execution context");
        this.stepExecution = stepExecution;
    }
    
    @Override
    public ExitStatus afterStep(StepExecution e) {
        logger.info("saving QOTD to the execution context");
        ExecutionContext stepContext = stepExecution.getExecutionContext();
        stepContext.put("quoteOfTheDay", qotd);
        return stepExecution.getExitStatus();
    }
}
