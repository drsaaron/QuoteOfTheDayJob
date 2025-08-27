/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.blazartech.products.qotdp.job;

import com.blazartech.products.qotdp.data.QuoteOfTheDay;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.repository.dao.Jackson2ExecutionContextStringSerializer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

/**
 *
 * @author scott
 */
@Component
public class SerializerApplicationListener {
    
    private static final Logger logger = LoggerFactory.getLogger(SerializerApplicationListener.class);
    
    @Autowired
    private Jackson2ExecutionContextStringSerializer batchDefaultSerializer;
    
    @EventListener
    public void onApplicationEvent(ContextRefreshedEvent event) {
        logger.info("onApplicationEvent: {}", event);
                
        // allow the quote of the day object to be serialized ot the context
        ObjectMapper mapper = new ObjectMapper();
        mapper.registerModule(new JavaTimeModule());
        mapper.addMixIn(QuoteOfTheDay.class, Object.class);
        batchDefaultSerializer.setObjectMapper(mapper);
    }
}
