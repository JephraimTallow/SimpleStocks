package com.stocks.model;

public abstract class BaseStock {

    protected StockTicker ticker;
    protected int lastDividend;
    protected double fixedDividend;
    protected int parValue;

    protected double stockPrice;

    public BaseStock(StockTicker ticker, int lastDividend, double fixedDividend, int parValue, int stockPrice) {
        this.ticker = ticker;
        this.lastDividend = lastDividend;
        this.fixedDividend = fixedDividend/100;
        this.parValue = parValue;
        this.stockPrice = stockPrice;
    }

    public abstract double caculateDividendYield();

    public double calculatePERatio(){
        double returnVal = stockPrice / lastDividend;
        return returnVal == Double.POSITIVE_INFINITY ? 0 : returnVal;
    }

    public void setStockPrice(double stockPrice) {
        this.stockPrice = stockPrice;
    }

    public StockTicker getTicker() {
        return ticker;
    }
}
