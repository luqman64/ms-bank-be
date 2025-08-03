package com.luqman.bank.be.service;

import com.luqman.bank.be.dto.FundRequestDto;
import com.luqman.bank.be.dto.FundUpdateReqDto;
import com.luqman.bank.be.entity.FundTrx;
import com.luqman.bank.be.repository.FundTrxRepository;
import com.luqman.bank.be.service.impl.FundServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(SpringExtension.class)
class FundServiceImplTest {

    @InjectMocks
    private FundServiceImpl fundService;

    @Mock
    private FundTrxRepository fundTrxRepository;

    @Test
    void fundListTest() {
        FundTrx fundTrx = new FundTrx();
        fundTrx.setCustomerId("11");
        List<FundTrx> fundTrxList = new ArrayList<>();
        fundTrxList.add(fundTrx);

        PageRequest pageRequest = PageRequest.of(0, 10);
        Page<FundTrx> pageFundTrx = new PageImpl<>(fundTrxList, pageRequest, fundTrxList.size());

        when(fundTrxRepository.getFundList(any(), any(), any(), any())).thenReturn(pageFundTrx);
        assertNotNull(fundService.fundList(new FundRequestDto()));
    }

    @Test
    void fundByIdTest() {
        when(fundTrxRepository.findById(any())).thenReturn(Optional.of(new FundTrx()));
        assertNotNull(fundService.fundById(1));
    }

    @Test
    void updateByFundIdTest() {
        FundTrx fundTrx = new FundTrx();
        fundTrx.setCustomerId("11");

        when(fundTrxRepository.findById(any())).thenReturn(Optional.of(fundTrx));
        when(fundTrxRepository.save(any())).thenReturn(fundTrx);
        assertNotNull(fundService.updateFundById(new FundUpdateReqDto()));
    }

    @Test
    void updateByFundIdNullTest() {
        when(fundTrxRepository.findById(any())).thenReturn(Optional.empty());
        when(fundTrxRepository.save(any())).thenReturn(Optional.empty());
        assertNull(fundService.updateFundById(new FundUpdateReqDto()));
    }

}
