/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.blazartech.products.qotdp.job.distribute.impl.facebook;

import com.blazartech.products.qotdp.job.distribute.FormatQuoteOfTheDayPAB;
import com.blazartech.products.qotdp.process.AggregatedQuoteOfTheDay;
import com.blazartech.products.services.date.DateServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 *
 * @author scott
 */
@Component("facebookFormatter")
public class FormatQuoteOfTheDayFacebookImpl implements FormatQuoteOfTheDayPAB {

    @Autowired
    private DateServices dateServices;
    
    @Override
    public String formatQuoteOfTheDay(AggregatedQuoteOfTheDay qotd) {
        String messageText = "Quote of the Day (" + dateServices.formatDate(qotd.getQuoteOfTheDay().getRunDate()) + ")\n\n" +  qotd.getQuote().getText() + "\n\n" + "Source: " + qotd.getSourceCode().getText();
        return messageText;
    }
    
}
