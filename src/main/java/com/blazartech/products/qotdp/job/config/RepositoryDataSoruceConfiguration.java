/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.blazartech.products.qotdp.job.config;

import com.blazartech.products.crypto.BlazarCryptoFile;
import javax.sql.DataSource;
import org.apache.commons.dbcp2.BasicDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 *
 * @author scott
 */
//@Configuration
public class RepositoryDataSoruceConfiguration {
  /*  
    @Autowired
    private BlazarCryptoFile cryptoFile;
    
    @Value("${blazartech.batch.repository.ds.userId}")
    private String userID;
    
    @Value("${blazartech.batch.repository.ds.resourceId}")
    private String resourceID;
    
    @Value("${blazartech.batch.repository.ds.url}")
    private String url;
    
    @Value("${blazartech.batch.repository.ds.driverClass}")
    private String driverClass;

    @Bean("JobRepositoryDataSource")
    public DataSource getJobRepositoryDataSource() {
        BasicDataSource ds = new BasicDataSource();
        ds.setUsername(userID);
        ds.setPassword(cryptoFile.getPassword(userID, resourceID));
        ds.setUrl(url);
        ds.setDriverClassName(driverClass);
        ds.setInitialSize(3);
        ds.setMaxTotal(5);
        return ds;
    }*/
}
