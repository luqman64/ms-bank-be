package com.luqman.bank.be.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalTime;

@Getter
@Setter
@Entity
@Table(name = "fund_trx")
public class FundTrx {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;
    @Column(name = "account_number")
    private String accountNumber;
    @Column(name = "trx_amount")
    private BigDecimal trxAmount;
    @Column(name = "description")
    private String description;
    @Column(name = "trx_date")
    private LocalDate trxDate;
    @Column(name = "trx_time")
    private LocalTime trxTime;
    @Column(name = "customer_id")
    private String customerId;
}