package breaker.Beans;

import org.springframework.stereotype.Component;

import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

@Component
public class SHAUtility {



    public String getSHA(String value){
        String hash = "DEFAULT_VALUE";
        try {

            //String binary =new BigInteger(value,16).toString(10);

            MessageDigest md = MessageDigest.getInstance("SHA-256");

            byte [] digest = md.digest(new BigInteger(value.getBytes()).toByteArray()  ); //value.getBytes() new BigInteger(value,16).toByteArray()
            BigInteger no = new BigInteger(1,digest);
            hash = no.toString(16);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return hash;
    }



}
