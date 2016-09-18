package test.morgan.service;

import lombok.Synchronized;
import org.springframework.stereotype.Service;
import test.morgan.exception.StockExceptions;
import test.morgan.modal.Stock;
import test.morgan.modal.Trade;
import test.morgan.modal.constants.TradeType;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.SortedMap;
import java.util.TreeMap;

/**
 * Service API definitions provides 5 operations implementatins
 */
@Service("stockService")
public class StockServiceImpl implements StockService {

    /****
     * Calculates Dividend yield for a Given Stock for a given price
     * @param stock
     * @param price
     * @return
     */
    @Override
    public double calculateDividendYield(Stock stock, double price) {
        switch (stock.getType()) {
            case COMMON:
                return stock.getLastDividend() / price;
            case PREFERRED:
                return stock.getFixedDividend() * stock.getParValue() / price;
            default:
                return 0.0;
        }
    }

    /***
     * Calculates Price by earning ratio for a given Stock for a given price
     * @param stock
     * @param price
     * @return
     * @throws StockExceptions
     */
    @Override
    public double calculatePriceByEarning(Stock stock, double price) throws StockExceptions {
        double priceByEarningPerShare = 0.0;
        if (stock.getLastDividend() == 0)
            throw new StockExceptions("The PE ratio for this stock doesn't exist");

        priceByEarningPerShare = price / stock.getLastDividend();
        return priceByEarningPerShare;
    }

    /****
     * Process a Buy or SELL trade requests synchronized in nature to provide consistency in Trade
     * @param stock
     * @param type
     * @param quantity
     * @param price
     * @return
     * @throws InterruptedException
     */
    @Override
    @Synchronized
    public LocalDateTime processTrade(Stock stock, TradeType type, int quantity, double price) throws InterruptedException {
        SortedMap trades = stock.getTrades();
        LocalDateTime currentTimeStamp = LocalDateTime.now();
        switch (type) {
            case BUY:
                trades.put(currentTimeStamp, new Trade(currentTimeStamp, quantity, type.BUY, price));
                stock.setLastTradedPrice(price);
                Thread.sleep(1000);
                break;
            case SELL:
                trades.put(currentTimeStamp, new Trade(currentTimeStamp, quantity, type.SELL, price));
                Thread.sleep(1000);
                stock.setLastTradedPrice(price);
                break;

        }
        return currentTimeStamp;
    }

    /***
     * Calculates the volume weighted stoc price for the trades within last 15 minutes
     * @param stock
     * @return
     */
    @Override
    public double calculateVolWeightedStockPrice(Stock stock) {

        double volWtStockPrice = 0.0;

        TreeMap trades = stock.getTrades();
        LocalDateTime fifteenMinsBefore = LocalDateTime.now().minusMinutes(15);

        // all transactions within last 15 minutes
        SortedMap<LocalDateTime, Trade> last15MinsTrades = trades.tailMap(fifteenMinsBefore);
        // Calculate
        Double totalPrice = 0.0;
        Integer totalQuantity = 0;

        totalQuantity = last15MinsTrades.values().stream().mapToInt(Trade::getQuantity).sum();
        totalPrice = last15MinsTrades.values().stream().mapToDouble(trade -> trade.getTradedPrice() * trade.getQuantity()).sum();

        volWtStockPrice = totalPrice / totalQuantity;

        return volWtStockPrice;
    }

    /***
     * Calculates All share index
     * @param stocks
     * @return
     */
    @Override
    public double calculateAllShareIndex(Collection<Stock> stocks) {
        double productsOfTradedPrices = stocks.stream().mapToDouble(trade -> trade.getLastTradedPrice()).reduce(1, (a, b) -> a * b);
        int numberfShare = stocks.size();
        double index = Math.pow(productsOfTradedPrices, 1.0 / numberfShare);
        return index;
    }
}
