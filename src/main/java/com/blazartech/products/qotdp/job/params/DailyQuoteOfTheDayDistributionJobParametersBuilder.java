/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.blazartech.products.qotdp.job.params;

import com.blazartech.products.services.date.DateServices;
import com.nm.ffba.common.batch.IJobParametersBuilder;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 *
 * @author scott
 */
@Component("DailyQuoteOfTheDayDistributionJobParametersBuilder")
public class DailyQuoteOfTheDayDistributionJobParametersBuilder implements IJobParametersBuilder {

    private static final Logger logger = Logger.getLogger(DailyQuoteOfTheDayDistributionJobParametersBuilder.class);

    @Autowired
    private DateServices dateServices;

    @Override
    public Map<String, Object> buildJobParameters(String[] arguments) {
        String runDateString = arguments[2];
        Date runDate = dateServices.parseDate(runDateString);
        logger.info("runDate = " + dateServices.formatDate(runDate));
        
        Map<String, Object> parameters = new HashMap<>();
        parameters.put("runDate", runDate);

        return parameters;
    }
}
