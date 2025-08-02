package com.luqman.bank.be.controller;

import com.luqman.bank.be.dto.FundRequestDto;
import com.luqman.bank.be.dto.FundResponseDto;
import com.luqman.bank.be.dto.FundUpdateReqDto;
import com.luqman.bank.be.entity.FundTrx;
import com.luqman.bank.be.service.FundService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@Slf4j
@RestController
public class FundController {

    @Autowired
    private FundService fundService;

    @PostMapping("/getFundList")
    public ResponseEntity<FundResponseDto> getFundList(@RequestBody FundRequestDto fundRequestDto) {
        return new ResponseEntity<>(fundService.fundList(fundRequestDto), HttpStatus.OK);
    }

    @GetMapping("/getFundById/{id}")
    public ResponseEntity<FundTrx> getFundById(@PathVariable("id") Integer id) {
        Optional<FundTrx> fundTrx = fundService.fundById(id);
        if (fundTrx.isPresent()) {
            return new ResponseEntity<>(fundTrx.get(), HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @PostMapping("/updateFundById")
    public ResponseEntity<FundTrx> updateFundById(@RequestBody FundUpdateReqDto fundUpdateReqDto) {
        FundTrx fundTrx = fundService.updateFundById(fundUpdateReqDto);
        if (fundTrx != null) {
            return new ResponseEntity<>(fundTrx, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }

}
