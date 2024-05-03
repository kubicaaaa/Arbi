package org.example;

public class BinanceRequest {

    private final String apiKey;
    private final String secretKey;
    private String request;

    public BinanceRequest(Program app, String request) {
        this.apiKey = app.getBinanceApi();
        this.secretKey = app.getBinanceSecret();
        this.request = request;
    }

    public void executeRequest(){

    }
}
