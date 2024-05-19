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
        fees = Math.round(fees * 100) / 100.0;

        System.out.println("Operation fees: " + fees + "$");

        double buyQuantity = value / askPrice;

        double bidPrice = (value + 0.1f + fees) / buyQuantity;
        bidPrice = Math.round(bidPrice * 100000) / 100000.0; // rounding to reach proper price format for exchanges

        return bidPrice;
    }

    public static void main(String[] args) throws Exception {

        String asset = "IOTX";

            String binanceApi = "INSERT YOUR BINANCE API KEY";
            String binanceSecret = "INSERT YOUR BINANCE SECRET API KEY";
            String bitgetApi = "INSERT YOUR BITGET API KEY";
            String bitgetSecret = "INSERT YOUR BITGET SECRET API KEY";
            String bitgetPassphrase = "INSERT YOUR BITGET PASSPHRASE";
            String binanceUsdtAddress = "INSERT YOUR BINANCE USDT ADDRESS";
            String bitgetAssetAddress = "INSERT YOUR BITGET ASSET ADDRESS";

        Program app = new Program(asset, binanceApi, binanceSecret, bitgetApi, bitgetSecret, bitgetPassphrase,
                binanceUsdtAddress, bitgetAssetAddress);

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