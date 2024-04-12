package patterns.generative.builder.vers2;

public class Car {

    //Important fields
    private String name;
    private int horsePower;

    //UNImportant fields
    private String color;
    private int amountDoors;

    private Car(CarBuilder carBuilder) {
        this.name = carBuilder.name;
        this.horsePower = carBuilder.horsePower;
        this.color = carBuilder.color;
        this.amountDoors = carBuilder.amountDoors;
    }

    static class CarBuilder {

        //Important fields
        private String name;
        private int horsePower;

        //UNImportant fields
        private String color;
        private int amountDoors;

        public CarBuilder(String name, int horsePower) {
            this.name = name;
            this.horsePower = horsePower;
        }

        public CarBuilder setColor(String color) {
            this.color = color;
            return this;
        }

        public CarBuilder setAmountDoors(int amountDoors) {
            this.amountDoors = amountDoors;
            return this;
        }

        public Car build() {
            return new Car(this);
        }
    }

    @Override
    public String toString() {
        return "Car{" +
                "name='" + name + '\'' +
                ", horsePower=" + horsePower +
                ", color='" + color + '\'' +
                ", amountDoors=" + amountDoors +
                '}';
    }
}
