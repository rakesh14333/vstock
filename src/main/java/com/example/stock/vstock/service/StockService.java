package com.example.stock.vstock.service;

import com.example.stock.vstock.config.respository.CoinPriceRepository;
import com.example.stock.vstock.config.respository.DailyMaxPriceRepository;
import com.example.stock.vstock.entity.CoinPrice;
import com.example.stock.vstock.model.CoinResponse;
import com.example.stock.vstock.model.PriceResponse;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.annotation.PostConstruct;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.*;

@Service
public class StockService {
    public final String COIN_LIST_URI = "https://api.coingecko.com/api/v3/coins/list?include_platform=false";

    public final String COIN_PRICE_URL = "https://api.coingecko.com/api/v3/simple/price?ids=";

    @Autowired
    private RestTemplate restTemplate;
    @Autowired
    public CoinPriceRepository coinPriceRepository;
    @Autowired
    public DailyMaxPriceRepository dailyMaxPriceRepository;

    public static List<CoinPrice> coinPriceList = new ArrayList<>();
    String coinString = "";

    @PostConstruct
    public void getCoinsList() {

        ResponseEntity<Object> response = restTemplate.getForEntity(COIN_LIST_URI, Object.class);

        if (HttpStatus.OK.equals(response.getStatusCode()) && response.getBody() != null) {
            ArrayList<Object> coinsObject = (ArrayList<Object>) response.getBody();
            int count = 0;
            for (Object obj : coinsObject) {
                CoinResponse coin = new ObjectMapper().convertValue(obj, CoinResponse.class);
                if (coin.getId() != null && !coin.getId().isEmpty()) {
                    coinString += coin.getId() + ",";
                    count++;
                }
                if (count >= 300) {
                    break;
                }
            }
        }
    }

    public void savePriceListDetails() {
        ResponseEntity<LinkedHashMap> coinResponse = restTemplate.getForEntity(COIN_PRICE_URL + coinString + "&vs_currencies=eur", LinkedHashMap.class);

        LinkedHashMap priceObj = coinResponse.getBody();

        Set<String> keys = priceObj.keySet();

        for (String s : keys) {

            PriceResponse price = new ObjectMapper().convertValue(priceObj.get(s), PriceResponse.class);

            //System.out.println("Price for " + s + " is " + price.getEur());
            if (price.getEur() != null) {
                CoinPrice coinPrice = new CoinPrice();
                coinPrice.setCoinName(s);
                coinPrice.setPrice(price.getEur());
                coinPrice.setCurrTime(new Date());
                coinPriceRepository.save(coinPrice);

                //Store in local list
                //coinPriceList.add(coinPrice);

            }
        }

        System.out.println("Loaded the stock prices into memory");
    }

    @Scheduled(fixedDelay = 200000)
    public void savePriceDetailsOnFixedTime() {

        System.out.println("Loading the stock prices into memory");

        savePriceListDetails();
    }
    @Transactional
    @Scheduled(fixedDelay = 100000)
    public void saveMaxPricesIntoDailyMaxPricesTable() {
        System.out.println("Started updating DailyMaxPricesTable......");
        dailyMaxPriceRepository.insertMaxPricesIntoDailyMaxPricesTable();
        System.out.println("Finished updating DailyMaxPricesTable.......");
    }
}
