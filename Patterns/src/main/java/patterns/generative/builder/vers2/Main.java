package patterns.generative.builder.vers2;

public class Main {
    public static void main(String[] args) {
        Car car = new Car.CarBuilder("Volvo", 201).build();
        Car car2 = new Car.CarBuilder("Wolswagen", 199)
                                    .setColor("grey")
                                    .setAmountDoors(4)
                                    .build();
        System.out.println(car);
        System.out.println("---------------------------------------------");
        System.out.println(car2);
    }
}
