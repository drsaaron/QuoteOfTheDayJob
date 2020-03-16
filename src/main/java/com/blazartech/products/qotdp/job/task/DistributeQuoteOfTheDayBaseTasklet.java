/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.blazartech.products.qotdp.job.task;

import com.blazartech.products.qotdp.data.QuoteOfTheDay;
import com.blazartech.products.qotdp.job.distribute.DistributeQuoteOfTheDayPAB;
import com.blazartech.products.qotdp.process.AggregatedQuoteOfTheDay;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.beans.factory.annotation.Value;

/**
 *
 * @author scott
 */
abstract public class DistributeQuoteOfTheDayBaseTasklet extends BaseTasklet implements Tasklet {

    private static final Logger logger = LoggerFactory.getLogger(DistributeQuoteOfTheDayBaseTasklet.class);

    abstract protected DistributeQuoteOfTheDayPAB getDistributor();

    @Value("#{jobExecutionContext['quoteOfTheDay']}")
    private QuoteOfTheDay qotd;

    @Override
    public RepeatStatus execute(StepContribution sc, ChunkContext cc) throws Exception {
        logger.info("posting quote of the day");

        logger.info("found quote " + qotd.getQuoteNumber() + " for " + qotd.getRunDate());

        AggregatedQuoteOfTheDay aggregatedQuote = getGetQuoteOfTheDayPAB().getAggregatedQuoteOfTheDay(qotd);

        logger.info("distributing....");
        getDistributor().distributeQuoteOfTheDay(aggregatedQuote);

        return RepeatStatus.FINISHED;
    }
}
