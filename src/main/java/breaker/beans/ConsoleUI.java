package breaker.beans;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.File;
import java.math.BigInteger;
import java.util.Scanner;



@Component("consoleUI")
public class ConsoleUI {

    @Autowired
    Attacker attecker;

    private File dictionary;

    public void getStarted(){
        System.out.println(" /$$$$$$$  /$$   /$$                         /$$                      \n" +
                "| $$__  $$|__/  | $$                        |__/                      \n" +
                "| $$  \\ $$ /$$ /$$$$$$    /$$$$$$$  /$$$$$$  /$$ /$$$$$$$             \n" +
                "| $$$$$$$ | $$|_  $$_/   /$$_____/ /$$__  $$| $$| $$__  $$            \n" +
                "| $$__  $$| $$  | $$    | $$      | $$  \\ $$| $$| $$  \\ $$            \n" +
                "| $$  \\ $$| $$  | $$ /$$| $$      | $$  | $$| $$| $$  | $$            \n" +
                "| $$$$$$$/| $$  |  $$$$/|  $$$$$$$|  $$$$$$/| $$| $$  | $$            \n" +
                "|_______/ |__/   \\___/   \\_______/ \\______/ |__/|__/  |__/            \n" +
                " /$$   /$$                                                            \n" +
                "| $$  /$$/                                                            \n" +
                "| $$ /$$/   /$$$$$$  /$$   /$$                                        \n" +
                "| $$$$$/   /$$__  $$| $$  | $$                                        \n" +
                "| $$  $$  | $$$$$$$$| $$  | $$                                        \n" +
                "| $$\\  $$ | $$_____/| $$  | $$                                        \n" +
                "| $$ \\  $$|  $$$$$$$|  $$$$$$$                                        \n" +
                "|__/  \\__/ \\_______/ \\____  $$                                        \n" +
                "                     /$$  | $$                                        \n" +
                " /$$$$$$$           |  $$$$$$/           /$$                          \n" +
                "| $$__  $$           \\______/           | $$                          \n" +
                "| $$  \\ $$  /$$$$$$   /$$$$$$   /$$$$$$ | $$   /$$  /$$$$$$   /$$$$$$ \n" +
                "| $$$$$$$  /$$__  $$ /$$__  $$ |____  $$| $$  /$$/ /$$__  $$ /$$__  $$\n" +
                "| $$__  $$| $$  \\__/| $$$$$$$$  /$$$$$$$| $$$$$$/ | $$$$$$$$| $$  \\__/\n" +
                "| $$  \\ $$| $$      | $$_____/ /$$__  $$| $$_  $$ | $$_____/| $$      \n" +
                "| $$$$$$$/| $$      |  $$$$$$$|  $$$$$$$| $$ \\  $$|  $$$$$$$| $$      \n" +
                "|_______/ |__/       \\_______/ \\_______/|__/  \\__/ \\_______/|__/      \n" +
                "                                                                      \n" +
                "                                                               ");
        System.out.println("By Adam Siedlecky");
        System.out.println("////////////////////////////////////////////////////////////////////////////////");
//        System.out.println("Enter a bitcoin-cli.exe location:   (for example D:\\BTC\\Bitcoin\\daemon\\)");
        Scanner input = new Scanner(System.in);
//        String path = input.nextLine();
//        path = " D:\\BTC\\Bitcoin\\daemon\\bitcoin-cli.exe";
        System.out.println("Choose your way to guess private keys:");
        System.out.println("1. Bruteforce by hashing numbers like x, x+1, x+2 etc.");
        System.out.println("2. Dictionary attack - you provide a dictionary file with words in new lines. UNSUPPORTED - DOESN'T WORK");
        String method = input.nextLine();
        while(!method.equals("1")&&!method.equals("1")){
            System.out.println("Wrong value! Please enter 1 or 2 !");
            method = input.nextLine();
        }

        if(method.equals("1")){
            //Hashing numbers method
            System.out.println("Enter initial value: (must be positive, integer and diffrent drom 0 and 1 , becouse BitcoinJ requires that)");
            String init = input.nextLine();
            while(!isNumeric(init)||
                    !isInteger(init)||init.equals("1")||init.equals("0")){
                System.out.println("Value isn't a proper number, try again: ");
                init = input.nextLine();
            }
            attecker.numberAttack(new BigInteger(init));
            System.out.println("Attacking in progress...");
        }else if(method.equals("2")){
            //Hashing records from dictionary
            System.out.println("Enter a filepath to your dictionary (seed records must be in new lines)");
            String filepath = input.nextLine();
            dictionary = new File(filepath);
            while(!dictionary.exists()){
                System.out.println("Dictionary doesn't exist at this path. Please enter correct one: ");
                filepath = input.nextLine();
                dictionary = new File(filepath);
            }
            attecker.dictionaryAttack(dictionary);

        }
    }
    private boolean isNumeric(String strNum) {
        return strNum.matches("-?\\d+(\\.\\d+)?");
    }

    private static boolean isInteger(String s) {
        try {
            Integer.parseInt(s);
        } catch(NumberFormatException e) {
            return false;
        } catch(NullPointerException e) {
            return false;
        }
        // only got here if we didn't return false
        return true;
    }
}
