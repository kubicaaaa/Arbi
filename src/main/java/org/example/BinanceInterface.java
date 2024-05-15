package org.example;

import com.binance.connector.client.SpotClient;
import com.binance.connector.client.impl.SpotClientImpl;
import java.time.Instant;
import java.util.LinkedHashMap;
import java.util.Map;

public class BinanceInterface {

    private final String apiKey;
    private final String secretKey;
    private SpotClient client;

    public BinanceInterface(String api, String secret) throws Exception {
        this.apiKey = api;
        this.secretKey = secret;
        this.client = getClient();
    }

    public SpotClient getClient() throws Exception {
       return new SpotClientImpl(apiKey, secretKey);
    }

    public void getBalance(String asset){
        Map<String,Object> params = new LinkedHashMap<String,Object>();
        params.put("asset", asset);
        String timestamp = String.valueOf(Instant.now().toEpochMilli());
        params.put("timestamp", timestamp);

        String result = client.createWallet().getUserAsset(params);
        System.out.println(result);
    }

    public void placeOrder(String asset, String side, double amount, double price){
        Map<String,Object> parameters = new LinkedHashMap<>();
        parameters.put("symbol", asset + "USDT");
        parameters.put("side", side);
        parameters.put("type", "LIMIT");
        parameters.put("timeInForce", "GTC");
        parameters.put("quantity", amount);
        parameters.put("price", price);

        String result = client.createTrade().newOrder(parameters);
        System.out.println(result);
    }

    public void withdraw(String coin, String network, String address, double amount){ // USDT transfer
        Map<String,Object> params = new LinkedHashMap<String,Object>();
        params.put("coin",coin);
        params.put("network",network);
        params.put("address",address);
        params.put("amount",amount);
        String timestamp = String.valueOf(Instant.now().toEpochMilli());
        params.put("timestamp", timestamp);

        String result = client.createWallet().withdraw(params);
        System.out.println(result);
    }
}
