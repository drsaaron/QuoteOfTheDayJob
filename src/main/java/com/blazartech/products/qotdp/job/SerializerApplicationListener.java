/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.blazartech.products.qotdp.job;

import com.blazartech.products.qotdp.data.QuoteOfTheDay;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.Date;
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
    
    @Autowired
    private Jackson2ExecutionContextStringSerializer serializer;
    
    @EventListener
    public void onApplicationEvent(ContextRefreshedEvent event) {
        // allow the quote of the day object to be serialized ot the context
        ObjectMapper mapper = new ObjectMapper();
        mapper.addMixIn(QuoteOfTheDay.class, Object.class);
        serializer.setObjectMapper(mapper);
    }
}
