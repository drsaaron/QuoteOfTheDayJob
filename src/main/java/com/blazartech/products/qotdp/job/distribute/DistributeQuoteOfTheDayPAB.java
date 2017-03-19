/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.blazartech.products.qotdp.job.distribute;

import com.blazartech.products.qotdp.process.AggregatedQuoteOfTheDay;

/**
 * interface for a component that will distribute the quote of the day somewhere
 * @author scott
 */
public interface DistributeQuoteOfTheDayPAB {
    
    public String getName();
    
    public void distributeQuoteOfTheDay(AggregatedQuoteOfTheDay qotd);
}
