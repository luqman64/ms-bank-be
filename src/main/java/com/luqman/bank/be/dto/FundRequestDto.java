package com.luqman.bank.be.dto;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class FundRequestDto {

    private String customerId;
    private String accountNumber;
    private String description;
    private Integer pageNo = 0;
    private Integer pageSize = 10;

}
