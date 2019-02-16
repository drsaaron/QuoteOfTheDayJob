/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.blazartech.products.qotdp.job.distribute.impl;

import com.blazartech.products.qotdp.data.Quote;
import com.blazartech.products.qotdp.data.QuoteOfTheDay;
import com.blazartech.products.qotdp.data.QuoteSourceCode;
import com.blazartech.products.qotdp.process.AggregatedQuoteOfTheDay;
import com.blazartech.products.services.date.DateServices;
import com.blazartech.products.services.date.impl.DateServicesImpl;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

/**
 *
 * @author scott
 */
@Configuration
@PropertySource("classpath:test.properties")
public class CommonFormatTestConfiguration {
    
    @Bean
    public DateServices getDateServices() {
        return new DateServicesImpl();
    }
    
    @Value("${dateServices.date.format}")
    private String dateFormat;
    
    @Bean
    DateFormat getDateFormat() {
        return new SimpleDateFormat(dateFormat);
    }
    
    @Value("${qotd.runDate}")
    private String runDate;
    
    @Value("${qotd.quoteNum}")
    private int quoteNum;
    
    @Value("${qotd.quoteText}")
    private String quoteText;
    
    @Value("${qotd.sourceCode}")
    private int sourceCode;
    
    @Value("${qotd.sourceCodeText}")
    private String sourceCodeText;
    
    @Bean
    public AggregatedQuoteOfTheDay getAggregatedQuoteOfTheDay() {
        AggregatedQuoteOfTheDay a = new AggregatedQuoteOfTheDay();
        
        Quote q = new Quote();
        q.setSourceCode(sourceCode);
        q.setText(quoteText);
        q.setUsable(true);
        
        QuoteOfTheDay qotd = new QuoteOfTheDay();
        qotd.setNumber(-1);
        qotd.setQuoteNumber(quoteNum);
        qotd.setRunDate(getDateServices().parseDate(runDate));
        
        QuoteSourceCode sc = new QuoteSourceCode();
        sc.setNumber(sourceCode);
        sc.setText(sourceCodeText);
        
        a.setSourceCode(sc);
        a.setQuote(q);
        a.setQuoteOfTheDay(qotd);
        
        return a;
    }
}
