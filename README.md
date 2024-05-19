Arbitrage Trading Bot
This is a Java project that implements an arbitrage trading bot between Binance and Bitget cryptocurrency exchanges. The bot buys a specified cryptocurrency on Binance and sells it on Bitget at a higher price, aiming to profit from the price difference between the two exchanges.
Prerequisites
Java Development Kit (JDK) 21 or higher
Maven
Dependencies
The project relies on the following dependencies:
ch.qos.logback:logback-classic for logging
com.alibaba:fastjson for JSON parsing
org.apache.commons:commons-collections4 for utility classes
com.squareup.okhttp3:okhttp for HTTP client
org.apache.commons:commons-lang3 for utility classes
com.squareup.retrofit2:retrofit and com.squareup.retrofit2:converter-gson for REST client
com.vaadin.external.google:android-json for JSON parsing
io.github.binance:binance-connector-java for Binance API integration
com.bitget.openapi:bitget-java-sdk-api for Bitget API integration
org.testng:testng for testing
org.springframework:spring-context and org.springframework:spring-core for Spring framework
javax.annotation:jsr250-api for annotations
org.junit.jupiter:junit-jupiter-api for testing
com.fasterxml.jackson.core:jackson-core and com.fasterxml.jackson.core:jackson-databind for JSON parsing
Building the Project
Clone the repository or download the source code.
Navigate to the project directory.
Build the project using Maven:
mvn clean install

Running the Bot
Open the UserInterface.java file and replace the placeholders with your actual API keys, secrets, and addresses:
java
String binanceApi = "INSERT YOUR BINANCE API KEY";
String binanceSecret = "INSERT YOUR BINANCE SECRET API KEY";
String bitgetApi = "INSERT YOUR BITGET API KEY";
String bitgetSecret = "INSERT YOUR BITGET SECRET API KEY";
String bitgetPassphrase = "INSERT YOUR BITGET PASSPHRASE";
String binanceUsdtAddress = "INSERT YOUR BINANCE USDT ADDRESS";
String bitgetAssetAddress = "INSERT YOUR BITGET ASSET ADDRESS";

Compile and run the UserInterface.java file.
How it Works
The program fetches the current ask price for the specified cryptocurrency from the Binance exchange.
It calculates the bid price for the Bitget exchange, taking into account various fees and ensuring a profitable transaction.
The program buys the cryptocurrency on Binance using the available USDT balance.
After the purchase is successful, it withdraws the purchased cryptocurrency from Binance and deposits it into the Bitget exchange.
Once the cryptocurrency is available on Bitget, the program sells it at the calculated bid price.
The USDT proceeds from the sale are withdrawn from Bitget and deposited back into the Binance account.
The process repeats indefinitely, with a 5-second delay between each iteration.
Note
The program assumes that you have sufficient USDT balance on Binance to initiate the trades.
It may take some time for the withdrawals and deposits to complete, so the program waits for 3 minutes after each withdrawal before proceeding to the next step.
The program is designed to trade a single cryptocurrency specified in the asset variable. Modify this variable to trade a different cryptocurrency.
Disclaimer
This program is provided for educational purposes only. Use it at your own risk. The authors are not responsible for any financial losses or legal consequences resulting from the use of this program.
