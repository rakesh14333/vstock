package com.example.stock.vstock.controller;

import com.example.stock.vstock.config.respository.CoinPriceRepository;
import com.example.stock.vstock.config.respository.DailyMaxPriceRepository;
import com.example.stock.vstock.entity.CoinPrice;
import com.example.stock.vstock.entity.MaxDailyPrice;
import com.example.stock.vstock.model.ProfitResponse;
import com.example.stock.vstock.service.StockProfitService;
import com.example.stock.vstock.service.StockService;
import jakarta.annotation.Nullable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.*;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/stock")
public class restcontroller {
    @Autowired
    private StockProfitService stockProfitService;

    @Autowired
    private DailyMaxPriceRepository dailyMaxPriceRepository;


    @GetMapping("/top10profitstocks")
    public ResponseEntity getTop10ProfitPercentStocksOfTheDay() {
        try {
            return ResponseEntity.ok(stockProfitService.getTop10HighestStockValuesOnCurrentDay());
        }
        catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Unexpected Error Occurred");
        }
    }

    @GetMapping("/top10stockprices")
    public ResponseEntity getTop10StocksOfTheday(){
        try {
            return ResponseEntity.ok(stockProfitService.getTop10MaxPricesStockValuesOnCurrentDay());
        }
        catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Unexpected Error Occurred");
        }
    }



}
