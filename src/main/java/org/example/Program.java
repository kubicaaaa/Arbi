package org.example;

public class Program {

    private final String asset;
    private final String binanceApi;
    private final String binanceSecret;
    private final String bitgetApi;
    private final String bitgetSecret;
    private final String bitgetPassphrase;
    private final String binanceAsset;
    private final String binanceUsdt;
    private final String bitgetAsset;
    private final String bitgetUsdt;
    private double askPrice;
    private double bidPrice;

    public Program(String asset, String binanceApi, String binanceSecret, String bitgetApi, String bitgetSecret,
                   String bitgetPassphrase, String binanceAsset, String binanceUsdt,
                   String bitgetAsset, String bitgetUsdt) {
        this.asset = asset;
        this.binanceApi = binanceApi;
        this.binanceSecret = binanceSecret;
        this.bitgetApi = bitgetApi;
        this.bitgetSecret = bitgetSecret;
        this.bitgetPassphrase = bitgetPassphrase;
        this.binanceAsset = binanceAsset;
        this.binanceUsdt = binanceUsdt;
        this.bitgetAsset = bitgetAsset;
        this.bitgetUsdt = bitgetUsdt;
    }

    public void setAskPrice(double askPrice) {
        this.askPrice = askPrice;
    }

    public void setBidPrice(double bidPrice) {
        this.bidPrice = bidPrice;
    }

    public void start() throws Exception {
        BitgetInterface bitget = new BitgetInterface(bitgetApi, bitgetSecret, bitgetPassphrase);
        BinanceInterface binance = new BinanceInterface(binanceApi, binanceSecret);

        binance.getBalance("USDT"); // USDT BALANCE

        binance.placeOrder("IOTX", "BUY", 0, askPrice); // BUYING

        binance.getBalance("IOTX"); // ASSET BALANCE

        binance.withdraw("IOTX", "IOTX", bitgetAsset, 0); // ASSET WITHDRAWAL

        Thread.sleep(180000); // 3 minutes break to let transaction arrive

        bitget.getBalance("IOTX"); //  ASSET BALANCE


        bitget.placeOrder(asset, "sell", "", ""); // SELLING
        bitget.getBalance("USDT"); // USDT BALANCE
        bitget.withdraw("USDT", binanceUsdt, "bep20", ""); // USDT WITHDRAWAL

        Thread.sleep(180000); // 3 minutes break to let transaction arrive
    }
}
