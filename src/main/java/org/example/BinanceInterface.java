package example;

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

    public double getBalance(String asset){
        Map<String,Object> params = new LinkedHashMap<String,Object>();
        params.put("coin", asset);
        String timestamp = String.valueOf(Instant.now().toEpochMilli());
        params.put("timestamp", timestamp);

        String result = client.createWallet().getUserAsset(params);
        System.out.println(result);

        return 0;
    }

    public void withdraw(String coin, String address, double amount){
        Map<String,Object> params = new LinkedHashMap<String,Object>();
        params.put("coin",coin);
        params.put("address",address);
        params.put("amount",amount);
        params.put("timestamp", "GTC");

        String result = client.createWallet().withdraw(params);
        System.out.println(result);
    }

    public void placeOrder(String symbol, String side, double amount, double price){
        Map<String,Object> parameters = new LinkedHashMap<>();
        parameters.put("symbol","BTCUSDT");
        parameters.put("side", "SELL");
        parameters.put("type", "LIMIT");
        parameters.put("timeInForce", "GTC");
        parameters.put("quantity", 0.01);
        parameters.put("price", 9500);

        String result = client.createTrade().testNewOrder(parameters);
        System.out.println(result);
    }
}
