# Arbi - Cryptocurrency Arbitrage Trading Bot

A Java-based automated arbitrage trading bot that exploits price differences between Binance and Bitget cryptocurrency exchanges to generate profit.

![arbi](https://github.com/kubicaaaa/Arbi/assets/136459875/c5b043da-f32b-49d9-bde7-db68cd1effbc)

## Overview

Arbi automatically identifies and executes arbitrage opportunities by buying cryptocurrency on Binance at a lower price and selling it on Bitget at a higher price. The bot handles the entire trading cycle including order placement, withdrawals, deposits, and profit calculation.

## How It Works

The arbitrage cycle follows these steps:

1. **Price Monitoring**: Continuously fetches the ask price from Binance and calculates the optimal bid price for Bitget
2. **Purchase**: Buys the cryptocurrency on Binance using available USDT balance
3. **Transfer to Bitget**: Withdraws the purchased cryptocurrency from Binance and deposits it into Bitget
4. **Sale**: Sells the cryptocurrency on Bitget at the calculated profitable bid price
5. **Transfer to Binance**: Withdraws the USDT proceeds from Bitget back to Binance
6. **Repeat**: The cycle repeats indefinitely with a 5-second delay between iterations

## Features

- Automated arbitrage trading between two major exchanges
- Dynamic fee calculation to ensure profitable transactions
- Automatic balance checking and order placement
- Withdrawal and deposit handling
- Built-in safety delays for transaction confirmation
- Continuous operation with automatic restart

## Configuration

Before running the bot, you need to configure the following in `UserInterface.java`:

```java
String asset = "IOTX";  // The cryptocurrency to trade
String binanceApi = "INSERT YOUR BINANCE API KEY";
String binanceSecret = "INSERT YOUR BINANCE SECRET API KEY";
String bitgetApi = "INSERT YOUR BITGET API KEY";
String bitgetSecret = "INSERT YOUR BITGET SECRET API KEY";
String bitgetPassphrase = "INSERT YOUR BITGET PASSPHRASE";
String binanceUsdtAddress = "INSERT YOUR BINANCE USDT ADDRESS";
String bitgetAssetAddress = "INSERT YOUR BITGET ASSET ADDRESS";
```

## Project Structure

```
Arbi/
├── src/
│   ├── main/
│   │   └── java/
│   │       └── org/example/
│   │           ├── BinanceInterface.java    # Binance API integration
│   │           ├── BitgetInterface.java     # Bitget API integration
│   │           ├── Program.java             # Main trading logic
│   │           └── UserInterface.java       # Entry point and configuration
│   └── test/
│       └── java/
│           ├── BinanceInterfaceTests.java   # Binance API tests
│           └── BitgetInterfaceTests.java    # Bitget API tests
├── pom.xml
└── README.md
```

## Key Components

### BinanceInterface
Handles all interactions with the Binance exchange:
- Balance checking
- Order placement
- Cryptocurrency withdrawals

### BitgetInterface
Manages Bitget exchange operations:
- Balance verification
- Limit order placement
- USDT and cryptocurrency withdrawals

### Program
Contains the core arbitrage trading logic:
- Coordinates buy and sell operations
- Manages transfers between exchanges
- Implements waiting periods for transaction confirmation

### UserInterface
Application entry point that:
- Fetches real-time price data
- Calculates profitable bid prices with fee consideration
- Initiates and monitors the trading cycle

## Fee Calculation

The bot automatically accounts for all trading and withdrawal fees:
- Binance taker fee: 0.075%
- Bitget taker fee: 0.080%
- Bitget USDT withdrawal fee: $0.29
- Asset withdrawal fees from both exchanges: $0.10 each

The bid price is calculated to ensure profitability after all fees are deducted.

## Safety Features

- 10-second balance check intervals to confirm transactions
- 3-minute waiting periods after withdrawals to ensure deposits are completed
- Automatic retry logic for balance verification
- Minimum balance thresholds before proceeding with operations

## Dependencies

Key dependencies include:
- Binance Connector Java (3.2.0)
- Bitget Java SDK (1.0.1-SNAPSHOT)
- Spring Framework (6.1.5)
- Jackson for JSON processing
- OkHttp for HTTP requests
- Retrofit for API calls

See `pom.xml` for the complete list of dependencies.

## Limitations

- Requires manual configuration of API credentials
- Hardcoded asset (IOTX) - requires code modification for different cryptocurrencies
- No built-in risk management or stop-loss mechanisms
- Sensitive to network latency and API rate limits
- Assumes sufficient liquidity on both exchanges

## Support

For questions or issues, please open an issue on the GitHub repository.
