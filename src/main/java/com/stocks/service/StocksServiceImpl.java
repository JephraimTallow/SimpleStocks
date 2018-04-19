package com.stocks.service;

import com.stocks.model.*;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;

@Service
public class StocksServiceImpl implements StocksService {

    private final Logger logger = Logger.getLogger(StocksServiceImpl.class.getName());

    private Map<StockTicker, List<Trade>> tickerTrades = new HashMap<>();

    @Override
    public void recordTrade(Trade trade) {
        List<Trade> recordedTrades = tickerTrades.get(trade.getStock().getTicker());
        if (recordedTrades == null) {
            recordedTrades = new ArrayList<>();
        }

        logger.log(Level.INFO, "Recording trade for - " + trade);

        recordedTrades.add(trade);
        tickerTrades.put(trade.getStock().getTicker(), recordedTrades);
    }


    @Override
    public double calculateStockPrice(StockTicker ticker, int minutesPrevious) {

        List<Trade> tradeList = getTradesByTimeFrame(ticker, minutesPrevious);

        double priceQuantity = 0;
        int totalNumberOfShares = 0;
        for (Trade trade : tradeList) {
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
    public List<Trade> getTradesByTimeFrame(StockTicker ticker, int minutesPrevious) {
        List<Trade> trades = tickerTrades.get(ticker);

        List<Trade> tradeList = trades.stream()
                .filter(x -> x.getTimestamp().isAfter(LocalDateTime.now().minusMinutes(minutesPrevious)))
                .collect(Collectors.toList());

        return tradeList;
    }

    @Override
    public double calculateAllShareIndex(int minutesPreviouss) {


        final double[] sumAllStockPrice = {0};

        
        tickerTrades.forEach((k, v) -> {
            double sumStockPrice = calculateStockPrice(k, minutesPreviouss);
            sumAllStockPrice[0] += sumStockPrice;
        });

//        for (Trade trade : trades) {
//            double sumStockPrice = calculateStockPrice(trade.getStock().getTicker(), minutesPreviouss);
//            sumAllStockPrice[0] += sumStockPrice;
//        }
        return Math.sqrt(sumAllStockPrice[0]);
    }

}
