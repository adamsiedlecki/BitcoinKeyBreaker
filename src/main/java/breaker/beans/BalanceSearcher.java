package breaker.beans;


import org.bitcoinj.core.*;
import org.bitcoinj.params.MainNetParams;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.math.BigInteger;

import static java.lang.System.out;

@Component
public class BalanceSearcher {

    @Autowired
    private SHAUtility shaUtility;

    @Autowired
    private KeyGenerator keyGenerator;

    @Autowired
    private JSONReader jsonReader;


    private String publicKey;
    private String privateKey;
    private String privateWIF;
    private BigInteger counter;

    public BalanceSearcher() {
        counter = BigInteger.ZERO;
    }

    public float getBalance(String privateKey) {
        this.privateKey = privateKey;
        counter = counter.add(BigInteger.ONE);
        privateWIF = keyGenerator.convertToWIF(privateKey);
        publicKey = getPublicKey(privateWIF);
        displayKeyPair();
        float balance;
        boolean afterChainSoFail = false;
        try {
            if (afterChainSoFail) {
                throw new Exception("ChainSo is unavailable ");
            }
            // request to Chain.so - it blocks after 11 times
            balance = getBalanceFromKey(publicKey);
        } catch (Exception e) {
            afterChainSoFail = true;
            // request to Blockchain.com
            balance = getBalanceFromKey2();
        }


        return balance;
    }

    private void displayKeyPair() {
        out.println(counter + " PrivateWIF :" + privateWIF);
        System.out.println("*---------------------*");
        out.println(counter + " Public : " + publicKey);
    }

    private String getPublicKey(String privateKey) {

        NetworkParameters params = MainNetParams.get();
        ECKey key;
        if (privateKey.length() == 51 || privateKey.length() == 52) {
            DumpedPrivateKey dumpedPrivateKey = DumpedPrivateKey.fromBase58(params, privateKey);
            key = dumpedPrivateKey.getKey();
        } else {
            BigInteger privKey = Base58.decodeToBigInteger(privateKey);
            key = ECKey.fromPrivate(privKey);
        }
        publicKey = LegacyAddress.fromKey(params, key).toString();

        return publicKey;
    }

    //This method loads data from Chain.so - but it blocks after 11 requests
    private float getBalanceFromKey(String publicKey) throws IOException, JSONException {
        String balance = "";

        JSONObject json = jsonReader.readJsonFromUrl("https://chain.so/api/v2/get_address_balance/BTC/" + publicKey + "/1");
        JSONObject data = json.getJSONObject("data");
        balance = (String) data.get("confirmed_balance");

        System.out.println("BALANCE: " + balance);
        float bal = Float.valueOf(balance);
        return bal;
    }

    private float getBalanceFromKey2() {
        Integer balance = Integer.parseInt("0");
        try {
            JSONObject json = jsonReader.readJsonFromUrl("https://blockchain.info/rawaddr/" + publicKey);
            balance = (Integer) json.get("final_balance");
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }
        System.out.println("BALANCE: " + balance);
        float bal = Float.valueOf(balance);
        return bal;
    }


}
