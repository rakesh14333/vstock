package com.example.stock.vstock.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
@Entity
@Table(name="daily_max_prices")
public class MaxDailyPrice {

    @EmbeddedId
    private DailyMaxPriceId Id;
    private Double max_price;
    private Double first_stock_price;
    private Double last_stock_price;
}
