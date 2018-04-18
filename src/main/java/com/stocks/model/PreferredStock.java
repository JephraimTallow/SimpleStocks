package com.stocks.model;

public class PreferredStock extends BaseStock {

    public PreferredStock(StockTicker ticker, int lastDividend, double fixedDividend, int parValue, int stockPrice) {
        super(ticker, lastDividend, fixedDividend, parValue, stockPrice);
    }

    @Override
    public double caculateDividendYield() {
        return ((fixedDividend) * parValue) / stockPrice;
    }
}
