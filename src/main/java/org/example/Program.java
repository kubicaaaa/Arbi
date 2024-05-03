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

    public void start(){
        /*
            Main logic:
            1.
            Buying at cheaper exchange and transfering assets to more expensive to sell.
            Next withdrawal USDT to cheaper exchange to make transaction again.
         */
    }

    public String getBinanceApi() {
        return binanceApi;
    }

    public String getBinanceSecret() {
        return binanceSecret;
    }

    public String getBitgetApi() {
        return bitgetApi;
    }

    public String getBitgetPassphrase() {
        return bitgetPassphrase;
    }

    public String getBitgetSecret() {
        return bitgetSecret;
    }
}
