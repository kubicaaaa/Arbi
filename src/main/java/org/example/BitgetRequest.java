package org.example;

import com.bitget.openapi.common.client.BitgetRestClient;
import com.bitget.openapi.common.domain.ClientParameter;
import com.bitget.openapi.common.enums.SupportedLocaleEnum;
import com.bitget.openapi.dto.response.ResponseResult;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableAsync;
import javax.annotation.Resource;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@Configuration
@EnableAsync
public class BitgetRequest {

    private final String apiKey;
    private final String secretKey;
    private final String passphrase;
    private String request;
    private final String baseUrl;

    @Resource
    private BitgetRestClient bitgetRestClient;

    public BitgetRequest(Program app, String request) {
        this.apiKey = app.getBitgetApi();
        this.secretKey = app.getBitgetSecret();
        this.passphrase = app.getBitgetPassphrase();
        this.request = request;
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


    // withdrawal, check balance, place order
    public void executeRequest() throws Exception {
        BitgetRestClient bitgetRestClient = bitgetRestClient();
        Map<String, String> paramMap = new HashMap();
        paramMap.put("symbol", "BTCUSDT_UMCBL");
        paramMap.put("marginCoin", "USDT");
        paramMap.put("side", "open_long");
        paramMap.put("orderType", "limit");
        paramMap.put("price", "27012.1");
        paramMap.put("size", "0.01");
        paramMap.put("timInForceValue", "normal");
        ResponseResult result = bitgetRestClient.bitget().v1().mixOrder().placeOrder(paramMap);
        ResponseResult result = bitgetRestClient.bitget().v2().spotWallet().withdrawal(paramMap);
        ResponseResult result = bitgetRestClient.bitget().v2().spotAccount().assets(paramMap);
        System.out.println(new JSONObject(String.valueOf(result)));
    }
}
