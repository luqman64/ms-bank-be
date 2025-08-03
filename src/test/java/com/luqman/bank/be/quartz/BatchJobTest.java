package com.luqman.bank.be.quartz;

import com.luqman.bank.be.service.BatchJobService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.quartz.impl.JobExecutionContextImpl;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.mockito.Mockito.*;

@ExtendWith(SpringExtension.class)
class BatchJobTest {

    @InjectMocks
    private BatchJob batchJob;

    @Mock
    private BatchJobService batchJobService;

    @Test
    void executeInternalTest() throws JobExecutionException {
        JobExecutionContext context = mock(JobExecutionContextImpl.class);
        batchJob.executeInternal(context);
        verify(batchJobService, times(1)).processBatchFile();
    }
}
