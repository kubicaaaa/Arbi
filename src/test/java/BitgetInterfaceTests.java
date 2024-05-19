import org.example.BitgetInterface;
import org.testng.annotations.Test;

public class BitgetInterfaceTests {

    @Test
    public void getBalance() throws Exception {
        String asset = "USDT";
        String bitgetApi = "INSERT YOUR BITGET API";
        String bitgetSecret = "INSERT YOUR BITGET SECRET API";
        String bitgetPassphrase = "INSERT YOUR BITGET PASSPHRASE";

        BitgetInterface bit = new BitgetInterface(bitgetApi, bitgetSecret, bitgetPassphrase);
        bit.getBalance(asset);
    }

    @Test
    public void placeOrder() throws Exception {
        String asset = "IOTX";
        String bitgetApi = "INSERT YOUR BITGET API";
        String bitgetSecret = "INSERT YOUR BITGET SECRET API";
        String bitgetPassphrase = "INSERT YOUR BITGET PASSPHRASE";

        BitgetInterface bit = new BitgetInterface(bitgetApi, bitgetSecret, bitgetPassphrase);
        bit.placeOrder(asset, "sell", "0.049084", "520");
    }

    @Test
    public void withdraw() throws Exception {
        String asset = "IOTX";
        String bitgetApi = "INSERT YOUR BITGET API";
        String bitgetSecret = "INSERT YOUR BITGET SECRET API";
        String bitgetPassphrase = "INSERT YOUR BITGET PASSPHRASE";
        String address = "INSERT YOUR WITHDRAWAL ADDRESS";

        BitgetInterface bit = new BitgetInterface(bitgetApi, bitgetSecret, bitgetPassphrase);
        bit.withdraw(asset, address, "iotex", "520.479");
    }
}