import org.example.BitgetInterface;
import org.testng.annotations.Test;

public class BitgetInterfaceTests {

    @Test
    public void getBalance() throws Exception { // PASSED
        String asset = "USDT";
        String bitgetApi = "bg_edf992931da5f6ac58a39466b99763df";
        String bitgetSecret = "51de7f3c3969ceb9469556f4a6b9b0b097b02dcfe8307c33666a9b4590438944";
        String bitgetPassphrase = "fabiaN342196";

        BitgetInterface bit = new BitgetInterface(bitgetApi, bitgetSecret, bitgetPassphrase);
        bit.getBalance(asset);
    }

    @Test
    public void placeOrder() throws Exception { // PASSED warto zaokraglac
        String asset = "IOTX";
        String bitgetApi = "bg_edf992931da5f6ac58a39466b99763df";
        String bitgetSecret = "51de7f3c3969ceb9469556f4a6b9b0b097b02dcfe8307c33666a9b4590438944";
        String bitgetPassphrase = "fabiaN342196";

        BitgetInterface bit = new BitgetInterface(bitgetApi, bitgetSecret, bitgetPassphrase);
        bit.placeOrder(asset, "sell", "0.049084", "520");
    }

    @Test
    public void withdraw() throws Exception { // PASSED
        String asset = "IOTX";
        String bitgetApi = "bg_edf992931da5f6ac58a39466b99763df";
        String bitgetSecret = "51de7f3c3969ceb9469556f4a6b9b0b097b02dcfe8307c33666a9b4590438944";
        String bitgetPassphrase = "fabiaN342196";
        String address = "io14zwq9lksqg8zavywxfurzudf8rwwr4yjj6w80e";

        BitgetInterface bit = new BitgetInterface(bitgetApi, bitgetSecret, bitgetPassphrase);
        bit.withdraw(asset, address, "iotex", "520.479");
    }
}