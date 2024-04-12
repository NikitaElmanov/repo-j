package patterns.generative.builder.entity;

public class Country {

    //important
    private String name;
    private String hymn;

    //unimportant
    private long population;
    private double area;

    private Country(CountryBuilder countryBuilder) {
        this.name = countryBuilder.getName();
        this.hymn = countryBuilder.getHymn();
        this.population = countryBuilder.getPopulation();
        this.area = countryBuilder.getArea();
    }

    public static class CountryBuilder {

        //important
        private String name;
        private String hymn;

        //unimportant
        private long population;
        private double area;

        public CountryBuilder(String name, String hymn) {
            this.name = name;
            this.hymn = hymn;
        }

        private String getName() {
            return name;
        }

        private String getHymn() {
            return hymn;
        }

        private long getPopulation() {
            return population;
        }

        private double getArea() {
            return area;
        }

        public CountryBuilder setPopulation(long population) {
            this.population = population;
            return this;
        }

        public CountryBuilder setArea(double area) {
            this.area = area;
            return this;
        }

        public Country build(){
            return new Country(this);
        }
    }

    @Override
    public String toString() {
        return "Country{" +
                "name='" + name + '\'' +
                ", hymn='" + hymn + '\'' +
                ", population=" + population +
                ", area=" + area +
                '}';
    }

    public String doSomething(){
        return this.name + " is working...";
    }
}
