package breaker.Configuration;

import breaker.Beans.KeyGenerator;
import breaker.Beans.SHAUtility;
import breaker.BitcoinKeyBreaker;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.math.BigDecimal;
import java.math.BigInteger;

@Configuration
public class Conf {

    @Bean("bruteForceKeyGenerator")
    public KeyGenerator getBitcoinKeyGenerator(){
        return new KeyGenerator();
    }



    @Bean("shaUtility")
    public SHAUtility getSHAUtility(){
        return  new SHAUtility();
    }
}
