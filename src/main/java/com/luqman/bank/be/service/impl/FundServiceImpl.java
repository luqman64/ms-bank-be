package com.luqman.bank.be.service.impl;

import com.luqman.bank.be.dto.FundRequestDto;
import com.luqman.bank.be.dto.FundResponseDto;
import com.luqman.bank.be.dto.FundUpdateReqDto;
import com.luqman.bank.be.entity.FundTrx;
import com.luqman.bank.be.repository.FundTrxRepository;
import com.luqman.bank.be.service.FundService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Optional;

@Slf4j
@Service
public class FundServiceImpl implements FundService {

    @Autowired
    private FundTrxRepository fundTrxRepository;

    @Override
    public FundResponseDto fundList(FundRequestDto fundRequestDto) {
        Page<FundTrx> fundTrx = fundTrxRepository.getFundList(fundRequestDto.getCustomerId(), fundRequestDto.getAccountNumber(),
                fundRequestDto.getDescription(), PageRequest.of(fundRequestDto.getPageNo(), fundRequestDto.getPageSize()));
        FundResponseDto fundResponseDto = new FundResponseDto();
        fundResponseDto.setFundTrxList(new ArrayList<>(fundTrx.getContent()));
        fundResponseDto.setPageNo(fundTrx.getPageable().getPageNumber());
        fundResponseDto.setPageSize(fundTrx.getPageable().getPageSize());
        fundResponseDto.setTotalpage(fundTrx.getTotalPages());
        return fundResponseDto;
    }

    @Override
    public Optional<FundTrx> fundById(Integer id) {
        return fundTrxRepository.findById(id);
    }

    @Override
    @Transactional
    public FundTrx updateFundById(FundUpdateReqDto fundUpdateReqDto) {
        Optional<FundTrx> fundTrx = fundTrxRepository.findById(fundUpdateReqDto.getId());
        if (fundTrx.isPresent()) {
            FundTrx fundTrx1 = fundTrx.get();
            fundTrx1.setDescription(fundUpdateReqDto.getDescription());
            return fundTrxRepository.save(fundTrx1);
        }
        return null;
    }


}
