package breaker.beans;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.math.BigInteger;

@Component
public class KeyGenerator {


    private BigInteger numberSeed =  BigInteger.valueOf(1);
    private String stringSeed;
    private final BigInteger maxValue =   BigInteger.valueOf(2).pow(256);

    @Autowired
    private SHAUtility shaUtility;



    public String getBruteForcePrivateKey(BigInteger seed){

            this.numberSeed = seed;

        // Decimal number: System.out.println(numberSeed.toString(10));
        String privateKey = shaUtility.getSHA(numberSeed.toString());
        if(isValid(privateKey)){
            //System.out.println(privateKey);
        return privateKey;
        }
        return null ;
    }

    public String getBruteForcePrivateKey(String seed){

        this.stringSeed = seed;

        System.out.println(numberSeed.toString(2));
        String privateKey = shaUtility.getSHA(numberSeed.toString());
        if(isValid(privateKey)){
            //System.out.println(privateKey);
            return privateKey;
        }
        return null ;
    }

    private boolean isValid(String privateKey){
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
