package com.stocks.model;

public class CommonStock extends BaseStock {

    public CommonStock(StockTicker ticker, int lastDividend, int fixedDividend, int parValue, int stockPrice) {
        super(ticker, lastDividend, fixedDividend, parValue, stockPrice);
    }

    @Override
    public double caculateDividendYield() {
        return lastDividend / stockPrice;
    }

}
