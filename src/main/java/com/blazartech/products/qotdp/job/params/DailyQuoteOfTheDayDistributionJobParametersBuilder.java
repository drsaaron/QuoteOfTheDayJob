/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.blazartech.products.qotdp.job.params;

import com.blazartech.batch.IJobParametersBuilder;
import com.blazartech.products.services.date.DateServices;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 *
 * @author scott
 */
@Component("DailyQuoteOfTheDayDistributionJobParametersBuilder")
public class DailyQuoteOfTheDayDistributionJobParametersBuilder implements IJobParametersBuilder {

    private static final Logger logger = LoggerFactory.getLogger(DailyQuoteOfTheDayDistributionJobParametersBuilder.class);

    @Autowired
    private DateServices dateServices;

    @Override
    public Map<String, Object> buildJobParameters(String[] arguments) {
        String runDateString = arguments[0];
        Date runDate = dateServices.parseDate(runDateString);
        logger.info("runDate = " + dateServices.formatDate(runDate));
        
        Map<String, Object> parameters = new HashMap<>();
        parameters.put("runDate", runDate);

        return parameters;
    }
}
