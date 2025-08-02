package com.luqman.bank.be.service.impl;

import com.luqman.bank.be.service.BatchJobService;
import org.springframework.beans.factory.annotation.Value;

public class BatchJobServiceImpl implements BatchJobService {

    @Value("${spring.quartz.file-path}")
    private String filePath;

    @Override
    public void processBatchFile() {

    }

}
