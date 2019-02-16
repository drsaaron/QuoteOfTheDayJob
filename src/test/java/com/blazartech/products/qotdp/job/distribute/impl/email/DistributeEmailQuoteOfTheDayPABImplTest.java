/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.blazartech.products.qotdp.job.distribute.impl.email;

import com.blazartech.products.mail.MailMessage;
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
import com.blazartech.products.mail.MessageMailer;
import java.util.Collection;
import javax.mail.MessagingException;

/**
 *
 * @author scott
 */
@RunWith(SpringRunner.class)
@ContextConfiguration(classes = {
    DistributeEmailQuoteOfTheDayPABImplTest.DistributeEmailQuoteOfTheDayPABImplTestConfiguration.class,
    CommonFormatTestConfiguration.class
})
public class DistributeEmailQuoteOfTheDayPABImplTest {
    
    private static final Logger logger = LoggerFactory.getLogger(DistributeEmailQuoteOfTheDayPABImplTest.class);
    
    static class DummyMessageMailer implements MessageMailer {

        @Override
        public void sendMessage(MailMessage message) throws MessagingException {
            throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        }
        
    }
    
    @Configuration
    static class DistributeEmailQuoteOfTheDayPABImplTestConfiguration {
        
        @Bean
        public DistributeEmailQuoteOfTheDayPABImpl getDistributeEmailQuoteOfTheDayPABImpl() {
            return new DistributeEmailQuoteOfTheDayPABImpl();
        }
        
        @Bean("emailFormatter")
        public FormatQuoteOfTheDayEmailImpl getFormatQuoteOfTheDayEmailImpl() {
            return new FormatQuoteOfTheDayEmailImpl();
        }
        
        @Bean
        public MessageMailer getMessageMailer() {
            return new DummyMessageMailer();
        }
    }
    
    @Autowired
    private DistributeEmailQuoteOfTheDayPABImpl distributor;
    
    @Autowired
    private AggregatedQuoteOfTheDay qotd;
    
    public DistributeEmailQuoteOfTheDayPABImplTest() {
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
     * Test of getName method, of class DistributeEmailQuoteOfTheDayPABImpl.
     */
    @Test
    public void testGetName() {
        logger.info("getName");
        
        String expResult = "email distributor";
        String result = distributor.getName();
        assertEquals(expResult, result);
    }

    /**
     * Test of getSubject method, of class DistributeEmailQuoteOfTheDayPABImpl.
     */
    @Test
    public void testGetSubject() {
        logger.info("getSubject");

        String expResult = "Quote of the Day (2019-02-02)";
        String result = distributor.getSubject(qotd);
        assertEquals(expResult, result);
    }    
    
    @Test
    public void testGetRecipients() {
        logger.info("getRecipients");
        
        String[] expectedSenders = { "me@email.com", "you@email.com", "other@email.com" };

        Collection<String> recipients = distributor.getRecipients();
        assertEquals(expectedSenders.length, recipients.size());
        
        int i = 0;
        for (String recipient : recipients) {
            assertEquals(expectedSenders[i++], recipient);
        }
    }
}
