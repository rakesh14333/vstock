package com.example.stock.vstock;

import com.example.stock.vstock.config.respository.CoinPriceRepository;
import com.example.stock.vstock.model.ProfitResponse;
import com.example.stock.vstock.service.StockProfitService;
import com.example.stock.vstock.service.Student;
import com.google.gson.Gson;
import org.junit.Assert;
import org.junit.Ignore;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Spy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@SpringBootTest
class VstockApplicationTests {
    @Spy
    Student theStudent;

    @Test
    public void getSummaryTest(){
        when(theStudent.getMarks()).thenReturn(50);
        assertArrayEquals(theStudent.getSummary(), new int[]{50, 25, 200});
    }



    @Mock
    CoinPriceRepository coinPriceRepository;

    @Autowired
    public StockProfitService stockProfitService;


    @Test
    void contextLoads() {

    }


    @Ignore
    void getDetails() {
        assertTrue(false);
    }

    @Test
    void getTop10ProfitPercentagesTest() {

        when(coinPriceRepository.findTop10ProfitStocksPercent()).thenReturn(giveDummyData());
        when(stockProfitService.getTop10StocksProfitPercentage()).thenReturn(List.of(new ProfitResponse()));
        assertNotNull(stockProfitService.getTop10StocksProfitPercentage());

    }

    List<Object[]> giveDummyData() {

        return new Gson().fromJson("[[\"-2\",0.00130392,0.00130392,1],[\"-2\",0.00130392,0.00130392,2],[\"-2\",0.00130392,0.00130392,3],[\"-2\",0.00130392,0.00130392,4],[\"0-knowledge-network\",1.6899E-4,1.6899E-4,1],[\"0-knowledge-network\",1.6899E-4,1.6899E-4,2],[\"0-knowledge-network\",1.6899E-4,1.6899E-4,3],[\"0-knowledge-network\",1.6899E-4,1.6899E-4,4],[\"01coin\",2.1742E-4,2.1742E-4,1],[\"01coin\",2.1742E-4,2.1742E-4,2]]", List.class);

    }


}
