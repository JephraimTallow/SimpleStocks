package com.stocks.model;

import com.stocks.service.ServiceConfig;
import com.stocks.service.StocksService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;


import java.time.LocalDateTime;
import java.util.List;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {ServiceConfig.class})
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
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
        populateTradeData();

        List<Trade> ginTrades = stocksService.getTickerTrades().get(StockTicker.GIN);
        double ginTradeSum = ginTrades.stream().mapToDouble(Trade::getTradePrice).sum();
        assertEquals(ginTradeSum,554, 0);
    }

    @Test
    public void calculateStockPrice() {
        populateTradeData();

        double calculatedStockPrice = stocksService.calculateStockPrice(StockTicker.GIN);
        assertEquals(calculatedStockPrice,180, 1);
    }

    @Test
    public void checkTradesByTimeframe() {
        //populateTradeData();

        currentTrade = new Trade(new CommonStock(StockTicker.GIN, 8, 2, 100, 100),
                LocalDateTime.now().minusMinutes(30), 221, 201, TypeOfTrade.BUY);
        stocksService.recordTrade(currentTrade);

        List<Trade> tradesByTimeframe = stocksService.getTradesByTimeframe(StockTicker.GIN, 31);
        assertEquals(1, tradesByTimeframe.size() ,0);
    }

    private void populateTradeData() {
        currentTrade = new Trade(new CommonStock(StockTicker.ALE, 0, 0, 100, 100),
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
    }
}
