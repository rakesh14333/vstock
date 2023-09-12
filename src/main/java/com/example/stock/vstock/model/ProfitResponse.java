package com.example.stock.vstock.model;

import lombok.Data;

@Data
public class ProfitResponse {

    private String coinName;

    private double profitPercentage;

    private String dayStartValue;

    private String presentValue;

    private Long rowValue;
}
