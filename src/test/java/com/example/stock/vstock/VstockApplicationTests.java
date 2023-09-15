package com.example.stock.vstock;

import com.example.stock.vstock.config.respository.CoinPriceRepository;
import com.example.stock.vstock.service.StockProfitService;
import org.junit.Ignore;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Spy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@SpringBootTest
class VstockApplicationTests {




    @Mock
    CoinPriceRepository coinPriceRepository;

    @Autowired
    public StockProfitService stockProfitService;


    @Test
    void contextLoads() {

    }






}
