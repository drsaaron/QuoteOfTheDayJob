/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.blazartech.products.qotdp.job.distribute.impl.telegram;

import com.blazartech.products.qotdp.job.distribute.impl.CommonFormatTestConfiguration;
import com.blazartech.products.qotdp.process.AggregatedQuoteOfTheDay;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

/**
 *
 * @author scott
 */
@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = {
    FormatQuoteOfTheDayTelegramImplTest.FormatQuoteOfTheDayTelegramImplTestConfiguration.class,
    CommonFormatTestConfiguration.class
})
public class FormatQuoteOfTheDayTelegramImplTest {

    private static final Logger logger = LoggerFactory.getLogger(FormatQuoteOfTheDayTelegramImplTest.class);

    @Configuration
    static class FormatQuoteOfTheDayTelegramImplTestConfiguration {

        @Bean
        public FormatQuoteOfTheDayTelegramImpl getFormatQuoteOfTheDayTelegramImpl() {
            return new FormatQuoteOfTheDayTelegramImpl();
        }

    }

    @Autowired
    private FormatQuoteOfTheDayTelegramImpl formatter;

    @Autowired
    private AggregatedQuoteOfTheDay qotd;

    public FormatQuoteOfTheDayTelegramImplTest() {
    }

    @BeforeAll
    public static void setUpClass() {
    }

    @AfterAll
    public static void tearDownClass() {
    }

    @BeforeEach
    public void setUp() {
    }

    @AfterEach
    public void tearDown() {
    }

    /**
     * Test of formatQuoteOfTheDay method, of class
     * FormatQuoteOfTheDayTelegramImpl.
     */
    @Test
    public void testFormatQuoteOfTheDay() {
        logger.info("formatQuoteOfTheDay");

        String expResult = "Quote of the Day (2019-02-02)\n"
                + "\n"
                + "I am a test quote, hear me roar\n"
                + "But also use a second line\n"
                + "\n"
                + "Source: <em>I am a source code</em>";
        String result = formatter.formatQuoteOfTheDay(qotd);
        assertEquals(expResult, result);

    }

}
