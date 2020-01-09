package breaker.beans;

import breaker.configuration.Conf;

import java.io.File;
import java.util.Scanner;

public class ConsoleUI {

    Attacker attacker = new Attacker();

    private File dictionary;

    public void getStarted(String mode, String initialValue) {
        System.out.println(Conf.BANNER);
        System.out.println("By Adam Siedlecky");
        if ("0".equals(mode)) {
            System.out.println("////////////////////////////////////////////////////////////////////////////////");
            Scanner input = new Scanner(System.in);
            System.out.println("Choose your way to guess private keys:");
            System.out.println("1. Bruteforce by hashing numbers like x, x+1, x+2 etc.");
            System.out.println("2. Dictionary attack - you provide a dictionary file with words in new lines. UNSUPPORTED - DOESN'T WORK");
            String method = input.nextLine();
            while (!method.equals("1") && !method.equals("1")) {
                System.out.println("Wrong value! Please enter 1 or 2 !");
                method = input.nextLine();
            }

            if (method.equals("1")) {
                //Hashing numbers method
                System.out.println("Enter initial value: (must be positive, hexadecimal and diffrent drom 0 and 1 , becouse BitcoinJ requires that)");
                String init = input.nextLine();
                while (!init.matches("-?[0-9a-fA-F]+") || init.length() > 64 || init.equals("1") || init.equals("2")) {
                    System.out.println("Value isn't a proper number, try again: ");
                    init = input.nextLine();
                }
                attacker.numberAttack(init);
                System.out.println("Attacking in progress...");
            } else if (method.equals("2")) {
                //Hashing records from dictionary
                System.out.println("Enter a filepath to your dictionary (seed records must be in new lines)");
                String filepath = input.nextLine();
                dictionary = new File(filepath);
                while (!dictionary.exists()) {
                    System.out.println("Dictionary doesn't exist at this path. Please enter correct one: ");
                    filepath = input.nextLine();
                    dictionary = new File(filepath);
                }
                attacker.dictionaryAttack(dictionary);

            }
        } else {
            attacker.numberAttack(initialValue);
        }

    }
}