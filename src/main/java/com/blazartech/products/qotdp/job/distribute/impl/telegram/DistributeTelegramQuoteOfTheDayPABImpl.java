/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.blazartech.products.qotdp.job.distribute.impl.telegram;

import com.blazartech.products.qotdp.job.distribute.DistributeQuoteOfTheDayPAB;
import com.blazartech.products.qotdp.job.distribute.FormatQuoteOfTheDayPAB;
import com.blazartech.products.qotdp.process.AggregatedQuoteOfTheDay;
import com.blazartech.products.telegram.client.BTTelegramClient;
import com.blazartech.products.telegram.client.Message;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 *
 * @author scott
 */
@Component("telegramPoster")
public class DistributeTelegramQuoteOfTheDayPABImpl implements DistributeQuoteOfTheDayPAB {
    
    private static final Logger logger = Logger.getLogger(DistributeTelegramQuoteOfTheDayPABImpl.class);
    
    @Autowired
    private BTTelegramClient client;
    
    @Autowired
    @Qualifier("telegramFormatter")
    private FormatQuoteOfTheDayPAB formatter;
    
    @Value("${blazartech.qotd.job.telegram.chatID}")
    private String chatID;

    @Override
    public String getName() {
        return "telegram";
    }

    @Override
    public void distributeQuoteOfTheDay(AggregatedQuoteOfTheDay qotd) {
        logger.info("posting message to telegram " + chatID);
        
        Message message = new Message();

        String text = formatter.formatQuoteOfTheDay(qotd);
        message.setText(text);
        message.setHtml(true);
        message.setChatID(chatID);

        client.sendMessage(message);
    }
}
