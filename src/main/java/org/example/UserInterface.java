package example;

import org.json.JSONException;
import org.json.JSONObject;
import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class UserInterface {

    public static JSONObject getData(String url) throws JSONException, IOException, InterruptedException {

        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(url))
                .GET()
                .build();
        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

        return new JSONObject(response.body());
    }

    public static float getProfit(float askPrice, float bidPrice, int value) {

        // USDT withdrawal fees
        float binanceUsdtFee = 0.14f;
        float bitgetUsdtFee = 0.29f;

        // Asset withdrawal fees
        float assetBinanceFee = 0;
        float assetBitgetFee = 0;

        // SPOT fees
        float binanceTakerFee = 0.00075f * value;
        float bitgetTakerFee = 0.00080f * value;

        // Calculating profit (difference between exchanges incl. withdrawal fees and maker/taker)
        float buyQuantity = value / askPrice;
        float sellQuantity = value / bidPrice;
        float profit = bidPrice * sellQuantity - askPrice * buyQuantity;
        profit -= bitgetUsdtFee;
        profit -= binanceTakerFee + bitgetTakerFee;
        profit -= assetBinanceFee * askPrice + assetBitgetFee * bidPrice;
        System.out.println(profit);

        return profit;
    }

    public static void main(String[] args) throws Exception {

        String asset = "IOTX";
        String binanceApi = "bxycYuVHCyXhV9DmrmBIlbhiEMyDnGdoejam7nmtsVP3gXl2mTtAcT81rTv0zTmM";
        String binanceSecret = "aszXOFFh5lxCpl8Q4noypIHCRQDHVZAdxwP4GemPa4ttiDhskmvSrzJjCn6irVGV";
        String bitgetApi = "bg_edf992931da5f6ac58a39466b99763df";
        String bitgetSecret = "51de7f3c3969ceb9469556f4a6b9b0b097b02dcfe8307c33666a9b4590438944";
        String bitgetPassphrase = "fabiaN342196";
        String binanceAssetAddress = "";
        String binanceUsdtAddress = "0x78b7ce5c646eed436badf902838c7222221c6bd2"; // BEP20
        String bitgetAssetAddress = "";
        String bitgetUsdtAddress = ""; // AVAXC
        Program app = new Program(asset, binanceApi, binanceSecret, bitgetApi, bitgetSecret, bitgetPassphrase,
                binanceAssetAddress, binanceUsdtAddress, bitgetAssetAddress, bitgetUsdtAddress);

        while (true) {
            // BINANCE API request
            JSONObject binanceData = getData("https://api.binance.com/api/v3/ticker/bookTicker?symbol=" + asset + "USDT");

            // BITGET API request
            JSONObject bitgetResponse = getData("https://api.bitget.com/api/spot/v1/market/ticker?symbol=" + asset + "USDT_SPBL");
            JSONObject bitgetData = bitgetResponse.getJSONObject("data");

            // Selling
            double binanceBid = binanceData.getDouble("bidPrice");
            double bitgetBid = bitgetData.getDouble("sellOne");
            // Buying
            double bitgetAsk = bitgetData.getDouble("buyOne");
            double binanceAsk = binanceData.getDouble("askPrice");

            System.out.println("Bitget BUY price: " + bitgetAsk);
            System.out.println("Binance BUY price: " + binanceAsk);
            System.out.println("Bitget SELL price: " + bitgetBid);
            System.out.println("Binance SELL price: " + binanceBid);

            float profit = getProfit((float) binanceAsk, (float) bitgetBid, 200);

            if (profit > 0) {
                System.out.println("Starting...");
                app.setAskPrice(binanceAsk);
                app.setBidPrice(bitgetBid);
                app.start();
            }

            Thread.sleep(5000);
        }
    }
}