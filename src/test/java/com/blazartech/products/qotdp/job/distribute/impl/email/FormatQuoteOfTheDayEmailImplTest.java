/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.blazartech.products.qotdp.job.distribute.impl.email;

import com.blazartech.products.qotdp.job.distribute.impl.CommonFormatTestConfiguration;
import com.blazartech.products.qotdp.process.AggregatedQuoteOfTheDay;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

/**
 *
 * @author scott
 */
@RunWith(SpringRunner.class)
@ContextConfiguration(classes = {
    FormatQuoteOfTheDayEmailImplTest.FormatQuoteOfTheDayEmailImplTestConfiguration.class,
    CommonFormatTestConfiguration.class
})
public class FormatQuoteOfTheDayEmailImplTest {
    
    private static final Logger logger = LoggerFactory.getLogger(FormatQuoteOfTheDayEmailImplTest.class);
    
    @Configuration
    static class FormatQuoteOfTheDayEmailImplTestConfiguration {
        
        @Bean
        public FormatQuoteOfTheDayEmailImpl getFormatQuoteOfTheDayEmailImpl() {
            return new FormatQuoteOfTheDayEmailImpl();
        }
    }
    
    @Autowired
    private FormatQuoteOfTheDayEmailImpl formatter;
    
    @Autowired
    private AggregatedQuoteOfTheDay qotd;

    public FormatQuoteOfTheDayEmailImplTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of formatQuoteOfTheDay method, of class FormatQuoteOfTheDayEmailImpl.
     */
    @Test
    public void testFormatQuoteOfTheDay() {
        logger.info("formatQuoteOfTheDay");

        String expResult = "I am a test quote, hear me roar<br>But also use a second line<br><br><font size=\"-1\">Source: <em>I am a source code</em></font>";
        String result = formatter.formatQuoteOfTheDay(qotd);
        assertEquals(expResult, result);
    }
    
}
