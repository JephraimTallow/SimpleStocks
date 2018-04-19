package com.stocks.service;

import com.stocks.model.BaseStock;
import com.stocks.model.StockTicker;
import com.stocks.model.Trade;

import java.util.List;
import java.util.Map;

public interface StocksService {

    /**
     * Store the value of each trade within a spring bean for this exercise.
     */
    void recordTrade(Trade trade);

    /**
     * The results are restricted to the previous xx minutes from the current timestamp
     * @param ticker
     * @return double
     */
    double calculateStockPrice(StockTicker ticker, int minutesPrevious);

    /**
     * Trades are held with associated stock against the ticker value
     * @return Map
     */
    Map<StockTicker, List<Trade>> getTickerTrades();

    /**
     * Return trades that are a specified number of minutes in the past to the current time
     * @param ticker
     * @param minutesPrevious
     * @return List<Trade>
     */
    List<Trade> getTradesByTimeFrame(StockTicker ticker, int minutesPrevious);

    /**
     * Calculate the share index from the geometric mean - in this case the timeframe to look back at trades is included.
     * @param minutesPrevious
     * @return double
     */
    double calculateAllShareIndex(int minutesPrevious);

}
