package patterns.builder;

import patterns.builder.entity.House;

public class Main {
    public static void main(String[] args) {
//        Country country = new Country.CountryBuilder("Ryazan", "bla-bla-bla-bla-bla")   COUNTRY
//                .setPopulation(1243546)
//                .setArea(213456.76543)
//                .build();
//
//        System.out.println(country);
//        System.out.println(country.doSomething());


        House house1 = new House.HouseBuilder(1, "Some Street")
                .setAmountOfFloors(5)
                .build();

        System.out.println(house1);
    }
}
