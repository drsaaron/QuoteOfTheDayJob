/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.blazartech.products.qotdp.job.config;

import org.springframework.batch.core.listener.ExecutionContextPromotionListener;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 *
 * @author scott
 */
@Configuration
public class QuoteOfTheDayPromotionListenerConfiguration {
    
    /**
     * provide a promotion listener that will promote the quote of the
     * day from the step execution context to the job execution context.
     * 
     * @return 
     */
    @Bean
    public ExecutionContextPromotionListener quoteOfTheDayPromotionListener() {
        ExecutionContextPromotionListener listener = new ExecutionContextPromotionListener();
        listener.setKeys(new String[] {"quoteOfTheDay"});
        return listener;
    }
}
