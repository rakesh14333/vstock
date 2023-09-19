package com.example.stock.vstock.model;

import lombok.Data;

import java.time.LocalDate;
import java.util.Date;

@Data
public class ProfitResponse {

    private String coinName;

    private LocalDate maxDate;

    private Double maxPrice;

    private Double firstStockPrice;

    private Double lastStockPrice;

    private Double profitPercentage;
}
