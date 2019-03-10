package breaker.Beans;

import breaker.Configuration.Conf;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.stereotype.Component;

import java.io.*;
import java.math.BigDecimal;
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
    private Float balance;
    private String succesfullKey;

    public void numberAttack(BigInteger seed){
        //BigInteger seed = BigInteger.valueOf(0);
        while(true) {
            seed = seed.add(BigInteger.ONE);
            String key = keyGenerator.getBruteForcePrivateKey(seed);

            //If there are any bitcoins on this private/public key, program should sout it and write it to a file.
            balance = balanceSearcher.getBalance(key);
            if(balance>0){
                succesfullKey = key;
                bitcoinsFoundReaction();
                break;
            }
            System.out.println(key);
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

    public void dictionaryAttack(String path){

    }
}
