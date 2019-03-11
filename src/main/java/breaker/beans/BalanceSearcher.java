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

    private String publicKey;
    private String privateKey;

    private String privateWIF;

    public float getBalance(String privateKey,String bitcoinCliPath){
        this.privateKey = privateKey;
        String pub = getPublicKey(privateKey);
        convertToWIF(privateKey);
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

    private String convertToWIF(String privateKey){
        privateKey = "0C28FCA386C7A227600B2FE50B7CAE11EC86D3BF1FBE471BE89827E19D72AA1D";
        System.out.println("Seed: "+privateKey);
        while(privateKey.length()<64){
            privateKey = "0"+privateKey;
        }
        String temporary = "80"+ privateKey;
        System.out.println("2. "+temporary);
        String hashed = shaUtility.getSHA(temporary);
        System.out.println("3. "+hashed);
        String hashedTwice = shaUtility.getSHA(hashed);
        System.out.println("4. "+hashedTwice);
        String checksum = ""+hashedTwice.toCharArray()[0]+hashedTwice.toCharArray()[1]+hashedTwice.toCharArray()[2]+hashedTwice.toCharArray()[3]+hashedTwice.toCharArray()[4]+hashedTwice.toCharArray()[5]+hashedTwice.toCharArray()[6]+hashedTwice.toCharArray()[7];
        System.out.println("5. Checksum: "+checksum);
        String temoporary = temporary+checksum;


//
//            //I should count a base58 basing on bytearray from hex!!
        System.out.println("Temp: "+temoporary);
        BigInteger bi = new BigInteger(temporary,16);
//        String hexTemporary = bi.toString(16);

        String result = Base58.encode(temporary.getBytes());
        System.out.println("Base58: "+result);
        System.out.println("///////////////////");
        privateWIF = result;
        System.out.println("Seed: "+privateKey);
        System.out.println("Hashed: "+hashed);
        System.out.println("Hashedtwice: "+hashedTwice);
        System.out.println("Temporary: "+temoporary);
       // System.out.println("HEXTEMP: "+hexTemporary);
//        String trueAddress = Base58.encode("800C28FCA386C7A227600B2FE50B7CAE11EC86D3BF1FBE471BE89827E19D72AA1D507A5B8D".getBytes());
//        System.out.println(trueAddress+" Should be equal to: 5HueCGU8rMjxEXxiPuD5BDku4MkFqeZyd4dZ1jvhTVqvbTLvyTJ" );
        return privateWIF;
    }




}
