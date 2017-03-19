/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.blazartech.products.qotdp.job.distribute.impl.email;

import com.blazartech.products.qotdp.job.distribute.FormatQuoteOfTheDayPAB;
import com.blazartech.products.qotdp.process.AggregatedQuoteOfTheDay;
import org.springframework.stereotype.Component;

/**
 *
 * @author scott
 */
@Component("emailFormatter")
public class FormatQuoteOfTheDayEmailImpl implements FormatQuoteOfTheDayPAB {

    @Override
    public String formatQuoteOfTheDay(AggregatedQuoteOfTheDay qotd) {
        String messageText = qotd.getQuote().getText().replaceAll("\n", "<br>") + "<br><br><font size=\"-1\">Source: <em>" + qotd.getSourceCode().getText() + "</em></font>";
        return messageText;
    }
    
}
