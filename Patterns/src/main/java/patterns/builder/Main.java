package patterns.builder;

import patterns.builder.entity.Country;

public class Main {
    public static void main(String[] args) {
        Country country = new Country.CountryBuilder("Ryazan", "bla-bla-bla-bla-bla")
                .setPopulation(1243546)
                .setArea(213456.76543)
                .build();

        System.out.println(country);
        System.out.println(country.doSomething());
    }
}
