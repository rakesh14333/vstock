package com.example.stock.vstock.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Generated;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "coin_price")
public class CoinPrice {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    private Double price;

    private String coinName;

    private Date currTime;
}
