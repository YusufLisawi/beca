package org.nttdata.countries;

public class Country {
    private String name;
    private String capital;
    private int population;
    private String continent;

    public Country(String name, String capital, int population, String continent) {
        this.name = name;
        this.capital = capital;
        this.population = population;
        this.continent = continent;
    }

    public String getName() {
        return name;
    }

    public String getCapital() {
        return capital;
    }

    public int getPopulation() {
        return population;
    }

    public String getContinent() {
        return continent;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setCapital(String capital) {
        this.capital = capital;
    }

    public void setPopulation(int population) {
        this.population = population;
    }

    public void setContinent(String continent) {
        this.continent = continent;
    }

    @Override
    public String toString() {
        return name + ';' + capital + ';' + population + ';' + continent;
    }
}
