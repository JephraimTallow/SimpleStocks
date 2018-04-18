package com.stocks.model;

import com.stocks.service.ServiceConfig;
import com.stocks.service.StocksService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import static org.junit.Assert.assertTrue;


import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {ServiceConfig.class})
public class RecordTradesTest {

    @Autowired
    StocksService stocksService;
    Trade currentTrade;

    @Test
    public void recordSingleTrade() {
        currentTrade = new Trade(new CommonStock(StockTicker.TEA, 0, 0, 100, 100),
                LocalDateTime.now(), 300, 150, TypeOfTrade.BUY);

        stocksService.recordTrade(currentTrade);
        assertTrue(stocksService.getTickerTrades().containsKey(StockTicker.TEA));
    }

    @Test
    public void recordMultipleTrades() {
        currentTrade = new Trade(new CommonStock(StockTicker.TEA, 0, 0, 100, 100),
                LocalDateTime.now(), 300, 150, TypeOfTrade.BUY);
        stocksService.recordTrade(currentTrade);
        currentTrade = new Trade(new CommonStock(StockTicker.GIN, 8, 2, 100, 100),
                LocalDateTime.now(), 500, 175, TypeOfTrade.BUY);
        stocksService.recordTrade(currentTrade);
        currentTrade = new Trade(new CommonStock(StockTicker.GIN, 8, 2, 100, 100),
                LocalDateTime.now().plusMinutes(3), 500, 178, TypeOfTrade.BUY);
        stocksService.recordTrade(currentTrade);
        currentTrade = new Trade(new CommonStock(StockTicker.GIN, 8, 2, 100, 100),
                LocalDateTime.now().plusMinutes(3), 221, 201, TypeOfTrade.BUY);
        stocksService.recordTrade(currentTrade);


        List<Trade> trades = stocksService.getTickerTrades().get(StockTicker.GIN);
        List<Double> collect = trades.stream().map(Trade::getTradePrice).collect(Collectors.toList());
        // TODO  - can use stream for all trade prices later on...

        assertTrue(stocksService.getTickerTrades().get(StockTicker.GIN).size() == 3);
    }

}
