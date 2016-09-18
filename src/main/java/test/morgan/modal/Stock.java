package test.morgan.modal;

import lombok.Data;
import test.morgan.modal.constants.StockType;

import java.time.LocalDateTime;
import java.util.TreeMap;

/**
 * placeholder of particular Stock instance
 */
@Data
public class Stock {

    private String symbol;
    private StockType type;
    private double lastDividend;
    private double fixedDividend;
    private double parValue;
    private double lastTradedPrice;

    public Stock(String symbol, StockType type, double lastDividend, double fixedDividend, double parValue) {
        this.symbol = symbol;
        this.type = type;
        this.lastDividend = lastDividend;
        this.fixedDividend = fixedDividend;
        this.parValue = parValue;
    }

    private TreeMap<LocalDateTime, Trade> trades = new TreeMap<>();

}
