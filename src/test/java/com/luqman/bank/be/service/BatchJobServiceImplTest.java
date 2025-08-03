package com.luqman.bank.be.service;

import com.luqman.bank.be.entity.FundTrx;
import com.luqman.bank.be.repository.FundTrxRepository;
import com.luqman.bank.be.service.impl.BatchJobServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.util.ReflectionTestUtils;

import java.io.FileWriter;
import java.nio.file.Path;

import org.junit.jupiter.api.extension.ExtendWith;

@ExtendWith(MockitoExtension.class)
class BatchJobServiceImplTest {

    @Mock
    private FundTrxRepository fundTrxRepository;

    @InjectMocks
    private BatchJobServiceImpl batchJobService;

    @TempDir
    Path tempDir;

    @Test
    void processBatchFile_shouldReadAndSaveData() throws Exception {
        String fileContent = "ACCOUNT_NUMBER|TRX_AMOUNT|DESCRIPTION|TRX_DATE|TRX_TIME|CUSTOMER_ID\n" +
                "8872838283|123.00|FUND TRANSFER|2019-09-12|11:11:11|222\n" +
                "8872838283|1123.00|ATM WITHDRWAL|2019-09-11|11:11:11|222";

        Path tempFile = tempDir.resolve("dataSourceTest.txt");
        try (FileWriter writer = new FileWriter(tempFile.toFile())) {
            writer.write(fileContent);
        }

        ReflectionTestUtils.setField(batchJobService, "fileName", tempFile.getFileName().toString());

        Path testResourcePath = Path.of("src", "test", "resources", tempFile.getFileName().toString());
        testResourcePath.toFile().getParentFile().mkdirs();
        java.nio.file.Files.copy(tempFile, testResourcePath, java.nio.file.StandardCopyOption.REPLACE_EXISTING);
        batchJobService.processBatchFile();
    }

    @Test
    void fundTrxProcLine_shouldReturnNullForHeader() throws Exception {
        var result = ReflectionTestUtils.invokeMethod(batchJobService,
                "fundTrxProcLine", "ACCOUNT_NUMBER|TRX_AMOUNT|DESCRIPTION|TRX_DATE|TRX_TIME|CUSTOMER_ID");
        assert result == null;
    }

    @Test
    void fundTrxProcLine_shouldMapFieldsCorrectly() throws Exception {
        String line = "1234567890|1000.00|Deposit|2023-08-01|09:00:00|CUST001";
        FundTrx result = ReflectionTestUtils.invokeMethod(batchJobService, "fundTrxProcLine", line);

        assert result != null;
        assert result.getAccountNumber().equals("1234567890");
        assert result.getTrxAmount().toString().equals("1000.00");
        assert result.getDescription().equals("Deposit");
        assert result.getTrxDate().toString().equals("2023-08-01");
        assert result.getTrxTime().toString().equals("09:00");
        assert result.getCustomerId().equals("CUST001");
    }
}

