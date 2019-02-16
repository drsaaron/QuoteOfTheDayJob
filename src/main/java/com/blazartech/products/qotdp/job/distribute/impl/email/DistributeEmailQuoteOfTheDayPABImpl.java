/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.blazartech.products.qotdp.job.distribute.impl.email;

import com.blazartech.products.mail.MailMessage;
import com.blazartech.products.mail.MessageMailer;
import com.blazartech.products.qotdp.job.distribute.DistributeQuoteOfTheDayPAB;
import com.blazartech.products.qotdp.job.distribute.FormatQuoteOfTheDayPAB;
import com.blazartech.products.qotdp.process.AggregatedQuoteOfTheDay;
import com.blazartech.products.services.date.DateServices;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import javax.mail.MessagingException;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 *
 * @author scott
 */
@Component("emailPoster")
public class DistributeEmailQuoteOfTheDayPABImpl implements DistributeQuoteOfTheDayPAB {
    
    private static final Logger logger = Logger.getLogger(DistributeEmailQuoteOfTheDayPABImpl.class);
    
    @Autowired
    private MessageMailer mailer;
    
    @Autowired
    private DateServices ds;
    
    @Autowired
    @Qualifier("emailFormatter")
    private FormatQuoteOfTheDayPAB formatter;
    
    @Value("${com.blazartech.products.qotdp.job.distribute.email.recipients}")
    private String recipientList;
    
    public Collection<String> getRecipients() {
        List<String> recipients = new ArrayList<>();
        recipients.addAll(Arrays.asList(recipientList.split(", *")));
        return recipients;
    }
    
    @Override
    public String getName() {
        return "email distributor";
    }
    
    public String getSubject(AggregatedQuoteOfTheDay qotd) {
        return "Quote of the Day (" + ds.formatDate(qotd.getQuoteOfTheDay().getRunDate()) + ")";
    }

    @Override
    public void distributeQuoteOfTheDay(AggregatedQuoteOfTheDay qotd) {
        logger.info("emailing quote of the day");
        
        // format the quote
        String formattedQuote = formatter.formatQuoteOfTheDay(qotd);
        
        // mail
        MailMessage message = new MailMessage();
        getRecipients().forEach((r) -> {
            message.addRecipient(r);
        });
        message.setSubject(getSubject(qotd));
        message.setText(formattedQuote);
        try {
            mailer.sendMessage(message);
        } catch(MessagingException e) {
            throw new RuntimeException("error sending quote: " + e.getMessage(), e);
        }
    }
    
    
}
