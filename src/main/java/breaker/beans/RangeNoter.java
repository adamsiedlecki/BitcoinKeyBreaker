package breaker.beans;

import org.springframework.stereotype.Component;

import java.io.*;

@Component("rangeNoter")
public class RangeNoter {

    File rangeRaport;
    FileWriter fileWriter;
    BufferedReader bufferedReader;

    public RangeNoter() {
        rangeRaport = new File("rangeRaport.txt");
        rangeRaport.delete();
        try {
            rangeRaport.createNewFile();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void saveFirst(String first) {

        try {
            fileWriter = new FileWriter(rangeRaport);
            fileWriter.write(first);
            fileWriter.flush();
            fileWriter.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void saveLast(String last) {

        try {


            bufferedReader = new BufferedReader(new FileReader(rangeRaport));
            String first = bufferedReader.readLine();
            bufferedReader.close();
            fileWriter = new FileWriter(rangeRaport);
            fileWriter.write(first);
            fileWriter.write("\n");
            fileWriter.write(last);
            fileWriter.close();


        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }


    }

}
