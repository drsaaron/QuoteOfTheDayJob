/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.blazartech.products.qotdp.job.task;

import com.blazartech.products.qotdp.data.QuoteOfTheDay;
import com.blazartech.products.qotdp.process.GetQuoteOfTheDayPAB;
import java.util.Date;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;

/**
 *
 * @author scott
 */
public abstract class BaseTasklet {
    
    private static final Logger logger = Logger.getLogger(BaseTasklet.class);
    
    @Autowired
    private GetQuoteOfTheDayPAB getQuoteOfTheDayPAB;
    
    @Value("#{jobParameters['runDate']}")
    private Date runDate;
    
    public Date getRunDate() {
        return runDate;
    }
    
    public QuoteOfTheDay getQuoteOfTheDay() {
        logger.info("getting quote of the day for " + runDate);
        
        return getGetQuoteOfTheDayPAB().getQuoteOfTheDay(runDate);
    }
    
    public GetQuoteOfTheDayPAB getGetQuoteOfTheDayPAB() {
        return getQuoteOfTheDayPAB;
    }
    
}
