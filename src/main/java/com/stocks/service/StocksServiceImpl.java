package com.stocks.service;

import com.stocks.model.*;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collector;
import java.util.stream.Collectors;

@Service
public class StocksServiceImpl implements StocksService {

    private Map<StockTicker, List<Trade>> tickerTrades = new HashMap<StockTicker, List<Trade>>();

    //private List<Trade> tradeList = new ArrayList<>();

    @Override
    public void recordTrade(Trade trade) {
        List<Trade> recordedTrades = tickerTrades.get(trade.getStock().getTicker());
        if (recordedTrades == null) {
            recordedTrades = new ArrayList<>();
        }

        recordedTrades.add(trade);
        tickerTrades.put(trade.getStock().getTicker(), recordedTrades);
    }

    /**
     * The results are restricted to the previous 15 minutes from the current timestamp
     * @param ticker
     * @return double
     */
    @Override
    public double calculateStockPrice(StockTicker ticker) {
        List<Trade> trades = tickerTrades.get(ticker);

        //trades.stream().map(Trade::getIncludedTrades).collect(Collectors.toList());
        // stream to get list of trades with timestamp t-15 or more

        List<Trade> tradeList = getTradesByTimeframe(ticker, 15);


        double priceQuantity = 0;
        int totalNumberOfShares = 0;
        for (Trade trade : trades) {
            priceQuantity += trade.getTradePrice() * trade.getNumberOfShares();
            totalNumberOfShares += trade.getNumberOfShares();
        }

        return priceQuantity /  totalNumberOfShares;
    }

    @Override
    public Map<StockTicker, List<Trade>> getTickerTrades() {
        return tickerTrades;
    }

    @Override
    public List<Trade> getTradesByTimeframe(StockTicker ticker, int minutesPrevious) {
        List<Trade> trades = tickerTrades.get(ticker);

        List<Trade> tradeList = trades.stream()
                .filter(x -> x.getTimestamp().isAfter(LocalDateTime.now().minusMinutes(minutesPrevious)))
                .collect(Collectors.toList());

        return tradeList;
    }

    public void setTickerTrades(Map<StockTicker, List<Trade>> tickerTrades) {
        this.tickerTrades = tickerTrades;
    }
}
