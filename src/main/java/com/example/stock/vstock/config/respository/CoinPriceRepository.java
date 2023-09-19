package com.example.stock.vstock.config.respository;

import com.example.stock.vstock.entity.CoinPrice;
import com.example.stock.vstock.model.ProfitResponse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface CoinPriceRepository extends JpaRepository<CoinPrice,Integer> {

}
