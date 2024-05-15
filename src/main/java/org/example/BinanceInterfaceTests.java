import org.example.BinanceInterface;
import org.testng.annotations.Test;

public class BinanceInterfaceTests {
    @Test
    public void getBalance() throws Exception { // PASSED
        String asset = "IOTX";
        String api = "bxycYuVHCyXhV9DmrmBIlbhiEMyDnGdoejam7nmtsVP3gXl2mTtAcT81rTv0zTmM";
        String secret = "aszXOFFh5lxCpl8Q4noypIHCRQDHVZAdxwP4GemPa4ttiDhskmvSrzJjCn6irVGV";

        BinanceInterface bin = new BinanceInterface(api, secret);
        bin.getBalance(asset);
    }

    @Test
    public void placeOrder() throws Exception { // PASSED tylko okragle wartosci 520 zamiast 520.3
        String asset = "IOTX";
        String api = "bxycYuVHCyXhV9DmrmBIlbhiEMyDnGdoejam7nmtsVP3gXl2mTtAcT81rTv0zTmM";
        String secret = "aszXOFFh5lxCpl8Q4noypIHCRQDHVZAdxwP4GemPa4ttiDhskmvSrzJjCn6irVGV";

        BinanceInterface bin = new BinanceInterface(api, secret);
        bin.placeOrder(asset, "SELL", 520, 0.04892);
    }

    @Test
    public void withdraw() throws Exception { // PASSED
        String asset = "IOTX";
        String api = "bxycYuVHCyXhV9DmrmBIlbhiEMyDnGdoejam7nmtsVP3gXl2mTtAcT81rTv0zTmM";
        String secret = "aszXOFFh5lxCpl8Q4noypIHCRQDHVZAdxwP4GemPa4ttiDhskmvSrzJjCn6irVGV";

        BinanceInterface bin = new BinanceInterface(api, secret);
        bin.withdraw(asset, "IOTX" , "io13sv50j2fu8jzfessv7t2e3mp0w43p6wpf30zls", 520.37);
    }
}
