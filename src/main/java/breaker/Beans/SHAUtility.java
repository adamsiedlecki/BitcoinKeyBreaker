package breaker.Beans;

import org.springframework.stereotype.Component;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

@Component
public class SHAUtility {



    public String getSHA(String value){
        String hash = "DEFAULT_VALUE";
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            byte [] digest = md.digest(value.getBytes());
            BigInteger no = new BigInteger(1,digest);
            hash = no.toString(16);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return hash;
    }

}
