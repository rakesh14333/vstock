package com.example.stock.vstock.service;

import com.example.stock.vstock.config.respository.CoinPriceRepository;
import com.example.stock.vstock.entity.CoinPrice;
import com.example.stock.vstock.model.ProfitResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class StockProfitService {

@Autowired
private CoinPriceRepository coinPriceRepository;


public List<CoinPrice> getTopStocks(){
    return coinPriceRepository.findAll();
}
public List<ProfitResponse> getTop10StocksProfitPercentage(){

    List<ProfitResponse> profitDataList=new ArrayList<>();

    List<Object[]> result=coinPriceRepository.findTop10ProfitStocksPercent();

    for(Object[] row:result){
      ProfitResponse profitResponse=new ProfitResponse();
      profitResponse.setCoinName((String) row[0]);
      profitResponse.setDayStartValue((String) row[1]);
      profitResponse.setPresentValue((String) row[2]);
      profitResponse.setRowValue((Long) row[3]);

      double dayStartStock=Double.parseDouble(profitResponse.getDayStartValue());
      double dayPresentStock=Double.parseDouble(profitResponse.getPresentValue());

      if(dayStartStock!=0 && dayPresentStock!=0 && profitResponse.getRowValue()==1) {
          double profitPercent = ((dayStartStock - dayPresentStock) / dayPresentStock) * 100;
          profitResponse.setProfitPercentage((Double) profitPercent);
      }
      profitDataList.add(profitResponse);
    }

    profitDataList.sort((data1, data2) -> Double.compare(data2.getProfitPercentage(), data1.getProfitPercentage()));

    // Return the top 10 entries
    return profitDataList.stream()
            .limit(10)
            .collect(Collectors.toList());


}
public void getallDetails(){
    System.out.println("Rakesh here");
}

}
