/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.blazartech.products.qotdp.job.config;

import com.blazartech.products.qotdp.data.QuoteOfTheDay;
import java.time.LocalDate;
import java.util.LinkedHashMap;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.convert.converter.Converter;

/**
 *
 * @author scott
 */
public class LinkedMapToQuoteOfTheDayConverter implements Converter<LinkedHashMap, QuoteOfTheDay> {

    private static final Logger logger = LoggerFactory.getLogger(LinkedMapToQuoteOfTheDayConverter.class);
    
    @Override
    public QuoteOfTheDay convert(LinkedHashMap source) {
        logger.info("converting {}", source);
        int number = (int) source.get("number");
        int quoteNumber = (int) source.get("quoteNumber");
        List<Integer> runDateElements = (List<Integer>) source.get("runDate");
        LocalDate runDate = LocalDate.now().withYear(runDateElements.get(0)).withMonth(runDateElements.get(1)).withDayOfMonth(runDateElements.get(2));
        
        QuoteOfTheDay qotd = new QuoteOfTheDay();
        qotd.setNumber(number);
        qotd.setQuoteNumber(quoteNumber);
        qotd.setRunDate(runDate);
        
        return qotd;
    }
    
}
