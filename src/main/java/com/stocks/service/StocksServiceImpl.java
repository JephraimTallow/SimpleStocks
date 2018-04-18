package com.stocks.service;

import com.stocks.model.*;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class StocksServiceImpl implements StocksService {

    Map<StockTicker, List<Trade>> tickerTrades = new HashMap<StockTicker, List<Trade>>();

    List<Trade> tradeList = new ArrayList<>();

    @Override
    public void recordTrade(Trade trade) {
        tradeList.add(trade);
        tickerTrades.put(trade.getStock().getTicker(), tradeList);
    }

    @Override
    public BaseStock calculateStockPrice(StockTicker ticker) {
        return null;
    }

    @Override
    public Map<StockTicker, List<Trade>> getTickerTrades() {
        return tickerTrades;
    }

    public void setTickerTrades(Map<StockTicker, List<Trade>> tickerTrades) {
        this.tickerTrades = tickerTrades;
    }
}
