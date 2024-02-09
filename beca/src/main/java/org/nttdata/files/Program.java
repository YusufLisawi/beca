package org.nttdata.files;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.PrintWriter;

public class Program {
    public static void main(String[] args) {
        try {
            FileReader fileReader = new FileReader("src/main/java/org/nttdata/files/original.txt");
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            String line;
            int i = 0;
            PrintWriter copie = new PrintWriter("src/main/java/org/nttdata/files/copie.txt");
            while ((line = bufferedReader.readLine()) != null) {
                copie.println(++i + ": " + line);
            }
            bufferedReader.close();
            fileReader.close();
            copie.close();
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
    }
}
