package breaker.beans;

import java.io.*;
import java.math.BigInteger;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public class Attacker {

    private KeyGenerator keyGenerator = new KeyGenerator();
    private BalanceSearcher balanceSearcher = new BalanceSearcher();
    private RangeNoter rangeNoter = new RangeNoter();

    private boolean isFirstSeed = true;
    private File file;
    private File dictionary;
    private Float balance;
    private String succesfullKey;
    private String bitcoinCliPath;
    private String seedString;

    public void numberAttack(String seed) {
        //BigInteger seed = BigInteger.valueOf(0);


        while (true) {

            seedString = String.valueOf(seed);

            while (seedString.length() < 64) {
                seedString = "0" + seedString;
            }

            String key = keyGenerator.convertToWIF(seedString);

            //If there are any bitcoins on this private/public key, program should sout it and write it to a file.
            balance = balanceSearcher.getBalance(String.valueOf(seed));
            if (balance > 0) {
                succesfullKey = key;
                bitcoinsFoundReaction();
                break;
            }
            //There is a need to tell which numbers (seed) are checked. So it creates a file and note a first and last value
            if (isFirstSeed) {
                rangeNoter.saveFirst(seedString);
            }
            rangeNoter.saveLast(seedString);

            //System.out.println("PRE: "+seedString);
            BigInteger i = new BigInteger(seedString, 16);
            i = i.add(BigInteger.ONE);
            seed = i.toString(16);
            //System.out.println("POST: "+seed);
            //seed = seed.add(BigInteger.ONE);
            isFirstSeed = false;
        }
    }

    private void bitcoinsFoundReaction() {
        System.out.println("This is impossible! The program have found " + balance + " BTC on key: " + succesfullKey);
        file = new File("Bitcoins found" + new GregorianCalendar());
        try {
            Calendar cal = Calendar.getInstance();
            Date date = cal.getTime();
            DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH-mm-ss");
            String formattedDate = dateFormat.format(date);

            FileWriter fw = new FileWriter("BitcoinsFound" + formattedDate + ".txt");
            fw.append("This is impossible! The program have found " + balance + " BTC on key: " + succesfullKey);
            fw.close();

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //This shouldn' probably work
    //also hasnt sense
    public void dictionaryAttack(File dictionary) {
        while (true) {
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
            balance = balanceSearcher.getBalance(key);
            if (balance > 0) {
                succesfullKey = key;
                bitcoinsFoundReaction();
                break;
            }
            System.out.println(key);
        }
    }
}
