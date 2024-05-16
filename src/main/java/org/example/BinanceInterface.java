package org.example;

import com.binance.connector.client.SpotClient;
import com.binance.connector.client.impl.SpotClientImpl;
import java.time.Instant;
import java.util.LinkedHashMap;
import java.util.Map;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

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

    public double getBalance(String asset) throws JsonProcessingException {
        Map<String,Object> params = new LinkedHashMap<>();
        params.put("asset", asset);
        String timestamp = String.valueOf(Instant.now().toEpochMilli());
        params.put("timestamp", timestamp);

        String result = client.createWallet().getUserAsset(params);
        ObjectMapper objectMapper = new ObjectMapper();
        JsonNode rootNode = objectMapper.readTree(result);
        JsonNode firstObject = rootNode.get(0);

        double balance = firstObject.get("free").asDouble();
        System.out.println("Checking Binance balance...");
        System.out.println("Binance " + asset + " balance: " + balance);

        return balance;
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
        System.out.println("Placing " + side + " order at Binance to " + side + " " + amount + " " + asset + "...");
        System.out.println(result);
    }

    public void withdraw(String coin, String network, String address, double amount){
        Map<String,Object> params = new LinkedHashMap<String,Object>();
        params.put("coin", coin);
        params.put("network", network);
        params.put("address", address);
        params.put("amount", amount);
        String timestamp = String.valueOf(Instant.now().toEpochMilli());
        params.put("timestamp", timestamp);

        String result = client.createWallet().withdraw(params);
        System.out.println("Withdrawing " + amount + " " + coin + " to Bitget...");
        System.out.println(result);
    }
}
