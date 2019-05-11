package breaker;

import breaker.beans.ConsoleUI;
import breaker.configuration.Conf;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

// Made by AdamSiedlecky
public class BitcoinKeyBreaker {

    public static void main (String [] args){


        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(Conf.class);

        ConsoleUI consoleUI = context.getBean("consoleUI",ConsoleUI.class);
        if (args.length == 2) {
            consoleUI.getStarted(args[0], args[1]);
        } else {
            consoleUI.getStarted("0", "0");
        }

    }
}
