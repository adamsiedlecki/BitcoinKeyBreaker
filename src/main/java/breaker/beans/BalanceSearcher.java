package breaker.beans;


import org.bitcoinj.core.Base58;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.math.BigInteger;


import static java.lang.System.*;

@Component
public class BalanceSearcher {

    @Autowired
    SHAUtility shaUtility;

    @Autowired
    KeyGenerator keyGenerator;
    private String publicKey;
    private String privateKey;
    private String privateWIF;



    public float getBalance(String privateKey,String bitcoinCliPath){
        this.privateKey = privateKey;
        String pub = getPublicKey(privateKey);
        privateWIF = keyGenerator.convertToWIF(privateKey);
        out.println("PrivateWIF :"+privateWIF);
        out.println("Public : "+pub);
//        ProcessBuilder pb = new ProcessBuilder("bitcoin-cli.exe","getbalance",publicKey);
//        Map<String, String> env = pb.environment();
//        env.put("VAR1", "myValue");
//        env.remove("OTHERVAR");
//        env.put("VAR2", env.get("VAR1") + "suffix");
//        pb.directory(new File(bitcoinCliPath));
//        try {
//            Process p = pb.start();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
        return 1;
    }



    private String getPublicKey(String privateKey){


        return publicKey;
    }






}
