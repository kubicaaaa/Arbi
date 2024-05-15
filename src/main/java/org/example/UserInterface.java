package org.example;

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

    // Calculating bid price to make every transaction profitable
    public static double getBidPrice(double askPrice, int value) {

        // Fees
        double bitgetUsdtFee = 0.29;
        double assetBinanceFee = 0.1;
        double assetBitgetFee = 0.1;
        double binanceTakerFee = 0.00075 * value;
        double bitgetTakerFee = 0.00080 * value;

        double fees = bitgetUsdtFee + assetBinanceFee + assetBitgetFee + binanceTakerFee + bitgetTakerFee;
        System.out.println(fees);

        double buyQuantity = value / askPrice;
        System.out.println(buyQuantity);

        double bidPrice = (value + 0.1f + fees) / buyQuantity;

        System.out.println(bidPrice);

        return bidPrice;
    }

    public static void main(String[] args) throws Exception {

        String asset = "IOTX";
        String binanceApi = "bxycYuVHCyXhV9DmrmBIlbhiEMyDnGdoejam7nmtsVP3gXl2mTtAcT81rTv0zTmM";
        String binanceSecret = "aszXOFFh5lxCpl8Q4noypIHCRQDHVZAdxwP4GemPa4ttiDhskmvSrzJjCn6irVGV";
        String bitgetApi = "bg_edf992931da5f6ac58a39466b99763df";
        String bitgetSecret = "51de7f3c3969ceb9469556f4a6b9b0b097b02dcfe8307c33666a9b4590438944";
        String bitgetPassphrase = "fabiaN342196";
        String binanceAssetAddress = "io14zwq9lksqg8zavywxfurzudf8rwwr4yjj6w80e";
        String binanceUsdtAddress = "0x78b7ce5c646eed436badf902838c7222221c6bd2"; // BEP20
        String bitgetAssetAddress = "io13sv50j2fu8jzfessv7t2e3mp0w43p6wpf30zls";
        String bitgetUsdtAddress = "0x604867d76103c22d3aa5c7ec06f4ae37f488ef3e"; // AVAXC
        Program app = new Program(asset, binanceApi, binanceSecret, bitgetApi, bitgetSecret, bitgetPassphrase,
                binanceAssetAddress, binanceUsdtAddress, bitgetAssetAddress, bitgetUsdtAddress);

        while (true) {
            // BINANCE API request
            JSONObject binanceData = getData("https://api.binance.com/api/v3/ticker/bookTicker?symbol=" + asset + "USDT");

            // Buying
            double binanceAsk = binanceData.getDouble("askPrice");

            // Selling
            double bitgetBid = getBidPrice(binanceAsk, 100);

            System.out.println("Binance BUY price: " + binanceAsk);
            System.out.println("Bitget SELL price: " + bitgetBid);

            System.out.println("Starting...");
            app.setAskPrice(binanceAsk);
            app.setBidPrice(bitgetBid);
            app.start();

            Thread.sleep(5000);
        }
    }
}