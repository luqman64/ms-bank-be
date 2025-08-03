package com.luqman.bank.be.dto;

import com.luqman.bank.be.entity.FundTrx;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
public class FundResponseDto {

    private Integer pageSize;
    private Integer pageNo;
    private Integer totalpage;
    private List<FundTrx> fundTrxList;

}
