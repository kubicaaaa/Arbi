package org.example;

public class Program {

    private String binanceApi;
    private String binanceSecret;
    private String bitgetApi;
    private String bitgetSecret;
    private String bitgetPassphrase;

    public Program(String binanceApi, String binanceSecret, String bitgetApi, String bitgetPassphrase, String bitgetSecret) {
        this.binanceApi = binanceApi;
        this.binanceSecret = binanceSecret;
        this.bitgetApi = bitgetApi;
        this.bitgetPassphrase = bitgetPassphrase;
        this.bitgetSecret = bitgetSecret;

    }

    public void start() throws Exception {
        BitgetInterface bitget = new BitgetInterface(bitgetApi, bitgetSecret, bitgetPassphrase);
        BinanceInterface binance = new BinanceInterface(binanceApi, binanceSecret);
        binance.getBalance(); // USDT BALANCE
        binance.placeOrder(); // BUYING
        binance.getBalance(); // ASSET BALANCE
        binance.withdraw(); // ASSET WITHDRAWAL
        Thread.sleep(60000);
        bitget.getBalance(); //  ASSET BALANCE
        bitget.placeOrder(); // SELLING
        bitget.getBalance(); // USDT BALANCE
        bitget.withdraw(); // USDT WITHDRAWAL
        Thread.sleep(60000);
    }
}
