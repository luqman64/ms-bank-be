package com.luqman.bank.be.quartz;

import com.luqman.bank.be.service.BatchJobService;
import lombok.extern.slf4j.Slf4j;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.quartz.QuartzJobBean;
import org.springframework.stereotype.Component;

import java.util.Date;

@Slf4j
@Component
public class BatchJob extends QuartzJobBean {

    @Autowired
    private BatchJobService batchJobService;

    @Override
    protected void executeInternal(JobExecutionContext context) throws JobExecutionException {
        log.info("Running batch job at " + new Date());
        batchJobService.processBatchFile();
        log.info("Batch job end");
    }
}
