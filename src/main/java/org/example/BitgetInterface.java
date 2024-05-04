package org.example;

import com.bitget.openapi.common.client.BitgetRestClient;
import com.bitget.openapi.common.domain.ClientParameter;
import com.bitget.openapi.common.enums.SupportedLocaleEnum;
import com.bitget.openapi.dto.response.ResponseResult;
import org.json.JSONObject;
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

    public BitgetInterface(String api, String secretApi, String passphrase) {
        this.apiKey = api;
        this.secretKey = secretApi;
        this.passphrase = passphrase;
        this.baseUrl = "https://api.bitget.com";
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

    public void getBalance() throws Exception {
        Map<String, String> params = new HashMap();
        params.put("symbol", "BTCUSDT_UMCBL");
        params.put("marginCoin", "USDT");
        params.put("side", "open_long");
        params.put("orderType", "limit");
        params.put("price", "27012.1");
        params.put("size", "0.01");
        params.put("timInForceValue", "normal");

        ResponseResult result = bitgetRestClient.bitget().v2().spotAccount().assets(params);
        System.out.println(new JSONObject(String.valueOf(result)));
    }

    public void withdraw() throws Exception {
        Map<String, String> params = new HashMap();
        params.put("symbol", "BTCUSDT_UMCBL");
        params.put("marginCoin", "USDT");
        params.put("side", "open_long");
        params.put("orderType", "limit");
        params.put("price", "27012.1");
        params.put("size", "0.01");
        params.put("timInForceValue", "normal");

        ResponseResult result = bitgetRestClient.bitget().v2().spotWallet().withdrawal(params);
        System.out.println(new JSONObject(String.valueOf(result)));
    }

    public void placeOrder() throws Exception {
        Map<String, String> params = new HashMap();
        params.put("symbol", "BTCUSDT_UMCBL");
        params.put("marginCoin", "USDT");
        params.put("side", "open_long");
        params.put("orderType", "limit");
        params.put("price", "27012.1");
        params.put("size", "0.01");
        params.put("timInForceValue", "normal");

        ResponseResult result = bitgetRestClient.bitget().v1().mixOrder().placeOrder(params);
        System.out.println(new JSONObject(String.valueOf(result)));
    }
}
