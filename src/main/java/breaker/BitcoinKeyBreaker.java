package breaker;

import breaker.beans.ConsoleUI;
import breaker.configuration.Conf;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

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
