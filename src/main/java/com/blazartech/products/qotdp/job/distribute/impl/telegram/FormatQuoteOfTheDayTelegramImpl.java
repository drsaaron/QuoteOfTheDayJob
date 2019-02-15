/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.blazartech.products.qotdp.job.distribute.impl.telegram;

import com.blazartech.products.qotdp.job.distribute.FormatQuoteOfTheDayPAB;
import com.blazartech.products.qotdp.process.AggregatedQuoteOfTheDay;
import com.blazartech.products.services.date.DateServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 *
 * @author scott
 */
@Component("telegramFormatter")
public class FormatQuoteOfTheDayTelegramImpl implements FormatQuoteOfTheDayPAB {

    @Autowired
    private DateServices ds;
    
    @Override
    public String formatQuoteOfTheDay(AggregatedQuoteOfTheDay qotd) {
        String quoteText = qotd.getQuote().getText().replaceAll("<", "&lt;").replaceAll(">", "&gt;");
        String messageText = "Quote of the Day (" + ds.formatDate(qotd.getQuoteOfTheDay().getRunDate()) + ")\n\n" +  quoteText + "\n\n" + "Source: <em>" + qotd.getSourceCode().getText() + "</em>";
        return messageText;
    }
    
}
