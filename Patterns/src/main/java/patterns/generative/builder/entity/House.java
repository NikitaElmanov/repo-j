package patterns.generative.builder.entity;

import lombok.Getter;

public class House {

    private Integer number;
    private String street;
    private Integer amountOfFloors;

    private House(HouseBuilder houseBuilder) {
        this.number = houseBuilder.getNumber();
        this.street = houseBuilder.getStreet();
        this.amountOfFloors = houseBuilder.getAmountOfFloors();
    }

    @Getter
    public static class HouseBuilder {

        private Integer number;
        private String street;

        // unimportant;
        private Integer amountOfFloors;

        public HouseBuilder(Integer number, String street) {
            this.number = number;
            this.street = street;
        }

        public HouseBuilder setAmountOfFloors(Integer amountOfFloors) {
            this.amountOfFloors = amountOfFloors;
            return this;
        }

        public House build() {
            return new House(this);
        }
    }

    @Override
    public String toString() {
        return "House{" +
                "number=" + number +
                ", street='" + street + '\'' +
                ", amountOfFloors=" + amountOfFloors +
                '}';
    }
}
