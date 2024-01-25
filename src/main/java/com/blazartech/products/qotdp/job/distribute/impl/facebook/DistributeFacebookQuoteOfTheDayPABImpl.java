/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.blazartech.products.qotdp.job.distribute.impl.facebook;

import com.blazartech.products.fbclient.BTFacebookClient;
import com.blazartech.products.fbclient.Status;
import com.blazartech.products.qotdp.job.distribute.DistributeQuoteOfTheDayPAB;
import com.blazartech.products.qotdp.job.distribute.FormatQuoteOfTheDayPAB;
import com.blazartech.products.qotdp.process.AggregatedQuoteOfTheDay;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 *
 * @author scott
 */
@Component("facebookPoster")
public class DistributeFacebookQuoteOfTheDayPABImpl implements DistributeQuoteOfTheDayPAB {

    private static final Logger logger = LoggerFactory.getLogger(DistributeFacebookQuoteOfTheDayPABImpl.class);
    
    @Value("${qotd.job.doDistribute}")
    private boolean doDistribute;
    
    @Autowired
    private BTFacebookClient facebookClient;
    
    @Autowired
    @Qualifier("facebookFormatter")
    private FormatQuoteOfTheDayPAB formatPAB;
    
    @Override
    public String getName() {
        return "facebook";
    }

    @Override
    public void distributeQuoteOfTheDay(AggregatedQuoteOfTheDay qotd) {
        Status newNote = new Status();

        String text = formatPAB.formatQuoteOfTheDay(qotd);
        newNote.setText(text);

        if (doDistribute) {
            facebookClient.postStatus(newNote);
        } else {
            logger.info("not distributing {}", newNote);
//            throw new RuntimeException("I shall fail you");
        }
    }
    
}
