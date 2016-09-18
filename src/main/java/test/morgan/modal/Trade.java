package test.morgan.modal;

import lombok.Data;
import test.morgan.modal.constants.TradeType;

import java.time.LocalDateTime;

/**
 * placeholder of particular Trade instance
 */
@Data
public class Trade {

    private LocalDateTime timeStamp;
    private int quantity;
    private TradeType tradeType;
    private double tradedPrice;

    public Trade(LocalDateTime timeStamp, int quantity, TradeType tradeType, double tradedPrice) {
        this.timeStamp = timeStamp;
        this.quantity = quantity;
        this.tradeType = tradeType;
        this.tradedPrice = tradedPrice;
    }
}
