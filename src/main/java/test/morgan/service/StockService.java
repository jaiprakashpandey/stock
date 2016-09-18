package test.morgan.service;

import test.morgan.exception.StockExceptions;
import test.morgan.modal.Stock;
import test.morgan.modal.constants.TradeType;

import java.time.LocalDateTime;
import java.util.Collection;

/**
 * Service API declarations
 */
public interface StockService {

    public double calculateDividendYield(Stock stock, double price);

    public double calculatePriceByEarning(Stock stock, double price) throws StockExceptions;

    public LocalDateTime processTrade(Stock stock, TradeType type, int quantity, double price) throws InterruptedException;

    public double calculateVolWeightedStockPrice(Stock stock);

    public double calculateAllShareIndex(Collection<Stock> stocks);
}
