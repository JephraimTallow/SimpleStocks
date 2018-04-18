package com.stocks.model;

import com.stocks.service.ServiceConfig;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.junit.Assert.assertEquals;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {ServiceConfig.class})
public class PreferredStockTest {

    BaseStock tea;
    BaseStock ale;
    BaseStock joe;
    BaseStock gin;

    @Before
    public void setUp() {
        tea = new CommonStock(StockTicker.TEA, 0, 0, 100, 100);
        ale = new CommonStock(StockTicker.ALE, 23, 0, 60, 100);
        joe = new CommonStock(StockTicker.JOE, 13, 0, 250, 100);
        gin = new PreferredStock(StockTicker.GIN, 8, 2, 100, 100);
    }

    @After
    public void tearDown() {
    }

    @Test
    public void caculateTeaDividendYield() {
        double yieldValue = tea.caculateDividendYield();
        assertEquals("Calculation of yield incorrect", 0, yieldValue, 0);
    }

    @Test
    public void caculateAleDividendYield() {
        double yieldValue = ale.caculateDividendYield();
        assertEquals("Calculation of yield incorrect", 0.23, yieldValue, 0);
    }

    @Test
    public void caculateGinDividendYield() {
        double yieldValue = gin.caculateDividendYield();
        assertEquals("Calculation of yield incorrect", 0.02, yieldValue, 0);
    }

    @Test
    public void calculateTeaPERatio() {
        assertEquals(0, tea.calculatePERatio(),  0);
    }

    @Test
    public void calculateAlePERatio() {
        assertEquals(4.347, ale.calculatePERatio(), 0.001);
    }
}