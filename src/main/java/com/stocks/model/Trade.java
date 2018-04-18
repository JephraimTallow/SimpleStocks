package com.stocks.model;

import java.time.LocalDateTime;

public class Trade {

    private BaseStock stock;
    private LocalDateTime timestamp;
    private int numberOfShares;
    private double tradePrice;
    private TypeOfTrade type;

    public Trade(BaseStock stock, LocalDateTime timestamp, int numberOfShares, double tradePrice, TypeOfTrade type){
        this.stock = stock;
        this.timestamp = timestamp;
        this.numberOfShares = numberOfShares;
        this.tradePrice = tradePrice;
        this.type = type;
    }


    public BaseStock getStock() {
        return stock;
    }

    public double getTradePrice() {
        return tradePrice;
    }

    public int getNumberOfShares() {
        return numberOfShares;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }
}
