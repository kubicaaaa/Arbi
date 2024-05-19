package org.example;

public class Program {

    private final String asset;
    private final String binanceApi;
    private final String binanceSecret;
    private final String bitgetApi;
    private final String bitgetSecret;
    private final String bitgetPassphrase;
    private final String binanceUsdt;
    private final String bitgetAsset;
    private double askPrice;
    private double bidPrice;

    public Program(String asset, String binanceApi, String binanceSecret, String bitgetApi, String bitgetSecret,
                   String bitgetPassphrase, String binanceUsdt, String bitgetAsset) {
        this.asset = asset;
        this.binanceApi = binanceApi;
        this.binanceSecret = binanceSecret;
        this.bitgetApi = bitgetApi;
        this.bitgetSecret = bitgetSecret;
        this.bitgetPassphrase = bitgetPassphrase;
        this.binanceUsdt = binanceUsdt;
        this.bitgetAsset = bitgetAsset;
    }

    public void start() throws Exception {
        BitgetInterface bitget = new BitgetInterface(bitgetApi, bitgetSecret, bitgetPassphrase);
        BinanceInterface binance = new BinanceInterface(binanceApi, binanceSecret);

        int amount = 0;
        while (amount < 1) {
            amount = (int) (binance.getBalance("USDT") / askPrice); // USDT BALANCE
            Thread.sleep(10000);
        }

        binance.placeOrder(asset, "BUY", amount, askPrice); // BUYING

        double assetAmount = 0;
        while (assetAmount < 1) { // CHECKING EVERY 10 SECONDS IF ASSETS ARE BOUGHT
            assetAmount = binance.getBalance(asset); // ASSET BALANCE
            Thread.sleep(10000);
        }

        binance.withdraw(asset, "IOTX", bitgetAsset, assetAmount); // ASSET WITHDRAWAL

        Thread.sleep(180000); // 3 minutes break to let assets be deposited

        int value = 0;
        while (value < 1) {
            value = (int) (bitget.getBalance(asset) / bidPrice); //  ASSET BALANCE
            Thread.sleep(10000);
        }

        bitget.placeOrder(asset, "sell", "", String.valueOf(value)); // SELLING

        double usdtBalance = 0;
        while (usdtBalance < 1) {
            usdtBalance = bitget.getBalance(asset); // USDT BALANCE
            Thread.sleep(10000);
        }

        bitget.withdraw(asset, binanceUsdt, "bep20", String.valueOf(usdtBalance)); // USDT WITHDRAWAL

        Thread.sleep(180000); // 3 minutes break to let assets be deposited
    }

    public void setAskPrice(double askPrice) {
        this.askPrice = askPrice;
    }

    public void setBidPrice(double bidPrice) {
        this.bidPrice = bidPrice;
    }
}
