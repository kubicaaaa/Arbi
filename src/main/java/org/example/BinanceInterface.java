package org.example;

import com.binance.connector.client.SpotClient;
import com.binance.connector.client.impl.SpotClientImpl;
import com.binance.connector.client.utils.signaturegenerator.HmacSignatureGenerator;
import java.util.LinkedHashMap;
import java.util.Map;

public class BinanceInterface {

    private final String apiKey;
    private final String secretKey;
    private SpotClient client;

    public BinanceInterface(String api, String secret) throws Exception {
        this.apiKey = api;
        this.secretKey = secret;
        getClient();
    }

    public void getClient() throws Exception {
        HmacSignatureGenerator signGenerator = new HmacSignatureGenerator(secretKey);
        this.client = new SpotClientImpl(apiKey, String.valueOf(signGenerator));
    }

    public void getBalance(){
        Map<String,Object> params = new LinkedHashMap<String,Object>();
        params.put("symbol","BTCUSDT");
        params.put("side", "SELL");
        params.put("type", "LIMIT");
        params.put("timeInForce", "GTC");
        params.put("quantity", 0.01);
        params.put("price", 9500);

        String result = client.createWallet().walletBalance(params);
        System.out.println(result);
    }

    public void withdraw(){
        Map<String,Object> params = new LinkedHashMap<String,Object>();
        params.put("symbol","BTCUSDT");
        params.put("side", "SELL");
        params.put("type", "LIMIT");
        params.put("timeInForce", "GTC");
        params.put("quantity", 0.01);
        params.put("price", 9500);

        String result = client.createWallet().withdraw(params);
    }

    public void placeOrder(){
        Map<String,Object> parameters = new LinkedHashMap<String,Object>();
        parameters.put("symbol","BTCUSDT");
        parameters.put("side", "SELL");
        parameters.put("type", "LIMIT");
        parameters.put("timeInForce", "GTC");
        parameters.put("quantity", 0.01);
        parameters.put("price", 9500);

        String result = client.createTrade().testNewOrder(parameters);
    }
}
