package breaker.Beans;

import breaker.Configuration.Conf;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.stereotype.Component;

import java.io.File;
import java.math.BigDecimal;
import java.math.BigInteger;

@Component
public class Attacker {

    @Autowired
    KeyGenerator keyGenerator;

    File file;

    public void attack(BigInteger seed){
        //BigInteger seed = BigInteger.valueOf(0);
        while(true) {
            seed = seed.add(BigInteger.ONE);
            String key = keyGenerator.getBruteForcePrivateKey(seed);

            //If there are any bitcoins on this private/public key, program should sout it and write it to a file.
        }

    }
}
