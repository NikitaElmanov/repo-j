package patterns.structure.bridge;

import java.util.Arrays;

public class BridgeMain {

    public static void main(String[] args) {
        new BridgeMain().test();
    }

    void test() {
        Event[] events = {
                new LongTrip(new SportCar()),
                new Race(new Track())
        };

        Arrays.stream(events).forEach(Event::move);
    }

    interface Car {

        void go();
    }

    class Track implements Car {

        @Override
        public void go() {
            System.out.println("Track is going");
        }
    }

    class SportCar implements Car {

        @Override
        public void go() {
            System.out.println("SportCar is going");
        }
    }

    abstract class Event {

        protected Car car;

        protected Event(final Car car) {
            this.car = car;
        }

        abstract void move();
    }

    class Race extends Event {

        protected Race(final Car car) {
            super(car);
        }

        @Override
        void move() {
            System.out.println("it's a racing now");
            car.go();
        }
    }

    class LongTrip extends Event {

        protected LongTrip(final Car car) {
            super(car);
        }

        @Override
        void move() {
            System.out.println("it's a long trip now");
            car.go();
        }
    }

}
