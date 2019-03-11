package breaker.configuration;

import breaker.beans.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration

public class Conf {

    @Bean("bruteForceKeyGenerator")
    public KeyGenerator getBitcoinKeyGenerator(){
        return new KeyGenerator();
    }

    @Bean("attacker")
    public Attacker getAttacker(){
            return new Attacker();
    }

    @Bean("shaUtility")
    public SHAUtility getSHAUtility(){
        return  new SHAUtility();
    }

    @Bean("consoleUI")
    public ConsoleUI getConsoleUI(){
        return new ConsoleUI();
    }

    @Bean
    public BalanceSearcher getBalanceSearcher(){
        return new BalanceSearcher();
    }
}
