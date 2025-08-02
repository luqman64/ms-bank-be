package com.luqman.bank.be.config;

import com.luqman.bank.be.quartz.BatchJob;
import org.quartz.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class QuartzConfig {

    @Value("${spring.quartz.expression}")
    private String cronExpression;

    @Bean
    public JobDetail batchJobDetail() {
        return JobBuilder.newJob(BatchJob.class)
                .withIdentity("batchJob")
                .storeDurably()
                .build();
    }

    @Bean
    public Trigger batchJobTrigger(JobDetail batchJobDetail) {
        return TriggerBuilder.newTrigger()
                .forJob(batchJobDetail)
                .withIdentity("batchTrigger")
                .withSchedule(CronScheduleBuilder.cronSchedule(cronExpression))
                .build();
    }

}
