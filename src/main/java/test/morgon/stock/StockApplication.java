package test.morgon.stock;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import test.morgan.exception.StockExceptions;
import test.morgan.modal.Stock;
import test.morgan.modal.constants.TradeType;
import test.morgan.service.StockService;
import test.morgan.service.StockServiceImpl;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.Map;

/**
 * Main application that initiates the stock market operations
 */
@SpringBootApplication
@ComponentScan("test.morgan.service")
public class StockApplication {
    private static Log log = LogFactory.getLog(StockApplication.class);

    StockService stockService;

    public static void main(String[] args) throws InterruptedException {
        SpringApplication.run(StockApplication.class, args);
        StockApplication application = new StockApplication();
        ApplicationContext context = new AnnotationConfigApplicationContext(StockApplication.class);
        application.stockService = context.getBean("stockService", StockServiceImpl.class);

        log.info("\n\n---Starting Super Simple Stock Market application---\n");

        application.initiateSuperSimpleStockMarket();
        log.info("\n\n---Super Simple Stock Market application completed---\n");
    }

    private void initiateSuperSimpleStockMarket() throws InterruptedException {
        // lets create some stocks
        Map<String, Stock> stockData = StockDataHelper.getStocks();
        Collection<Stock> stocks = stockData.values();
        //for given Stocks
        stocks.stream().forEach(stock -> {
            //for given price calculate dividend
            double dividendYield = stockService.calculateDividendYield(stock, 10.2);

            log.info("--YIELD FOR STOCK: " + stock.getSymbol() + " is " + dividendYield + " for price: " + 10.2);

            //for given price calculate Price by earning ratio as per formula given in assignment
            double ratio = 0;
            try {
                ratio = stockService.calculatePriceByEarning(stock, 10.2);
                log.info("--P/E RATIO FOR STOCK: " + stock.getSymbol() + " is " + ratio + " for price: " + 10.2);
            } catch (StockExceptions stockExceptions) {
                log.info("--The PE ratio for this stock doesn't exist: " + stock.getSymbol());
            }

        });


        log.info("\n\n--Initiating trades for stocks TEA/GIN/ALE---\n");
        log.info("\n\n--Buying and selling each stock types at price 20.0 and 10.0 respectively ---\n");
        // Trades for given Stock TEA
        //BUY
        Stock tea = stockData.get("TEA");
        LocalDateTime tradeTime = stockService.processTrade(tea, TradeType.BUY, 10, 20.0);
        log.info("TEA is bought successfully at:" + tradeTime);
        //SELL
        tradeTime = null;
        tradeTime = stockService.processTrade(tea, TradeType.SELL, 5, 10.0);
        log.info("TEA is sold successfully at:" + tradeTime);
        tradeTime = null;
        // Trades for given Stock GIN
        //BUY
        Stock gin = stockData.get("GIN");
        tradeTime = stockService.processTrade(gin, TradeType.BUY, 5, 20.0);
        log.info("gin is bought successfully at:" + tradeTime);
        tradeTime = null;
        //SELL
        tradeTime = stockService.processTrade(gin, TradeType.SELL, 5, 10.0);
        log.info("gin is sold successfully at:" + tradeTime);
        tradeTime = null;
        // Trades for given Stock ALE
        //BUY
        Stock ale = stockData.get("ALE");
        tradeTime = stockService.processTrade(ale, TradeType.BUY, 5, 20.0);
        log.info("ALE is bought successfully at:" + tradeTime);
        //SELL
        tradeTime = stockService.processTrade(ale, TradeType.SELL, 10, 10.0);
        log.info("ALE is sold successfully at:" + tradeTime);

        log.info("\n\n---Trades for stocks TEA/GIN/ALE completed ---\n");

        //calculate volume weighted stock price for last 15 mins trades

        double volumeWeightedStockPrice_tea = stockService.calculateVolWeightedStockPrice(tea);
        log.info("---VOLUME WEIGHTED STOCK PRICE FOR TEA:" + volumeWeightedStockPrice_tea);

        double volumeWeightedStockPrice_gin = stockService.calculateVolWeightedStockPrice(gin);
        log.info("---VOLUME WEIGHTED STOCK PRICE FOR GIN:" + volumeWeightedStockPrice_gin);

        double volumeWeightedStockPrice_ALE = stockService.calculateVolWeightedStockPrice(ale);
        log.info("---VOLUME WEIGHTED STOCK PRICE FOR ALE:" + volumeWeightedStockPrice_ALE);

        //calculate Al share index

        double shareIndex = stockService.calculateAllShareIndex(stocks);
        log.info("---ALL SHARE INDEX:" + shareIndex);

    }
}