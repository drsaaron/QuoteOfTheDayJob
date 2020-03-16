/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.blazartech.products.qotdp.job.task;

import com.blazartech.products.qotdp.job.distribute.DistributeQuoteOfTheDayPAB;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

/**
 *
 * @author scott
 */
@Component("mailQuoteOfTheDayTasklet")
@Scope("step")
public class MailQuoteOfTheDayTasklet extends DistributeQuoteOfTheDayBaseTasklet {

    private static final Logger logger = LoggerFactory.getLogger(MailQuoteOfTheDayTasklet.class);
    
    @Autowired
    @Qualifier("emailPoster")
    private DistributeQuoteOfTheDayPAB distributor;

    @Override
    protected DistributeQuoteOfTheDayPAB getDistributor() {
        return distributor;
    }
    
    
}
