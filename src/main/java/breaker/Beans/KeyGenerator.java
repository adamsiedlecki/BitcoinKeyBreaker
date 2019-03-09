package breaker.Beans;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.ByteArrayOutputStream;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

@Component
public class KeyGenerator {


    private BigInteger actualSeed =  BigInteger.valueOf(1);
    private final BigInteger maxValue =   BigInteger.valueOf(2).pow(256);

    @Autowired
    private SHAUtility shaUtility;



    public String getBruteForcePrivateKey(BigInteger seed){
        if(seed.compareTo(maxValue) != 0 || seed.compareTo(maxValue) != 1) {
            this.actualSeed = seed;
        }
        System.out.println(actualSeed.toString(2));
        String privateKey = shaUtility.getSHA(actualSeed.toString());
        if(isValid(privateKey)){
            //System.out.println(privateKey);
        return privateKey;
        }
        return null ;
    }

    public boolean isValid(String privateKey){
        BigInteger bi = new BigInteger(privateKey,16);
        if(bi.compareTo(maxValue) == 1){
            return false;
        }else if(bi.compareTo(maxValue) == 0){
            return false;
        }else{
            return true;
        }
    }
}
