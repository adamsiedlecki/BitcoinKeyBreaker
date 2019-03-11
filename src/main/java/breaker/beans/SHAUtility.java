package breaker.beans;

import org.springframework.stereotype.Component;
import org.apache.commons.codec.digest.DigestUtils;

@Component
public class SHAUtility {

    public String getSHA(String value){
        byte[] hexByte = hexStringToByteArray(value);

        String hash = hash = DigestUtils.sha256Hex(hexByte);
        System.out.println(hash);
        return hash;
    }

    public static byte[] hexStringToByteArray(String hex) {
        int l = hex.length();
        byte[] data = new byte[l/2];
        for (int i = 0; i < l; i += 2) {
            data[i/2] = (byte) ((Character.digit(hex.charAt(i), 16)<< 4 )
                    + Character.digit(hex.charAt(i+1), 16));
        }
        return data;
    }
}
