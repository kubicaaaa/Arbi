package org.example;

import com.bitget.openapi.common.client.BitgetRestClient;
import com.bitget.openapi.common.domain.ClientParameter;
import com.bitget.openapi.common.enums.SupportedLocaleEnum;
import com.bitget.openapi.dto.response.ResponseResult;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableAsync;
import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;

@Configuration
@EnableAsync
public class BitgetInterface {

    private final String apiKey;
    private final String secretKey;
    private final String passphrase;
    private final String baseUrl;

    @Resource
    private BitgetRestClient bitgetRestClient;

    public BitgetInterface(String api, String secretApi, String passphrase) throws Exception {
        this.apiKey = api;
        this.secretKey = secretApi;
        this.passphrase = passphrase;
        this.baseUrl = "https://api.bitget.com";
        this.bitgetRestClient = bitgetRestClient();
    }

    @Bean
    public BitgetRestClient bitgetRestClient() throws Exception {
        ClientParameter parameter = ClientParameter.builder()
                .apiKey(apiKey)
                .secretKey(secretKey)
                .passphrase(passphrase)
                .baseUrl(baseUrl)
                .locale(SupportedLocaleEnum.ZH_CN.getName()).build();
        return BitgetRestClient.builder().configuration(parameter).build();
    }

    public double getBalance(String asset) throws Exception {
        Map<String, String> params = new HashMap();
        params.put("coin", asset);

        ResponseResult result = bitgetRestClient.bitget().v2().spotAccount().assets(params);
        String resultAsString = result.getData().toString();

        // Convert the string to valid JSON
        String json = resultAsString.replace("=", "\":\"")
                .replace(", ", "\", \"")
                .replace("{", "{\"")
                .replace("}", "\"}");

        ObjectMapper objectMapper = new ObjectMapper();
        JsonNode rootNode = objectMapper.readTree(json);
        JsonNode firstObject = rootNode.get(0);

        double balance = firstObject.get("available").asDouble();
        System.out.println("Checking Bitget balance...");
        System.out.println("Bitget " + asset + " balance: " + balance);

        return balance;
    }

    public void placeOrder(String asset, String side, String price, String amount) throws Exception {
        Map<String, String> params = new HashMap();
        params.put("symbol", asset + "USDT");
        params.put("side", side);
        params.put("orderType", "limit");
        params.put("force", "gtc");
        params.put("price", price);
        params.put("size", amount);

        ResponseResult result = bitgetRestClient.bitget().v2().spotOrder().placeOrder(params);
        String resultAsString = result.getData().toString();
        System.out.println("Placing " + side + " order at Bitget to " + side + " " + amount + " " + asset + "...");
        System.out.println(resultAsString);
    }

    public void withdraw(String asset, String address, String chain, String amount) throws Exception {
        Map<String, String> params = new HashMap();
        params.put("coin", asset);
        params.put("transferType", "on_chain");
        params.put("address", address);
        params.put("chain", chain);
        params.put("size", amount);

        ResponseResult result = bitgetRestClient.bitget().v2().spotWallet().withdrawal(params);
        String resultAsString = result.getData().toString();
        System.out.println("Withdrawing " + amount + " " + asset + " to Binance...");
        System.out.println(resultAsString);
    }
}
