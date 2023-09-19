package com.example.stock.vstock.service;

import com.example.stock.vstock.config.respository.CoinPriceRepository;
import com.example.stock.vstock.config.respository.DailyMaxPriceRepository;
import com.example.stock.vstock.entity.CoinPrice;
import com.example.stock.vstock.entity.MaxDailyPrice;
import com.example.stock.vstock.model.ProfitResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class StockProfitService {

@Autowired
private DailyMaxPriceRepository dailyMaxPriceRepository;



public List<ProfitResponse> getTop10MaxPricesStockValuesOnCurrentDay() {
    try {
        List<MaxDailyPrice> maxPriceDataList = dailyMaxPriceRepository.findTop10MaxPricesStockValuesOnCurrentDay();

        List<ProfitResponse> result = new ArrayList<>();

        for (MaxDailyPrice row : maxPriceDataList) {
            ProfitResponse profitResponse = new ProfitResponse();
            profitResponse.setCoinName(row.getId().getCoinName());
            profitResponse.setMaxPrice(row.getMax_price());
            profitResponse.setFirstStockPrice(row.getFirst_stock_price());
            profitResponse.setLastStockPrice(row.getLast_stock_price());
            profitResponse.setMaxDate(row.getId().getMaxDate());

            result.add(profitResponse);
        }

        // Return the top 10 entries
        return result;
    } catch (Exception e) {
        e.printStackTrace();
    }
    return Collections.emptyList();

}

public List<ProfitResponse> getTop10HighestStockValuesOnCurrentDay() {
    try {
        List<MaxDailyPrice> profitResponseList = dailyMaxPriceRepository.findTop10HighestStockValuesOnCurrentDay();
        ArrayList<ProfitResponse> profitResponseArrayList = new ArrayList<>();
        for (MaxDailyPrice row : profitResponseList) {
            ProfitResponse profitResponse = new ProfitResponse();
            profitResponse.setCoinName(row.getId().getCoinName());
            profitResponse.setMaxPrice(row.getMax_price());
            profitResponse.setMaxDate(row.getId().getMaxDate());
            profitResponse.setFirstStockPrice(row.getFirst_stock_price());
            profitResponse.setLastStockPrice(row.getLast_stock_price());

            Double profitPercent = ((row.getLast_stock_price() - row.getFirst_stock_price()) / row.getFirst_stock_price()) * 100;

            profitResponse.setProfitPercentage(profitPercent);

            profitResponseArrayList.add(profitResponse);
        }
        profitResponseArrayList.sort((data1, data2) -> Double.compare(data2.getProfitPercentage(), data1.getProfitPercentage()));
        return profitResponseArrayList.stream().limit(10).collect(Collectors.toList());
    }

    catch (Exception e) {
        e.printStackTrace();
    }
    return Collections.emptyList();
}



}
