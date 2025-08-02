package com.luqman.bank.be.service;

import com.luqman.bank.be.dto.FundRequestDto;
import com.luqman.bank.be.dto.FundResponseDto;
import com.luqman.bank.be.dto.FundUpdateReqDto;
import com.luqman.bank.be.entity.FundTrx;

import java.util.Optional;

public interface FundService {

    public FundResponseDto fundList(FundRequestDto fundRequestDto);

    public Optional<FundTrx> fundById(Integer id);

    public FundTrx updateFundById(FundUpdateReqDto fundUpdateReqDto);
}
