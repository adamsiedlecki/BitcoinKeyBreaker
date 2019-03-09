package breaker.Beans;

import org.springframework.stereotype.Component;

import java.util.Scanner;

@Component
public class ConsoleUI {

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
        System.out.println("To run this application, you need to have installed Bitcoin Core client.");
        System.out.println("Enter a bitcoin-cli.exe location:   (for example D:\\BTC\\Bitcoin\\daemon\\bitcoin-cli.exe)");
        Scanner input = new Scanner(System.in);
        String path = input.nextLine();
        path = " D:\\BTC\\Bitcoin\\daemon\\bitcoin-cli.exe";
        System.out.println("Choose your way to guess private keys:");
        System.out.println("1. Bruteforce by hashing numbers like x, x+1, x+2 etc.");
        System.out.println("2. Dictionary attack - you provide a dictionary file with words in new lines.");
        String method = input.nextLine();
        while(!method.equals("1")||!method.equals("2")){
            System.out.println("Wrong value! Please enter 1 or 2 !");
            method = input.nextLine();
        }

        if(method.equals("1")){
            //Hashing numbers method
        }else if(method.equals("2")){
            //Hashing records from dictionary
        }
    }
}
