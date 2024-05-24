Arbitrage Trading Bot


This Java project implements an arbitrage trading bot between Binance and Bitget cryptocurrency exchanges. Arbi buys a specified cryptocurrency on Binance and sells it on Bitget at a higher price, aiming to profit from the price difference between the two exchanges.



![arbi](https://github.com/kubicaaaa/Arbi/assets/136459875/c5b043da-f32b-49d9-bde7-db68cd1effbc)

#
How it Works


The program fetches the current ask price for the specified cryptocurrency from the Binance exchange.
It calculates the bid price for the Bitget exchange, taking into account various fees and ensuring a profitable transaction.
The program buys the cryptocurrency on Binance using the available USDT balance.
After the purchase is successful, it withdraws the purchased cryptocurrency from Binance and deposits it into the Bitget exchange.
Once the cryptocurrency is available on Bitget, the program sells it at the calculated bid price.
The USDT proceeds from the sale are withdrawn from Bitget and deposited back into the Binance account.
The process repeats indefinitely, with a 5-second delay between each iteration.
