package com.example.stock.vstock.controller;

import com.example.stock.vstock.config.respository.CoinPriceRepository;
import com.example.stock.vstock.entity.CoinPrice;
import com.example.stock.vstock.model.ProfitResponse;
import com.example.stock.vstock.service.StockProfitService;
import com.example.stock.vstock.service.StockService;
import jakarta.annotation.Nullable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Date;
import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping("/stock")
public class restcontroller {
    @Autowired
    private StockProfitService stockProfitService;
    @Autowired
    private CoinPriceRepository coinPriceRepository;


    @GetMapping("/stocks")
    public ResponseEntity getStockDetails(@Nullable @RequestParam String db) {

        if ("yes".equalsIgnoreCase(db)) {
            return ResponseEntity.ok(coinPriceRepository.findAll());
        } else {
            return ResponseEntity.ok(StockService.coinPriceList);
        }

    }

    @GetMapping("/topstocks")
    public List<CoinPrice> getTop10StocksOfDay() {
        // Date currentTimeStamp=new Date();
        // logger.info("Current Timestamp: " + currentTimeStamp);
        return coinPriceRepository.findTop10StocksOfDay();
    }

    @GetMapping("/profitstocks")
    public List<CoinPrice> getProfitStocks() {
        return coinPriceRepository.findTop10Stocks();
    }

    @GetMapping("/top10profitstocks")
    public List<ProfitResponse> getTop10StocksProfitPercentage() {
        return stockProfitService.getTop10StocksProfitPercentage();
    }

}
