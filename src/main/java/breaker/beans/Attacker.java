package breaker.beans;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.*;
import java.math.BigInteger;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

@Component("attacker")
public class Attacker {

    @Autowired
    KeyGenerator keyGenerator;

    @Autowired
    BalanceSearcher balanceSearcher;

    private File file;
    private File dictionary;
    private Float balance;
    private String succesfullKey;
    private String bitcoinCliPath;

    public void numberAttack(BigInteger seed,String bitcoinCliPath){
        //BigInteger seed = BigInteger.valueOf(0);
        while(true) {

            String key = keyGenerator.convertToWIF(String.valueOf(seed));

            //If there are any bitcoins on this private/public key, program should sout it and write it to a file.
            balance = balanceSearcher.getBalance(String.valueOf(seed),bitcoinCliPath);
            if(balance>0){
                succesfullKey = key;
                bitcoinsFoundReaction();
                break;
            }
            System.out.println(key);
            seed = seed.add(BigInteger.ONE);
        }
    }

    private void bitcoinsFoundReaction(){
        System.out.println("This is impossible! The program have found "+balance+" BTC on key: "+succesfullKey);
        file = new File("Bitcoins found"+new GregorianCalendar());
        try {
            Calendar cal = Calendar.getInstance();
            Date date=cal.getTime();
            DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH-mm-ss");
            String formattedDate=dateFormat.format(date);

            FileWriter fw = new FileWriter("BitcoinsFound"+formattedDate+".txt");
            fw.append("This is impossible! The program have found "+balance+" BTC on key: "+succesfullKey);
            fw.close();

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void dictionaryAttack(File dictionary,String bitcoinCliPath){
        while(true) {
            BufferedReader bf;
            String seed = "";
            try {
                bf = new BufferedReader(new FileReader(dictionary));
                seed = bf.readLine();
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }

            String key = keyGenerator.convertToWIF(seed);


            //If there are any bitcoins on this private/public key, program should sout it and write it to a file.
            balance = balanceSearcher.getBalance(key,bitcoinCliPath);
            if(balance>0){
                succesfullKey = key;
                bitcoinsFoundReaction();
                break;
            }
            System.out.println(key);
        }
    }
}
