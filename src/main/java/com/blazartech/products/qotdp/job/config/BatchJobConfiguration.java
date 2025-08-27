/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.blazartech.products.qotdp.job.config;

import com.blazartech.batch.IJobParametersBuilder;
import java.util.Map;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.job.Job;
import org.springframework.batch.core.job.builder.FlowBuilder;
import org.springframework.batch.core.job.builder.JobBuilder;
import org.springframework.batch.core.job.builder.SimpleJobBuilder;
import org.springframework.batch.core.job.flow.Flow;
import org.springframework.batch.core.job.parameters.JobParametersIncrementer;
import org.springframework.batch.core.listener.ExecutionContextPromotionListener;
import org.springframework.batch.core.repository.ExecutionContextSerializer;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.repository.dao.Jackson2ExecutionContextStringSerializer;
import org.springframework.batch.core.step.Step;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.task.TaskExecutor;
import org.springframework.transaction.PlatformTransactionManager;

/**
 *
 * @author scott
 */
@Configuration
@EnableBatchProcessing
public class BatchJobConfiguration {
    
    private static final String JOB_NAME = "dailyQuoteOfTheDayDistributionJob";
    
    @Autowired
    private Tasklet createQuoteOfTheDayTasklet;
    
    @Autowired
    private ExecutionContextPromotionListener quoteOfTheDayPromotionListener;
    
    @Autowired
    private JobRepository jobRepository;
    
    @Autowired
    private PlatformTransactionManager transactionManager;
    
    @Bean
    public Step getQuoteOfTheDayStep() {
        return new StepBuilder("dailyQuoteOfTheDayDistributionJob_getQuoteStep", jobRepository)
                .tasklet(createQuoteOfTheDayTasklet, transactionManager)
                .listener(quoteOfTheDayPromotionListener)
                .build();
    }
    
    private Step buildTaskletStep(String name, Tasklet tasklet) {
        return new StepBuilder(name, jobRepository)
                .tasklet(tasklet, transactionManager)
                .build();
    }
    
    @Bean
    public Step mailQuoteOfTheDayStep() {
        return buildTaskletStep("dailyQuoteOfTheDayDistributionJob_distributionSplit_mailStep", mailQuoteOfTheDayTasklet);
    }
    
    @Bean
    public Step postQuoteOfTheDayFacebookStep() {
        return buildTaskletStep("dailyQuoteOfTheDayDistributionJob_distributionSplit_facebookStep", postQuoteOfTheDayFacebookTasklet);
    }
    
    @Bean
    public Step postQuoteOfTheDayTelegramStep() {
        return buildTaskletStep("dailyQuoteOfTheDayDistributionJob_distributionSplit_telegramStep", postQuoteOfTheDayTelegramTasklet);
    }
    
    @Autowired
    private Tasklet mailQuoteOfTheDayTasklet;
    
    @Autowired
    private Tasklet postQuoteOfTheDayFacebookTasklet;
    
    @Autowired
    private Tasklet postQuoteOfTheDayTelegramTasklet;
    
    @Bean
    public Map<String, Job> batchJobMap(Job dailyQuoteOfTheDayDistributionJob) {
        return Map.of(JOB_NAME, dailyQuoteOfTheDayDistributionJob);
    }
    
    @Autowired
    private IJobParametersBuilder jobParameterBuilder;
    
    @Bean
    public Map<String, IJobParametersBuilder> batchJobParameterBuilderMap() {
        return Map.of(JOB_NAME, jobParameterBuilder);
    }
    
    @Bean
    public ExecutionContextSerializer jacksonSerializer() {
        return new Jackson2ExecutionContextStringSerializer();
    }
    
    /*
    <batch:job id="dailyQuoteOfTheDayDistributionJob" restartable="true">
        
        <!-- get the quote of the data in the database -->
        <batch:step id="dailyQuoteOfTheDayDistributionJob_getQuoteStep" parent="getQuoteOfTheDayStep" next="dailyQuoteOfTheDayDistributionJob_distributionSplit" />
        
        <!-- parallel processing for the various distributions -->
        <batch:split id="dailyQuoteOfTheDayDistributionJob_distributionSplit" next="" task-executor="taskExecutor">
            <!-- mail -->
            <batch:flow>                
                <batch:step id="dailyQuoteOfTheDayDistributionJob_distributionSplit_mailStep" parent="mailQuoteOfTheDayStep" />
            </batch:flow> 
                
            <!-- post to facebook -->
            <batch:flow>
                <batch:step id="dailyQuoteOfTheDayDistributionJob_distributionSplit_facebookStep" parent="postQuoteOfTheDayFacebookStep" />
            </batch:flow>
            
            <!-- send to telegram -->
            <batch:flow>
                <batch:step id="dailyQuoteOfTheDayDistributionJob_distributionSplit_telegramStep" parent="postQuoteOfTheDayTelegramStep" />
            </batch:flow>
        </batch:split>
    </batch:job>
    */
    
    @Autowired
    private JobParametersIncrementer jobParametersIncrementer;
    
    @Autowired
    private TaskExecutor taskExecutor;
    
    private Flow buildFlow(String flowName, Step flowStep) {
        Flow flow = new FlowBuilder<Flow>(flowName)
                .start(flowStep)
                .build();
        return flow;
    }
    
    @Bean
    public Job dailyQuoteOfTheDayDistributionJob() {
        SimpleJobBuilder jobBuilder = new JobBuilder(JOB_NAME, jobRepository)
                .incrementer(jobParametersIncrementer)
                .start(getQuoteOfTheDayStep());
                
        Flow mailFlow = buildFlow("mailFlow", mailQuoteOfTheDayStep());
        Flow facebookFlow = buildFlow("facebookFlow", postQuoteOfTheDayFacebookStep());
        Flow telegramFlow = buildFlow("telegramFlow", postQuoteOfTheDayTelegramStep());
        
        Flow distributeQuoteFlow = new FlowBuilder<Flow>("dailyQuoteOfTheDayDistributionJob_distributionSplit")
                .split(taskExecutor)
                .add(mailFlow, facebookFlow, telegramFlow)
                .build();
        
        Step distributeQuoteStep = new StepBuilder("dailyQuoteOfTheDayDistributionJob_distributionSplit", jobRepository)
                .flow(distributeQuoteFlow)
                .build();
        
        jobBuilder.next(distributeQuoteStep);
                
        return jobBuilder.build();
    }
}
