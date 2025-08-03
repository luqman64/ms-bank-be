package com.luqman.bank.be.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.luqman.bank.be.dto.FundRequestDto;
import com.luqman.bank.be.dto.FundResponseDto;
import com.luqman.bank.be.dto.FundUpdateReqDto;
import com.luqman.bank.be.entity.FundTrx;
import com.luqman.bank.be.service.FundService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(FundController.class)
class FundControllerTest {

    @Autowired
    private MockMvc mvc;

    @MockitoBean
    private FundService fundService;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void getFundListTest() throws Exception {
        when(fundService.fundList(any())).thenReturn(new FundResponseDto());
        mvc.perform(post("/getFundList").contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(new FundRequestDto()))).andExpect(status().isOk());
    }

    @Test
    void getFundByIdTest() throws Exception {
        FundTrx fundTrx = new FundTrx();
        fundTrx.setCustomerId("11");
        when(fundService.fundById(any())).thenReturn(Optional.of(fundTrx));
        mvc.perform(get("/getFundById/11")).andExpect(status().isOk());
    }

    @Test
    void getFundByIdErrTest() throws Exception {
        when(fundService.fundById(any())).thenReturn(Optional.empty());
        mvc.perform(get("/getFundById/11")).andExpect(status().isInternalServerError());
    }

    @Test
    void updateFundByIdTest() throws Exception {
        when(fundService.updateFundById(any())).thenReturn(new FundTrx());
        mvc.perform(post("/updateFundById").contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(new FundUpdateReqDto()))).andExpect(status().isOk());

    }

    @Test
    void updateFundByIdErrTest() throws Exception {
        when(fundService.updateFundById(any())).thenReturn(null);
        mvc.perform(post("/updateFundById").contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(new FundUpdateReqDto()))).andExpect(status().isInternalServerError());

    }
}
