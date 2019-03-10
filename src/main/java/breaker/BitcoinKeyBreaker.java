package breaker;

import breaker.Beans.ConsoleUI;
import breaker.Beans.KeyGenerator;
import breaker.Configuration.Conf;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;

import java.math.BigInteger;

// Made by AdamSiedlecky
@SpringBootApplication
public class BitcoinKeyBreaker {

    public static void main (String [] args){

    SpringApplication.run(BitcoinKeyBreaker.class);
    AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(Conf.class);

        ConsoleUI consoleUI = context.getBean("consoleUI",ConsoleUI.class);
        consoleUI.getStarted();


    }
}
