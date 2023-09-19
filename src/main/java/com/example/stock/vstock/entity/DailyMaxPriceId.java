package com.example.stock.vstock.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDate;
@Data
@Embeddable
public class DailyMaxPriceId implements Serializable {
    @Column(name="coin_name")
    private String coinName;
    @Column(name="max_date")
    private LocalDate maxDate;



}
