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
    public void recordTrade(Trade trade);

    public double calculateStockPrice(StockTicker ticker);

    /**
     * Trades are held with associated stock against the ticker value
     * @return Map
     */
    public Map<StockTicker, List<Trade>> getTickerTrades();

    public List<Trade> getTradesByTimeframe(StockTicker ticker, int minutesPrevious);

}
