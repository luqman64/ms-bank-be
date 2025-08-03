package com.luqman.bank.be.controller;

import com.luqman.bank.be.entity.FundTrx;
import com.luqman.bank.be.service.FundService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(PageController.class)
class PageControllerTest {

    @Autowired
    private MockMvc mvc;

    @MockitoBean
    private FundService fundService;

    @Test
    void indexTest() throws Exception {
        mvc.perform(get("/")).andExpect(status().isOk());
    }

    @Test
    void updatePageTest() throws Exception {
        when(fundService.fundById(any())).thenReturn(Optional.of(new FundTrx()));
        mvc.perform(get("/update/1")).andExpect(status().isOk());
    }


}
