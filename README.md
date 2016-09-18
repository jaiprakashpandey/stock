## Super Simple Stock Market application

This is simple stock market application that provide certain stock market operations for a given stocks and given price.

It contains below 5 operations 
1.calculates DIVIDEND YIELD for a stock
2.calculates P/E ratio for a stock
3.calculates volume weighted stock price for the trades within last 15 minutes
4.records Trades
5.calculates share index for all trades


## Main application class file name resides in package test.morgan.stock that initiates the stock market operations
  StockApplication.java

## Resources used for application
As this is simple application, there are currently no resource file to control the application.

## Exceptions
for the stocks which has no dividend declared or company doesn't distribute, the application throws and logs indication messages that P/E ratio doesn't exist.

## Synchronization
Even this is simple application though while doing trades transaction care has been taken that operation is marked Synchronized to provide consistency while BUY or SELL of trades

## Software used to develop & build the application:
OS 32/64 bit
JAVA 1.8(jdk1.8.0_91)
Spring Boot 1.4
Maven 3.0.5
Junit 4.11
Lombok
Intellij 2015

## To build the application:
 mvn clean install
 mvn verify test

## Test cases
Tests covers almost all expected operations mentioned in the assignment.
There are 5 test cases run when application is built, any changes in application can be easily caught by tests.

## To execute the application:
java -jar stockmarket-1.0-SNAPSHOT.jar


## sample output below:

---Starting Super Simple Stock Market application---

2016-09-19 00:22:46.821  INFO 1108 --- [           main] test.morgon.stock.StockApplication       : --YIELD FOR STOCK: TEA is 0.0 for price: 10.2
2016-09-19 00:22:46.822  INFO 1108 --- [           main] test.morgon.stock.StockApplication       : --The PE ratio for this stock doesn't exist: TEA
2016-09-19 00:22:46.822  INFO 1108 --- [           main] test.morgon.stock.StockApplication       : --YIELD FOR STOCK: ALE is 2.2549019607843137 for price: 10.2
2016-09-19 00:22:46.822  INFO 1108 --- [           main] test.morgon.stock.StockApplication       : --P/E RATIO FOR STOCK: ALE is 0.4434782608695652 for price: 10.2
2016-09-19 00:22:46.822  INFO 1108 --- [           main] test.morgon.stock.StockApplication       : --YIELD FOR STOCK: GIN is 0.19607843137254904 for price: 10.2
2016-09-19 00:22:46.823  INFO 1108 --- [           main] test.morgon.stock.StockApplication       : --P/E RATIO FOR STOCK: GIN is 1.275 for price: 10.2
2016-09-19 00:22:46.823  INFO 1108 --- [           main] test.morgon.stock.StockApplication       :

--Initiating trades for stocks TEA/GIN/ALE---

2016-09-19 00:22:46.823  INFO 1108 --- [           main] test.morgon.stock.StockApplication       :
--Buying and selling each stock types at price 20.0 and 10.0 respectively ---

2016-09-19 00:22:47.836  INFO 1108 --- [           main] test.morgon.stock.StockApplication       : TEA is bought successfully at:2016-09-19T00:22:46.832
2016-09-19 00:22:48.837  INFO 1108 --- [           main] test.morgon.stock.StockApplication       : TEA is sold successfully at:2016-09-19T00:22:47.836
2016-09-19 00:22:49.838  INFO 1108 --- [           main] test.morgon.stock.StockApplication       : gin is bought successfully at:2016-09-19T00:22:48.838
2016-09-19 00:22:50.846  INFO 1108 --- [           main] test.morgon.stock.StockApplication       : gin is sold successfully at:2016-09-19T00:22:49.846
2016-09-19 00:22:51.847  INFO 1108 --- [           main] test.morgon.stock.StockApplication       : ALE is bought successfully at:2016-09-19T00:22:50.847
2016-09-19 00:22:52.848  INFO 1108 --- [           main] test.morgon.stock.StockApplication       : ALE is sold successfully at:2016-09-19T00:22:51.848
2016-09-19 00:22:52.848  INFO 1108 --- [           main] test.morgon.stock.StockApplication       :

---Trades for stocks TEA/GIN/ALE completed ---

2016-09-19 00:22:52.876  INFO 1108 --- [           main] test.morgon.stock.StockApplication       : ---VOLUME WEIGHTED STOCK PRICE FOR TEA:16.666666666666668
2016-09-19 00:22:52.876  INFO 1108 --- [           main] test.morgon.stock.StockApplication       : ---VOLUME WEIGHTED STOCK PRICE FOR GIN:15.0
2016-09-19 00:22:52.877  INFO 1108 --- [           main] test.morgon.stock.StockApplication       : ---VOLUME WEIGHTED STOCK PRICE FOR ALE:13.333333333333334
2016-09-19 00:22:52.880  INFO 1108 --- [           main] test.morgon.stock.StockApplication       : ---ALL SHARE INDEX:9.999999999999998
2016-09-19 00:22:52.881  INFO 1108 --- [           main] test.morgon.stock.StockApplication       :

---Super Simple Stock Market application completed---