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

    @Query(value = "SELECT *, DATE(curr_time) AS date_only\n" +
            "FROM coin_price cp\n" +
            "ORDER BY date_only DESC, price desc LIMIT 10",nativeQuery = true)
    List<CoinPrice> findTop10StocksOfDay( );
    @Query(value = "SELECT *, DATE(curr_time) AS date_only,EXTRACT(HOUR from curr_time) as hour_only \n" +
            "FROM coin_price cp\n" +
            "ORDER BY date_only,hour_only DESC, price desc LIMIT 10",nativeQuery = true)
    List<CoinPrice> findTop10Stocks();

    @Query(value="select coin_name,first_value (price) over(partition by coin_name order by curr_time desc) as first_val,last_value (price) over(partition by coin_name order by curr_time) as last_val,\n" +
            "row_number() over (partition by coin_name)\n" +
            " from coin_price where DATE_TRUNC('day', curr_time)=current_date;",nativeQuery = true)
    List<Object[]> findTop10ProfitStocksPercent();


}
