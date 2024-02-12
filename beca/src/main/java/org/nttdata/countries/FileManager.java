package org.nttdata.countries;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.PrintWriter;
import java.util.ArrayList;

public class FileManager {

    private final ArrayList<Country> countries = new ArrayList<Country>();
    private String path;
    public FileManager(String path) {
        this.path = path;
        this.loadCountries(path);
    }

    private void loadCountries(String path) {
        try {
            FileReader fileReader = new FileReader(path);
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                String[] data = line.split(";");
                countries.add(new Country(data[0], data[1], Integer.parseInt(data[2]), data[3]));
            }
            bufferedReader.close();
            fileReader.close();
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
    }

    public void saveCountries() {
        try {
            PrintWriter writer = new PrintWriter(path);
            for (Country country : countries) {
                writer.println(country.getName() + ";" + country.getCapital() + ";" + country.getPopulation() + ";" + country.getContinent());
            }
            writer.close();
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
    }

    public ArrayList<Country> getCountries() {
        return countries;
    }
}
