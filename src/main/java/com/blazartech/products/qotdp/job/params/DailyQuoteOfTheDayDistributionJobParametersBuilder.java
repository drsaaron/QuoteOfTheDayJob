/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.blazartech.products.qotdp.job.params;

import com.nm.ffba.common.batch.IJobParametersBuilder;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;

/**
 *
 * @author scott
 */
@Component("DailyQuoteOfTheDayDistributionJobParametersBuilder")
public class DailyQuoteOfTheDayDistributionJobParametersBuilder implements IJobParametersBuilder {
    
    private static final Logger logger = Logger.getLogger(DailyQuoteOfTheDayDistributionJobParametersBuilder.class);

    @Override
    public Map<String, Object> buildJobParameters(String[] arguments) {
        try {
            String runDateString = arguments[2];
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            Date runDate = sdf.parse(runDateString);
            
            Map<String, Object> parameters = new HashMap<>();
            parameters.put("runDate", runDate);
            
            return parameters;
        } catch (ParseException e) {
            logger.error("error building arguments: " + e.getMessage());
            throw new RuntimeException("error building arguments: " + e.getMessage());
        }
    }
}
