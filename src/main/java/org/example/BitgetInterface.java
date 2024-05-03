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
        Map<String, String> paramMap = new HashMap();
        paramMap.put("symbol", "BTCUSDT_UMCBL");
        paramMap.put("marginCoin", "USDT");
        paramMap.put("side", "open_long");
        paramMap.put("orderType", "limit");
        paramMap.put("price", "27012.1");
        paramMap.put("size", "0.01");
        paramMap.put("timInForceValue", "normal");
        ResponseResult result = bitgetRestClient.bitget().v2().spotAccount().assets(paramMap);
        System.out.println(new JSONObject(String.valueOf(result)));
    }

    public void withdraw() throws Exception {
        Map<String, String> paramMap = new HashMap();
        paramMap.put("symbol", "BTCUSDT_UMCBL");
        paramMap.put("marginCoin", "USDT");
        paramMap.put("side", "open_long");
        paramMap.put("orderType", "limit");
        paramMap.put("price", "27012.1");
        paramMap.put("size", "0.01");
        paramMap.put("timInForceValue", "normal");
        ResponseResult result = bitgetRestClient.bitget().v2().spotWallet().withdrawal(paramMap);
        System.out.println(new JSONObject(String.valueOf(result)));
    }

    public void placeOrder() throws Exception {
        Map<String, String> paramMap = new HashMap();
        paramMap.put("symbol", "BTCUSDT_UMCBL");
        paramMap.put("marginCoin", "USDT");
        paramMap.put("side", "open_long");
        paramMap.put("orderType", "limit");
        paramMap.put("price", "27012.1");
        paramMap.put("size", "0.01");
        paramMap.put("timInForceValue", "normal");
        ResponseResult result = bitgetRestClient.bitget().v1().mixOrder().placeOrder(paramMap);
        System.out.println(new JSONObject(String.valueOf(result)));
    }
}
