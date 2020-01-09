package breaker;

import breaker.beans.ConsoleUI;

// Made by Adam Siedlecki
public class BitcoinKeyBreaker {

    public static void main(String[] args) {

        ConsoleUI consoleUI = new ConsoleUI();
        if (args.length == 2) {
            consoleUI.getStarted(args[0], args[1]);
        } else {
            consoleUI.getStarted("0", "0");
        }

    }
}
