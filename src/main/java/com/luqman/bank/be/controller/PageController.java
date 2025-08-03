package com.luqman.bank.be.controller;

import com.luqman.bank.be.entity.FundTrx;
import com.luqman.bank.be.service.FundService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.Optional;

@Slf4j
@Controller
public class PageController {

    @Autowired
    private FundService fundService;

    @GetMapping("/")
    public String mainPage(Model model) {
        return "index";
    }

    @GetMapping("/update/{id}")
    public String updateFundTrx(@PathVariable Integer id, Model model) {
        Optional<FundTrx> fundTrx = fundService.fundById(id);
        model.addAttribute("fundTrx", fundTrx.get());
        return "updatePage";
    }


}
