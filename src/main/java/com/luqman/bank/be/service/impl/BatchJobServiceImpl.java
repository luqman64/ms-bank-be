package com.luqman.bank.be.service.impl;

import com.luqman.bank.be.entity.FundTrx;
import com.luqman.bank.be.repository.FundTrxRepository;
import com.luqman.bank.be.service.BatchJobService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.item.file.transform.DelimitedLineTokenizer;
import org.springframework.batch.item.file.transform.FieldSet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Objects;
import java.util.stream.Stream;

@Slf4j
@Service
public class BatchJobServiceImpl implements BatchJobService {

    @Value("${spring.quartz.batchjob.filename}")
    private String fileName;

    private static final String SEPARATOR = "|";

    @Autowired
    private FundTrxRepository fundTrxRepository;

    @Override
    public void processBatchFile() {
        ArrayList<FundTrx> fundTrxList = new ArrayList<>();
        try (InputStream in = new ClassPathResource(fileName).getInputStream();
             BufferedReader reader = new BufferedReader(new InputStreamReader(in))) {
            Stream<String> lines = reader.lines();
            fundTrxList.addAll(lines.map(this::fundTrxProcLine).filter(Objects::nonNull).toList());
            if (!fundTrxList.isEmpty()) {
                fundTrxRepository.deleteAll();
                fundTrxRepository.saveAll(fundTrxList);
            }
        } catch (IOException e) {
            log.error("io error {}", e.getMessage());
        }
    }

    private FundTrx fundTrxProcLine(String line) {
        FundTrx fundTrx = null;
        if (!line.startsWith("ACCOUNT_NUMBER")) {
            DelimitedLineTokenizer tokenizer = new DelimitedLineTokenizer(SEPARATOR);
            tokenizer.setStrict(false);
            FieldSet fieldSet = tokenizer.tokenize(line);

            fundTrx = new FundTrx();
            fundTrx.setAccountNumber(fieldSet.readString(0));
            fundTrx.setTrxAmount(fieldSet.readBigDecimal(1));
            fundTrx.setDescription(fieldSet.readString(2));
            fundTrx.setTrxDate(LocalDate.parse(fieldSet.readString(3)));
            fundTrx.setTrxTime(LocalTime.parse(fieldSet.readString(4)));
            fundTrx.setCustomerId(fieldSet.readString(5));
        }
        return fundTrx;
    }

}
