package test.morgon.stock;

import lombok.Data;
import test.morgan.modal.Stock;
import test.morgan.modal.constants.StockType;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Jai on 9/17/2016.
 */
@Data
public class StockDataHelper {

    Map<String, Stock> stocks = new HashMap<>();


    public static Map<String, Stock> getStocks() {
        HashMap<String, Stock> someStocksData = new HashMap<>();
        someStocksData.put("TEA", new Stock("TEA", StockType.COMMON, 0.0, 0.0, 100.0));
        someStocksData.put("ALE", new Stock("ALE", StockType.COMMON, 23.0, 0.0, 60.0));
        someStocksData.put("GIN", new Stock("GIN", StockType.PREFERRED, 8.0, 0.02, 100.0));

        return someStocksData;
    }
}
