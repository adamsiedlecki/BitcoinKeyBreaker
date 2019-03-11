package breaker.beans;

import org.springframework.stereotype.Component;
import org.apache.commons.codec.digest.DigestUtils;

@Component
public class SHAUtility {

    public String getSHA(String value){
        String hash = hash = DigestUtils.sha256Hex(value.getBytes());
        System.out.println(hash);
        return hash;
    }
}
