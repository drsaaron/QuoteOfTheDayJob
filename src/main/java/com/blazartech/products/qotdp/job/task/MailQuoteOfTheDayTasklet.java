/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.blazartech.products.qotdp.job.task;

import com.blazartech.products.qotdp.data.QuoteOfTheDay;
import com.blazartech.products.qotdp.job.distribute.DistributeQuoteOfTheDayPAB;
import com.blazartech.products.qotdp.process.AggregatedQuoteOfTheDay;
import org.apache.log4j.Logger;
import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

/**
 *
 * @author scott
 */
@Component("mailQuoteOfTheDayTasklet")
@Scope("step")
public class MailQuoteOfTheDayTasklet extends DistributeQuoteOfTheDayBaseTasklet {

    private static final Logger logger = Logger.getLogger(MailQuoteOfTheDayTasklet.class);
    
    @Autowired
    @Qualifier("emailPoster")
    private DistributeQuoteOfTheDayPAB distributor;

    @Override
    protected DistributeQuoteOfTheDayPAB getDistributor() {
        return distributor;
    }
    
    
}
