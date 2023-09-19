package com.example.stock.vstock.config.respository;

import com.example.stock.vstock.entity.MaxDailyPrice;
import com.example.stock.vstock.model.ProfitResponse;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface DailyMaxPriceRepository extends JpaRepository<MaxDailyPrice,Integer> {
    @Transactional
    @Modifying
    @Query(value = "INSERT INTO daily_max_prices (coin_name, max_date, max_price, first_stock_price,last_stock_price)\n" +
            " SELECT\n" +
            "    coin_name,\n" +
            "    current_date,\n" +
            "    MAX(price) AS max_price,\n" +
            "    (\n" +
            "        SELECT price\n" +
            "        FROM coin_price AS s2\n" +
            "        WHERE s1.coin_name = s2.coin_name\n" +
            "        AND DATE_TRUNC('day', s2.curr_time) = current_date\n" +
            "        ORDER BY s2.curr_time\n" +
            "        LIMIT 1\n" +
            "    ) AS first_stock_price,\n" +
            "    (\n" +
            "        SELECT price\n" +
            "        FROM coin_price AS s2\n" +
            "        WHERE s1.coin_name = s2.coin_name\n" +
            "        AND DATE_TRUNC('day', s2.curr_time) = current_date\n" +
            "        ORDER BY s2.curr_time desc\n" +
            "        LIMIT 1\n" +
            "    ) AS last_stock_price\n" +
            "    \n" +
            "FROM coin_price AS s1\n" +
            "WHERE DATE_TRUNC('day', s1.curr_time) = current_date\n" +
            "GROUP BY coin_name\n" +
            "ON CONFLICT (coin_name, max_date) DO UPDATE\n" +
            "SET max_price = EXCLUDED.max_price, first_stock_price = EXCLUDED.first_stock_price, last_stock_price=EXCLUDED.last_stock_price;",nativeQuery = true)
    public void insertMaxPricesIntoDailyMaxPricesTable();

    @Query(value = "select * from daily_max_prices where max_date=current_date;",nativeQuery = true)
    public List<MaxDailyPrice> findTop10HighestStockValuesOnCurrentDay();

    @Query(value="select * from daily_max_prices where max_date=current_date  order by max_price desc limit 10;",nativeQuery = true)
    public List<MaxDailyPrice> findTop10MaxPricesStockValuesOnCurrentDay();
}
