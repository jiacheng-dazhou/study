package com.study.stock.controller;

import com.study.stock.impl.StockServiceImpl;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@RequestMapping("/stock")
public class StockController {
    @Resource
    private StockServiceImpl stockService;

    @GetMapping("/deduct")
    public String deduct(){
        this.stockService.deduct();
        return "hello stock deduct!!!";
    }
}
