import org.example.BinanceInterface;
import org.testng.annotations.Test;

public class BinanceInterfaceTests {
    @Test
    public void getBalance() throws Exception {
        String asset = "IOTX";
        String api = "INSERT YOUR BINANCE API";
        String secret = "INSERT YOUR BINANCE SECRET API";

        BinanceInterface bin = new BinanceInterface(api, secret);
        bin.getBalance(asset);
    }

    @Test
    public void placeOrder() throws Exception {
        String asset = "IOTX";
        String api = "INSERT YOUR BINANCE API";
        String secret = "INSERT YOUR BINANCE SECRET API";

        BinanceInterface bin = new BinanceInterface(api, secret);
        bin.placeOrder(asset, "SELL", 520, 0.04892);
    }

    @Test
    public void withdraw() throws Exception {
        String asset = "IOTX";
        String api = "INSERT YOUR BINANCE API";
        String secret = "INSERT YOUR BINANCE SECRET API";
        String address = "INSERT YOUR WITHDRAWAL ADDRESS";

        BinanceInterface bin = new BinanceInterface(api, secret);
        bin.withdraw(asset, "IOTX" , address, 520.37);
    }
}
