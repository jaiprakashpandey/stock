package test.morgan.service;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import test.morgan.exception.StockExceptions;
import test.morgan.modal.Stock;
import test.morgan.modal.Trade;
import test.morgan.modal.constants.StockType;
import test.morgan.modal.constants.TradeType;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collection;

import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.assertNotNull;


/***
 * Test class to test the Stock Service
 */
public class StockServiceImplTest {

    StockService stockService;

    @Before
    public void setUp() throws Exception {
        stockService = new StockServiceImpl();

    }

    @After
    public void tearDown() throws Exception {
        stockService = null;
    }

    @Test
    public void calculateDividendYield() throws StockExceptions {
        Stock apple = new Stock("AAPLE", StockType.COMMON, 10.0, 0.0, 100.0);
        Double yield = stockService.calculateDividendYield(apple, 20.0);
        assertNotNull(yield);
        assertEquals(0.5, yield);

    }

    @Test
    public void calculatePriceByEarning() throws StockExceptions {
        Stock apple = new Stock("AAPLE", StockType.COMMON, 10.0, 0.0, 100.0);
        Double priceByEarningsRatio = stockService.calculatePriceByEarning(apple, 20.0);
        assertNotNull(priceByEarningsRatio);
        assertEquals(2.0, priceByEarningsRatio);
    }

    @Test
    public void processTrade() throws Exception {
        Stock samsung = new Stock("SAMSUNG", StockType.PREFERRED, 0.0, 0.05, 100.0);
        int quantity = 1000;
        stockService.processTrade(samsung, TradeType.BUY, quantity, 20);

        assertEquals(1, samsung.getTrades().size());
        assertEquals(20.0, samsung.getLastTradedPrice());

        stockService.processTrade(samsung, TradeType.SELL, quantity, 30);

        assertEquals(2, samsung.getTrades().size());
        assertEquals(30.0, samsung.getLastTradedPrice());
    }

    @Test
    public void calculateVolWeightedStockPrice() throws Exception {
        Stock jpmc = new Stock("JPMC", StockType.COMMON, 10.0, 0.0, 100.0);
        int quantity = 1000;
        LocalDateTime buyTimeStamp = stockService.processTrade(jpmc, TradeType.BUY, quantity, 20);

        assertEquals(1, jpmc.getTrades().size());
        assertEquals(20.0, jpmc.getLastTradedPrice());

        LocalDateTime sellTimeStamp = stockService.processTrade(jpmc, TradeType.SELL, quantity, 30);

        Double volWtStockPrce = stockService.calculateVolWeightedStockPrice(jpmc);

        assertNotNull(volWtStockPrce);
        assertEquals(25.0, volWtStockPrce);

        // Mock the SELL Trade 20 minutes back
        Trade sellTradeTxn = jpmc.getTrades().get(sellTimeStamp);
        jpmc.getTrades().remove(sellTimeStamp);
        LocalDateTime thirteenMinsBefore = sellTimeStamp.minusMinutes(20);
        sellTradeTxn.setTimeStamp(thirteenMinsBefore);
        jpmc.getTrades().put(thirteenMinsBefore,sellTradeTxn);

        // now calculate Volume Weighted Stock price which should calculate trades which are done within last 15 mins only

        Double volWeightTradedWithinFifteenMins = stockService.calculateVolWeightedStockPrice(jpmc);

        assertNotNull(volWeightTradedWithinFifteenMins);
        assertEquals(20.0, volWeightTradedWithinFifteenMins);

    }

    @Test
    public void calculateAllShareIndex() throws Exception {
        Stock apple = new Stock("AAPLE", StockType.COMMON, 10.0, 0.0, 100.0);
        Stock samsung = new Stock("SAMSUNG", StockType.PREFERRED, 0.0, 0.05, 100.0);
        Stock jpmc = new Stock("JPMC", StockType.COMMON, 10.0, 0.0, 100.0);

        Collection<Stock> stocks = new ArrayList();
        stocks.add(apple);
        stocks.add(samsung);
        stocks.add(jpmc);

        stockService.processTrade(apple, TradeType.BUY, 10, 20);
        stockService.processTrade(samsung, TradeType.SELL, 10, 20);
        stockService.processTrade(jpmc, TradeType.SELL, 10, 20);

        Double shareIndex = stockService.calculateAllShareIndex(stocks);

        assertNotNull(shareIndex);
        assertEquals(19.999999999999996, shareIndex);

    }

}